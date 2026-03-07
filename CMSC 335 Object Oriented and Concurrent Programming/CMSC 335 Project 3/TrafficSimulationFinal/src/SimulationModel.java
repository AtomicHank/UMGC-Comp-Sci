import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SimulationModel {
    public static final double DISTANCE_BETWEEN_INTERSECTIONS_METERS = 1000.0;
    private static final DateTimeFormatter CLOCK_FORMAT = DateTimeFormatter.ofPattern("HH:mm:ss");

    public static class IntersectionState {
        private final int id;
        private final double xPositionMeters;
        private TrafficLightColor lightColor;

        IntersectionState(int id, double xPositionMeters, TrafficLightColor lightColor) {
            this.id = id;
            this.xPositionMeters = xPositionMeters;
            this.lightColor = lightColor;
        }

        public int getId() {
            return id;
        }

        public double getXPositionMeters() {
            return xPositionMeters;
        }

        public TrafficLightColor getLightColor() {
            return lightColor;
        }
    }

    public static class CarState {
        private final int id;
        private double xPositionMeters;
        private double yPositionMeters;
        private double speedKmh;
        private int waitingIntersectionId;

        CarState(int id, double xPositionMeters, double speedKmh) {
            this.id = id;
            this.xPositionMeters = xPositionMeters;
            this.yPositionMeters = 0.0;
            this.speedKmh = speedKmh;
            this.waitingIntersectionId = -1;
        }

        public int getId() {
            return id;
        }

        public double getXPositionMeters() {
            return xPositionMeters;
        }

        public double getYPositionMeters() {
            return yPositionMeters;
        }

        public double getSpeedKmh() {
            return speedKmh;
        }

        public boolean isWaitingAtRed() {
            return waitingIntersectionId != -1;
        }

        public int getWaitingIntersectionId() {
            return waitingIntersectionId;
        }
    }

    public static class Snapshot {
        public final String currentClock;
        public final long elapsedSeconds;
        public final List<IntersectionState> intersections;
        public final List<CarState> cars;

        Snapshot(String currentClock, long elapsedSeconds, List<IntersectionState> intersections, List<CarState> cars) {
            this.currentClock = currentClock;
            this.elapsedSeconds = elapsedSeconds;
            this.intersections = intersections;
            this.cars = cars;
        }
    }

    private final Random random = new Random();
    private final List<IntersectionState> intersections = new ArrayList<>();
    private final List<CarState> cars = new ArrayList<>();

    private String currentClock = LocalTime.now().format(CLOCK_FORMAT);
    private long elapsedSeconds;
    private int nextCarId = 1;
    private int nextIntersectionId = 1;

    public SimulationModel() {
        initializeDefaults();
        resetForNewRun();
    }

    private void initializeDefaults() {
        intersections.clear();
        cars.clear();
        nextCarId = 1;
        nextIntersectionId = 1;

        addIntersectionInternal();
        addIntersectionInternal();
        addIntersectionInternal();

        addCarInternal();
        addCarInternal();
        addCarInternal();
    }

    public synchronized void resetForNewRun() {
        elapsedSeconds = 0;
        currentClock = LocalTime.now().format(CLOCK_FORMAT);

        for (int i = 0; i < intersections.size(); i++) {
            intersections.get(i).lightColor = initialLightForIndex(i);
        }

        for (int i = 0; i < cars.size(); i++) {
            CarState car = cars.get(i);
            car.xPositionMeters = -200.0 * i;
            car.yPositionMeters = 0.0;
            car.speedKmh = 35.0 + random.nextDouble() * 15.0;
            car.waitingIntersectionId = -1;
        }
    }

    public synchronized int addCar() {
        return addCarInternal().id;
    }

    public synchronized int addIntersection() {
        return addIntersectionInternal().id;
    }

    public synchronized int getIntersectionCount() {
        return intersections.size();
    }

    public synchronized List<Integer> getIntersectionIds() {
        List<Integer> ids = new ArrayList<>();
        for (IntersectionState intersection : intersections) {
            ids.add(intersection.id);
        }
        return ids;
    }

    public synchronized void updateClock() {
        currentClock = LocalTime.now().format(CLOCK_FORMAT);
        elapsedSeconds++;
    }

    public synchronized TrafficLightColor getLightColor(int intersectionId) {
        IntersectionState intersection = findIntersection(intersectionId);
        return intersection == null ? TrafficLightColor.RED : intersection.lightColor;
    }

    public synchronized void setLightColor(int intersectionId, TrafficLightColor color) {
        IntersectionState intersection = findIntersection(intersectionId);
        if (intersection != null) {
            intersection.lightColor = color;
        }
    }

    public synchronized boolean hasIntersection(int intersectionId) {
        return findIntersection(intersectionId) != null;
    }

    public synchronized void updateCars(double deltaSeconds) {
        if (intersections.isEmpty()) {
            return;
        }

        double farEdge = intersections.get(intersections.size() - 1).xPositionMeters + 240.0;

        for (CarState car : cars) {
            if (car.waitingIntersectionId != -1) {
                TrafficLightColor waitLight = getLightColor(car.waitingIntersectionId);
                if (waitLight == TrafficLightColor.RED) {
                    car.speedKmh = 0.0;
                    continue;
                }
                car.waitingIntersectionId = -1;
                car.speedKmh = 34.0 + random.nextDouble() * 18.0;
            }

            if (random.nextDouble() < 0.08) {
                double shift = random.nextDouble() * 8.0 - 4.0;
                car.speedKmh = clamp(car.speedKmh + shift, 28.0, 62.0);
            }

            double speedMps = car.speedKmh * 1000.0 / 3600.0;
            double nextX = car.xPositionMeters + speedMps * deltaSeconds;

            IntersectionState redStop = findNextRedIntersectionCrossed(car.xPositionMeters, nextX);
            if (redStop != null) {
                car.xPositionMeters = redStop.xPositionMeters - 3.0;
                car.yPositionMeters = 0.0;
                car.speedKmh = 0.0;
                car.waitingIntersectionId = redStop.id;
                continue;
            }

            car.xPositionMeters = nextX;
            car.yPositionMeters = 0.0;

            if (car.xPositionMeters > farEdge) {
                car.xPositionMeters = -250.0 - random.nextDouble() * 260.0;
                car.yPositionMeters = 0.0;
                car.speedKmh = 32.0 + random.nextDouble() * 18.0;
                car.waitingIntersectionId = -1;
            }
        }
    }

    public synchronized Snapshot snapshot() {
        List<IntersectionState> intersectionCopy = new ArrayList<>();
        for (IntersectionState intersection : intersections) {
            intersectionCopy.add(new IntersectionState(intersection.id, intersection.xPositionMeters, intersection.lightColor));
        }

        List<CarState> carCopy = new ArrayList<>();
        for (CarState car : cars) {
            CarState clone = new CarState(car.id, car.xPositionMeters, car.speedKmh);
            clone.yPositionMeters = car.yPositionMeters;
            clone.waitingIntersectionId = car.waitingIntersectionId;
            carCopy.add(clone);
        }

        return new Snapshot(currentClock, elapsedSeconds, intersectionCopy, carCopy);
    }

    private CarState addCarInternal() {
        int index = cars.size();
        CarState car = new CarState(nextCarId++, -200.0 * index, 35.0 + random.nextDouble() * 15.0);
        cars.add(car);
        return car;
    }

    private IntersectionState addIntersectionInternal() {
        int index = intersections.size();
        double xPosition = DISTANCE_BETWEEN_INTERSECTIONS_METERS * (index + 1);
        IntersectionState intersection = new IntersectionState(nextIntersectionId++, xPosition, initialLightForIndex(index));
        intersections.add(intersection);
        return intersection;
    }

    private IntersectionState findIntersection(int intersectionId) {
        for (IntersectionState intersection : intersections) {
            if (intersection.id == intersectionId) {
                return intersection;
            }
        }
        return null;
    }

    private IntersectionState findNextRedIntersectionCrossed(double currentX, double nextX) {
        for (IntersectionState intersection : intersections) {
            if (intersection.lightColor != TrafficLightColor.RED) {
                continue;
            }
            double stopLine = intersection.xPositionMeters - 3.0;
            if (currentX < stopLine && nextX >= stopLine) {
                return intersection;
            }
        }
        return null;
    }

    private static TrafficLightColor initialLightForIndex(int index) {
        int mod = index % 3;
        if (mod == 0) {
            return TrafficLightColor.RED;
        }
        if (mod == 1) {
            return TrafficLightColor.GREEN;
        }
        return TrafficLightColor.YELLOW;
    }

    private static double clamp(double value, double min, double max) {
        return Math.max(min, Math.min(max, value));
    }
}

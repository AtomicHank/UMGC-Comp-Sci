public class TrafficLightWorker implements Runnable {
    private final int intersectionId;
    private final SimulationModel model;
    private final SimulationControl control;
    private final Runnable uiRefresh;

    public TrafficLightWorker(int intersectionId, SimulationModel model, SimulationControl control, Runnable uiRefresh) {
        this.intersectionId = intersectionId;
        this.model = model;
        this.control = control;
        this.uiRefresh = uiRefresh;
    }

    @Override
    public void run() {
        while (control.isRunning()) {
            try {
                control.awaitIfPaused();
                if (!control.isRunning() || !model.hasIntersection(intersectionId)) {
                    break;
                }

                TrafficLightColor current = model.getLightColor(intersectionId);
                int sleepMs;
                TrafficLightColor next;

                switch (current) {
                    case RED:
                        sleepMs = 12000;
                        next = TrafficLightColor.GREEN;
                        break;
                    case GREEN:
                        sleepMs = 10000;
                        next = TrafficLightColor.YELLOW;
                        break;
                    default:
                        sleepMs = 3000;
                        next = TrafficLightColor.RED;
                        break;
                }

                Thread.sleep(sleepMs);
                if (!control.isRunning()) {
                    break;
                }

                model.setLightColor(intersectionId, next);
                uiRefresh.run();
            } catch (InterruptedException ignored) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}

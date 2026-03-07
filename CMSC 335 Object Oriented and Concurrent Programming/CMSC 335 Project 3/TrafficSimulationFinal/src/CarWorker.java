public class CarWorker implements Runnable {
    private final SimulationModel model;
    private final SimulationControl control;
    private final Runnable uiRefresh;

    public CarWorker(SimulationModel model, SimulationControl control, Runnable uiRefresh) {
        this.model = model;
        this.control = control;
        this.uiRefresh = uiRefresh;
    }

    @Override
    public void run() {
        while (control.isRunning()) {
            try {
                control.awaitIfPaused();
                if (!control.isRunning()) {
                    break;
                }
                model.updateCars(0.25);
                uiRefresh.run();
                Thread.sleep(250);
            } catch (InterruptedException ignored) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}

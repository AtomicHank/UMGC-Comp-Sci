public class ClockWorker implements Runnable {
    private final SimulationModel model;
    private final SimulationControl control;
    private final Runnable uiRefresh;

    public ClockWorker(SimulationModel model, SimulationControl control, Runnable uiRefresh) {
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
                model.updateClock();
                uiRefresh.run();
                Thread.sleep(1000);
            } catch (InterruptedException ignored) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}

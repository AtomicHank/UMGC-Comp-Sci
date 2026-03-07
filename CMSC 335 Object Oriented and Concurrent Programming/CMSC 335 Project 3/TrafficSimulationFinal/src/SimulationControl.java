public class SimulationControl {
    private boolean running;
    private boolean paused;

    public synchronized boolean start() {
        if (running) {
            return false;
        }
        running = true;
        paused = false;
        notifyAll();
        return true;
    }

    public synchronized void pause() {
        if (running) {
            paused = true;
        }
    }

    public synchronized void resume() {
        if (running) {
            paused = false;
            notifyAll();
        }
    }

    public synchronized void stop() {
        running = false;
        paused = false;
        notifyAll();
    }

    public synchronized void awaitIfPaused() throws InterruptedException {
        while (running && paused) {
            wait();
        }
    }

    public synchronized boolean isRunning() {
        return running;
    }

    public synchronized boolean isPaused() {
        return paused;
    }
}

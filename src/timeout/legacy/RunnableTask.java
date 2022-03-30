package timeout.legacy;

public class RunnableTask implements Runnable {

    private volatile boolean stop = false;

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            if (!stop) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(i);
            }
        }
    }

    public void stop() {
        stop = true;
    }
}

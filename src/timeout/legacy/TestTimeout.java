package timeout.legacy;

public class TestTimeout {

    public static void main(String[] args) throws InterruptedException {
        RunnableTask myTask = new RunnableTask();
        Thread t1 = new Thread(myTask);;
        t1.start();
        Thread.sleep(5000);

        System.out.println("main thread is back to stop");
        myTask.stop();
    }
}

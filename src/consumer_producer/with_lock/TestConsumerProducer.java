package consumer_producer.with_lock;

public class TestConsumerProducer {
    public static void main(String[] args) throws InterruptedException {
        MyBlockingQueue test = new MyBlockingQueue();
        new Thread(() -> {
            for(int i = 1; i <= 20; i ++) {
                if(i%5 == 0) {
                    new Thread(() -> test.consume()).start();
                } else {
                    new Thread(() -> test.produce()).start();
                }
            }
        }).start();
        Thread.sleep(3000);
        new Thread(() -> test.consume()).start();
        new Thread(() -> test.consume()).start();
        new Thread(() -> test.consume()).start();
        new Thread(() -> test.produce()).start();
    }
}

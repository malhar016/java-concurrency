package consumer_producer.with_lock;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyBlockingQueue {

    Queue<Runnable> sharedQueue = new ArrayDeque<>();
    private static int MAX_COUNT = 10;

    final Lock lock = new ReentrantLock();
    final Condition notEmpty = lock.newCondition();
    final Condition notFull = lock.newCondition();
    int count = 1;

    public void produce() {
        lock.lock();
        try {
            if(sharedQueue.size() == MAX_COUNT){
                System.out.println("queue is full so waiting for consumption...");
                notFull.await();
            }
            System.out.println("produced item: " + count);
            sharedQueue.add(() -> System.out.println("consuming item: " + count));
            count ++;
            notEmpty.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void consume() {
        lock.lock();
        try {
            if(sharedQueue.size() == 0){
                System.out.println("queue is empty so waiting for production...");
                notEmpty.await();
            }
            count --;
            sharedQueue.poll().run();
            notFull.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}





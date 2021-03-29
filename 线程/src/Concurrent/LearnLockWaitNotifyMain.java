package Concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LearnLockWaitNotifyMain {
    public static void main(String[] args) {
        // TODO ReentrantLock类构造方法的参数fair：如果输入true，则创建的实例为公平锁（有代价）。一般默认值为false，使用非公平锁。
        Lock locker = new ReentrantLock(false);
        Condition condition = locker.newCondition();

        int workingSec = 2;
        int threadCount = 3;

        for(int i = 0; i < threadCount; i++){
            new Thread(() -> {
                System.out.println(getName() + "开始工作...");
                try {
                    locker.lock();
                    sleepSec(workingSec);
                    System.out.println(getName() + "进入等待...");
                    // TODO await方法调用后自动失去锁
                    condition.await();
                    // TODO 线程被唤醒后，第一件事就是去抢之前失去的锁。
                    System.out.println(getName() + "线程继续...");
                    sleepSec(2);
                    System.out.println(getName() + "结束");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    locker.unlock();
                }
            },"工作线程" + i).start();
        }

        System.out.println("--- Main线程开始sleep ---");
        // TODO await和signal/signalAll方法也会产生lost Notification问题。
        sleepSec(workingSec + 1);
        System.out.println("--- Main线程sleep结束 ---");
        try {
            locker.lock();
            // TODO signal与signalAll必须在当前线程获取锁之后才能调用。
            for(int i = 0; i < threadCount; i++){
                System.out.println("--- Main线程开始逐个唤醒其他工作线程 ---");
                condition.signal();
            }
//            System.out.println("--- Main线程开始唤醒所有的工作线程 ---");
//            condition.signalAll();
        }finally {
            locker.unlock();
        }
    }

    private static String getName(){
        return Thread.currentThread().getName();
    }

    private static void sleepSec(int workingSec){
        try {
            Thread.sleep(TimeUnit.SECONDS.toMillis(workingSec));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

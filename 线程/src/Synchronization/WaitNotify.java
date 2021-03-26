package Synchronization;

import java.util.concurrent.TimeUnit;

public class WaitNotify {
    public static void main(String[] args) {
        Object locker = new Object();

        int workingSec = 2;
        int threadCount = 5;

        for(int i = 0; i < threadCount; i++){
            // TODO 这里没有另外写一个类，其直接利用lambda表达式创建了一个Runnable实例。然后在实例上调了start.
            new Thread(() -> {
                System.out.println(getName() + "开始工作2s...");
                try{
                    synchronized (locker){
                        sleepSec(workingSec);
                        System.out.println(getName() + "进入等待...");
                        // TODO wait方法必须在进入对象的synchronized代码块时才能调用。如果你另写了一个类，在类的【实例方法】中也可调this.wait/notify/notifyAll.
                        // TODO 进入synchronized代码块后，线程会获得对象的monitor，执行完wait后，线程会失去对象的monitor。（代表其他线程可进了）
                        // TODO 如果wait方法不是synchronized代码块的最后一句，那么线程被唤醒后，第一件事就是去抢monitor...抢到的线程继续往下执行代码。
                        locker.wait();
                        System.out.println(getName() + "继续工作...");
                        sleepSec(2);
                        System.out.println(getName() + "执行结束。");
                    }
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },"线程" + i).start();
        }

        System.out.println("--- Main线程待机3s ---");
        // TODO synchronized锁不是公平锁，main线程也不能保证它一定最后一个拿到锁。只是从概率上讲，main线程多睡1s，所以大概率是最后拿到锁的线程。
        sleepSec(workingSec + 1);
        // TODO 如果有些线程wait之前先notify，那么就会发生lost notification，如下。
        //sleepSec(workingSec - 1);
        System.out.println("--- Main线程待机结束 ---");

        synchronized (locker){
            //System.out.println("--- 开始唤醒所有线程 ---");
            //locker.notifyAll();
            for(int i =0; i < threadCount; i++){
                System.out.println("--- 开始逐个唤醒线程 ---");
                locker.notify();
            }
        }
    }

    private static String getName(){ return Thread.currentThread().getName(); }

    private static void sleepSec(int sec){
        try{
            Thread.sleep(TimeUnit.SECONDS.toMillis(sec));
        }catch (InterruptedException ex){
            ex.printStackTrace();
        }
    }
}

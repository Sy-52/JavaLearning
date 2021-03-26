package Synchronization;

public class DataHolder {
    private long number = 0;
    public static long numberStatic = 0;
    private Object obj = new Object();

    //public void change(long delta){ this.number += delta; }
    // TODO 如果有一个线程在执行【该实例的synchronized方法】，那么其他线程要等该线程执行结束才能执行【该实例的synchronized方法】。
    // TODO synchronized方法会斩断CPU缓存数据的行为，其每次执行都会从内存中重新读取数据来进行操作。
    // TODO synchronized方法会慢很多。因为：1、两个线程次序执行，要2倍时间。2、多出在一个线程结束后让另一个线程进来的操作.3、从内存中读数据。
    public synchronized void change(long delta){
        this.number += delta;
    }

    // TODO 多个线程调用同一实例上的静态方法，线程也会依据同步控制的规则次序调用。
    public synchronized static void changeStatic(long delta){
        numberStatic += delta;
    }

    public void changeSyncBlock(long delta){
        int a = 5;
        // TODO 如果有多个线程通过同一实例进入了这个方法，执行到synchronized代码块，线程会次序执行。（其本质就是在实例上加了一个锁，避免多线程同时进入。）
        synchronized (this){ number += delta; }
        // TODO 对于静态方法，因为锁的是类的class，所以即使多个线程通过不同的实例去调用该方法，也要次序执行。
        //synchronized (DataHolder.class){ number += delta; }
        // TODO 任何的一个对象都可做锁，关键看你要锁在哪个对象上。
        //synchronized (obj){ number += delta; }
        int b = 10;
    }

    public void print(){ System.out.println("Number=" + number); }

    public static void printStatic(){ System.out.println("Number=" + numberStatic); }
}

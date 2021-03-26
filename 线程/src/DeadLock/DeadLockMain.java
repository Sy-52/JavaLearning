package DeadLock;

public class DeadLockMain {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("程序开始");
        // TODO 这里人为造成了一个死锁。避免死锁的方法就是：按照顺序申请资源。
        Resources resources = new Resources();
        Thread task1 = new Thread(new Task1(resources),"任务线程1");
        task1.start();
        Thread task2 = new Thread(new Task2(resources),"任务线程2");
        task2.start();

        task1.join();
        task2.join();

        System.out.println("程序结束");
    }
}

package Synchronization;

public class MultiThreadVersion {
    public static void main(String[] args) {
        DataHolder dataHolder = new DataHolder();
        // TODO 如果不同线程去调不同实例的synchronized方法，那么线程间就互不影响了。（且java对只有一个线程调用同步控制方法的情况做了优化）
        //DataHolder dataHolder1 = new DataHolder();
        // TODO 因为ChangeData实现了Runnable接口，所以其实例可以作为参数传入.
        Thread increaseThread = new Thread(new ChangeData(2,Integer.MAX_VALUE / 30,dataHolder));
        Thread decreaseThread = new Thread(new ChangeData(-2,Integer.MAX_VALUE / 30,dataHolder));
        System.out.println("2个线程开始执行...");
        increaseThread.start();
        decreaseThread.start();
        // TODO 一个线程在读取、计算、写回实例的成员变量值的过程中，可能会有其他线程乱入。再加上现代计算机CPU的缓存机制，（其不会每次计算后都写回），所以会造成混乱。
    }
}

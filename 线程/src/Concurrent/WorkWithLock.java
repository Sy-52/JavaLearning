package Concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class WorkWithLock implements Runnable{
    // TODO Lock接口中最常用的实现类是：ReentrantLock(adj.可再进入的).
    private Lock lock;

    public WorkWithLock(Lock lock){this.lock = lock;}

    @Override
    public void run() {
        while(true) {
            try {
                // TODO 标准使用方法如下。但如果用tryLock方法的话，就必须在if语句中写try...finally语句去释放掉锁。
                // TODO 从行为上说，Reentrant这套机制是可以和Synchronized(){}对应上的。
                //try{lock.lock();}finally{lock.unlock();}
                if (lock.tryLock(1, TimeUnit.SECONDS)) {
                    try {
                        printLockStatus("开始");
                        process1();
                    } finally {
                        lock.unlock();
                        printLockStatus("结束");
                    }
                } else {
                    printLockFailure();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void printLockStatus(String phase){
        // TODO 每获取到一个锁，计数器都会+1。
        System.out.println("当前线程为:" + Thread.currentThread().getName() + "; 阶段：" + phase
                + "; 持有锁数量：" + ((ReentrantLock)lock).getHoldCount());
    }

    private void printLockFailure(){
        System.out.println(Thread.currentThread().getName() + "尝试获取锁失败.");
    }

    private void process1(){
        try {
            lock.lock();
            printLockStatus("process1");
            process2();
        }finally {
            lock.unlock();
            printLockStatus("process1结束");
        }
    }

    private void process2(){
        try {
            lock.lock();
            printLockStatus("process2");
            process3();
        }finally {
            lock.unlock();
            printLockStatus("process2结束");
        }
    }

    private void process3(){
        try {
            lock.lock();
            printLockStatus("process3");
            Thread.sleep(TimeUnit.SECONDS.toMillis(3));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            printLockStatus("process3结束");
        }
    }
}

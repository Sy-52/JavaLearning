import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class InterruptThreadMain {
    public static final String TEXT = "仙人渺姑射，万顷飞霜花。\n长啸激云月，登天餐紫霞。\n凌风无御剑，桴海不乘槎。\n论道期何处，飘飘一天涯。";

    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + "线程开始执行.");

        List<Thread> threads = new ArrayList<>();

        for(int i = 1; i <=1; i++){
            //Thread thread = new Thread(new NotHandleInterrupt(TimeUnit.SECONDS.toMillis(8)),"我的线程" + i);
            // TODO 如果线程在sleep的时候被interrupt，就会抛interruptedException，如下。
            Thread thread = new Thread(new CreateOwnThreadMain.PrintStoryRunnable(TEXT, 200),"我的线程" + i);
            thread.start();
            threads.add(thread);
        }

        Thread.sleep(TimeUnit.SECONDS.toMillis(5));

        System.out.println("\n开始interrupt线程");
        // TODO interrupt只是一种标识。到底是否需要写代码去处理这个状态是我们自己决定的。
        threads.forEach(Thread::interrupt);
        System.out.println("线程interrupt执行结束，main线程再睡5秒钟等我的线程1.");
        Thread.sleep(TimeUnit.SECONDS.toMillis(5));
        System.out.println(Thread.currentThread().getName() + "线程执行结束.");
    }

    static class NotHandleInterrupt implements Runnable{
        private long duration;

        public NotHandleInterrupt(long duration){ this.duration = duration; }

        // TODO 只要启动一个线程，就会调用Runnable实现类所创建实例的实例方法run.
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "开始执行.");
            long startTime = System.currentTimeMillis();
            boolean isInterrupted = Thread.currentThread().isInterrupted();
            System.out.println(Thread.currentThread().getName() + "的isInterrupted为" + isInterrupted);
            while(true){
                if(isInterrupted != Thread.currentThread().isInterrupted()){
                    isInterrupted = Thread.currentThread().isInterrupted();
                    System.out.println("监控到" + Thread.currentThread().getName() +"的isInterrupted变化为" + isInterrupted);
                    System.out.println("此时我可以搞事，但我决定啥也不干。");
                }
                if(System.currentTimeMillis() - startTime > duration){
                    break;
                }
            }
            System.out.println(Thread.currentThread().getName() + "执行结束.");
        }
    }
}

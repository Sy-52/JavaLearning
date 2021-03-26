import java.util.concurrent.TimeUnit;

public class DaemonThreadMain {
    public static final String TEXT = "江帆归日月，游子复离歌。\n霜下千山暮，剑弹浩渺波。\n雁声亭外远，孤梦客中多。\n谁许秋风起，萧萧散鬓河。";

    public static void main(String[] args) throws InterruptedException {
        System.out.println("程序开始执行，执行的线程为" + Thread.currentThread().getName());
        for(int i = 1; i <=1; i++){
            Thread thread = new Thread(new CreateOwnThreadMain.PrintStoryRunnable(TEXT, 200),"线程" + i);
            // TODO 在启动线程之前，将该线程设置为守护线程。
            thread.setDaemon(true);
            // TODO 无论是否为守护线程，均可设置优先级。但优先级的作用不能保证，要看线程运行状态、机器本身的运行状态。
            thread.setPriority(Thread.MAX_PRIORITY);
            thread.start();
        }
        // TODO TimeUnit提供了一些时间单位间的互转方法。
        Thread.sleep(TimeUnit.SECONDS.toMillis(5));
        System.out.println("\n线程" + Thread.currentThread().getName() + "，执行结束。");
    }
}

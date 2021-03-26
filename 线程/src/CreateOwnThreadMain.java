public class CreateOwnThreadMain {
    public static final String TEXT = "江帆归日月，游子复离歌。\n霜下千山暮，剑弹浩渺波。\n雁声亭外远，孤梦客中多。\n谁许秋风起，萧萧散鬓河。";

    public static void main(String[] args) {
        // TODO currentThread返回的是当前正在执行这段代码的线程实例（虽然可能会有多个线程执行这段代码）
        System.out.println("程序开始执行，执行的线程为" + Thread.currentThread().getName());
        for(int i = 1; i <=1; i++){
            Thread thread = new Thread(new PrintStoryRunnable(TEXT, 200),"我的线程" + i);
            // TODO 只有调用start才能让CPU去另启一个线程。线程启动后，不会阻塞在原来的Main线程，而是到另一条道上去了。
            thread.start();
        }
        System.out.println("线程" + Thread.currentThread().getName() + "，执行结束。");
    }

    static class PrintStoryRunnable implements Runnable{
        private String text;
        private long interval;

        public PrintStoryRunnable(String text, long interval){
            this.text = text;
            this.interval = interval;
        }

        // TODO 只要启动一个线程，就会调用Runnable实现类所创建实例的实例方法run.
        @Override
        public void run() {
            try {
                // TODO 每个执行run的线程（执行这段代码）的num都各不相同（创建局部变量）.
                double num = Math.random();
                System.out.println(Thread.currentThread().getName() + "开始执行...");
                printSlowly(text, interval);
                System.out.println(Thread.currentThread().getName() + "执行结束.");
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }

        private void printSlowly(String str, long interval) throws InterruptedException {
            for (char ch : str.toCharArray()){
                // TODO 调用Thread类中的静态方法sleep.
                Thread.sleep(interval);
                System.out.print(ch);
            }
            System.out.println();
        }
    }
}

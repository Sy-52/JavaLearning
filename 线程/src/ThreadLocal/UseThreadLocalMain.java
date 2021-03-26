package ThreadLocal;

public class UseThreadLocalMain {
    public static void main(String[] args) {
        for(int i = 0; i < 3; i++){
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "开始处理...");
                PerformanceTracker.reset();
                InputHandler inputHandler = new InputHandler();
                String content = inputHandler.getInput();

                DBQuery query = new DBQuery();
                query.query();

                ContentProcess contentProcess = new ContentProcess();
                contentProcess.process(content);

                PerformanceTracker.finish();
                System.out.println(Thread.currentThread().getName() + "处理结束");
            },"线程" + i).start();
        }
    }
}

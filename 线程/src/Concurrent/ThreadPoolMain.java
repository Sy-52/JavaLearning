package Concurrent;

import java.util.concurrent.*;

public class ThreadPoolMain {
    public static void main(String[] args) {
        // TODO ExecutorService是接口。Executors.newFixedThreadPool返回的是一个ThreadPoolExecutor实例。
        ExecutorService executorService = Executors.newFixedThreadPool(1);

        // TODO submit的实参用lambda表达式也可以。
        executorService.submit(new Runnable(){
            public void run(){
                printExecutionThreadStatus("run方法");
            }
        });
        System.out.println("run任务已提交.");

        Future<String> future = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                printExecutionThreadStatus("call方法");
                System.out.println("call方法的代码开始执行...");
                Thread.sleep(TimeUnit.SECONDS.toMillis(3));
                System.out.println("call方法的代码执行结束.");
                return "value of call task";
            }
        });
        System.out.println("call任务已提交.");

        // TODO shutDown关闭后，只要有被提交的task，那么shutdown方法会等提交的所有线程执行结束后，才会关闭线程，释放线程资源。
        // TODO shutDown关闭后，不会再接受新提交的task.
        System.out.println("所有任务均已提交完毕，关闭线程池。");
        executorService.shutdown();

        try {
            System.out.println("现在开始调用get方法获取call方法的返回值：");
            // TODO get方法会抛出InterruptedException，所以调get方法后会一直等着，直到得到返回结果。
            // TODO 如果get之前线程已经结束，那么会立刻返回值。
            String result = future.get();
            System.out.println("获取到了call方法的返回值，为：" + result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private static void printExecutionThreadStatus(String phase){
        System.out.println("正在执行线程池里的线程" + Thread.currentThread().getName() + "中" + phase + "的代码...");
    }
}

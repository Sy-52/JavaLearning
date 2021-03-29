package Concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CountDownLatchMain {
    private static final List<String> CONTENTS = new ArrayList<>();
    private static long WORKING_DURATION = 0;

    public static void main(String[] args) throws InterruptedException {
        int threadCount = 10;

        CountDownLatch countDownLatch = new CountDownLatch(threadCount);

        long mainStartTime = System.currentTimeMillis();

        for(int i = 0; i < threadCount; i++){
            Thread thread = new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "开始抓取网页内容...");
                long startTime = System.currentTimeMillis();
                String webContent = getContentFromWeb();
                long workingDuration  = System.currentTimeMillis() - startTime;
                System.out.println(Thread.currentThread().getName() + "抓取网页内容结束.");
                synchronized(CONTENTS){
                    CONTENTS.add(webContent);
                    WORKING_DURATION += workingDuration;
                }
                // TODO join()本质是检测线程调度状态...如果线程还未启动/线程已经结束，join会立刻返回。（在要与线程绑定的情况下适用）
                // TODO countDownLatch是独立的，只要你创建了实例，就可以调用await、countDown。无所谓你是5个线程countDown2次还是...，只要countDown到0，await就会结束.
                countDownLatch.countDown();
                try {
                    Thread.sleep(TimeUnit.SECONDS.toMillis(5));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "结束");
            },"线程" + i);

            thread.sleep(10);
            thread.start();
        }
        // TODO countDownLatch.await会等待countDownLatch的值变为0（线程干完活后countDownLatch会减1），才会继续往下执行。
        countDownLatch.await();

        CONTENTS.forEach((s) -> {
            System.out.print(s.length() + ":");
            System.out.println(s);
        });

        long mainWorkingDuration = System.currentTimeMillis() - mainStartTime;
        System.out.println("工作线程累计工作时间：" + WORKING_DURATION);
        System.out.println("主线程工作时间：" + mainWorkingDuration);
        System.out.println("主线程结束.");
    }

    private static String getContentFromWeb(){
        StringBuilder ret = new StringBuilder();
        for(int i = 0; i < 128; i++){
            int rand = (int)(Math.random() * 1000) % 26;
            char ch = (char)(rand + 'A');
            ret.append(ch);
        }
        return  ret.toString();
    }
}

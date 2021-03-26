import java.util.ArrayList;
import java.util.List;

public class ThreadJoinMain {
    private static final List<String> CONTENTS = new ArrayList<>();
    // TODO WORKING_DURATION用以记录所有线程的工作时间之和。
    private static long WORKING_DURATION = 0;

    public static void main(String[] args) throws InterruptedException {
        List<Thread> threads = new ArrayList<>();
        // TODO 顾名思义，记一个main线程工作的时间。
        long mainThreadStartTime = System.currentTimeMillis();

        for(int i = 0; i < 10 ; i++){
            Thread thread = new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "开始抓取网页内容...");
                long startTime = System.currentTimeMillis();
                String webContent = getContentFromWeb();
                long threadWorkingDuration = System.currentTimeMillis() - startTime;
                System.out.println(Thread.currentThread().getName() + "抓取网页内容结束.");
                // TODO add方法不是线程安全的，如果两个线程都在执行add方法的代码，就可能乱套。
                synchronized (CONTENTS){
                    CONTENTS.add(webContent);
                    WORKING_DURATION += threadWorkingDuration;
                }
            },"线程" + i);
            thread.start();
            threads.add(thread);
        }

        // TODO 其实start方法返回后，线程是保证已经启动、运行了的，也可以调join了。这里sleep1ms只是程序员的通病。
        Thread.sleep(1);

        for(Thread thread : threads){
            String name = thread.getName();
            System.out.println("--- Main线程开始join " + name + "---");
            // TODO 如果在Main线程中join其他线程，Main线程会等待那个线程执行结束后，再继续往下执行。
            // TODO 如果在join之前，那个线程已经执行结束了，那么Main线程会【直接】往下执行。
            thread.join();
            System.out.println("--- Main线程join " + name + "结束 ---");
        }

        System.out.println("-------- 各线程抓取到的内容如下 -----------");
        CONTENTS.forEach(s -> {
            System.out.print(s.length() + ":");
            System.out.println(s);
        });

        long mainThreadWorkingDuration = System.currentTimeMillis() - mainThreadStartTime;
        // TODO 多线程的意义在于让工作并发的处理，使用更多的资源（CPU、磁盘、网络...）让工作更快的完成。
        // TODO 但是线程越多，同步的代价也就越高。线程数量与工作时间不是一个线性增长的关系。
        System.out.println("Main线程工作的时间为：" + mainThreadWorkingDuration);
        System.out.println("其余各线程工作的总时间为：" + WORKING_DURATION);
    }

    private static String getContentFromWeb(){
        StringBuilder ret = new StringBuilder();
        int length = (int)(Math.random() * 5000);
        for(int i = 0; i < length; i++){
            int rand = (int)(Math.random() * 1000) % 26;
            char ch = (char)(rand + 'a');
            ret.append(ch);
        }
        return ret.toString();
    }
}

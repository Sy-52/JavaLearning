import java.util.ArrayList;
import java.util.List;

public class ThreadJoinErrorVersionMain {
    private static final List<String> CONTENTS = new ArrayList<>();
    // TODO WORKING_DURATION用以记录所有线程的工作时间之和。
    private static long WORKING_DURATION = 0;

    public static void main(String[] args) throws InterruptedException {
        List<Thread> threads = new ArrayList<>();
        // TODO 记一个新建线程工作的时间。
        long newThreadStartTime = System.currentTimeMillis();

        for(int i = 0; i < 10 ; i++){
            Thread thread = new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "开始抓取网页内容...");
                long startTime = System.currentTimeMillis();
                String webContent = getContentFromWeb();
                long threadWorkingDuration = System.currentTimeMillis() - startTime;
                System.out.println(Thread.currentThread().getName() + "抓取网页内容结束.");
                synchronized (CONTENTS){
                    CONTENTS.add(webContent);
                    WORKING_DURATION += threadWorkingDuration;
                }
            },"线程" + i);
            threads.add(thread);
        }

        new Thread(() -> {
            for(Thread thread : threads){
                String name = thread.getName();
                System.out.println("--- 新建线程开始join " + name + "---");
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("--- 新建线程join " + name + "结束 ---");
            }

            System.out.println("-------- 各线程抓取到的内容如下 -----------");
            if(CONTENTS.size() == 0) System.out.println("空");
            CONTENTS.forEach(s -> {
                System.out.print(s.length() + ":");
                System.out.println(s);
            });

            long newThreadWorkingDuration = System.currentTimeMillis() - newThreadStartTime;
            System.out.println("新建线程工作的时间为：" + newThreadWorkingDuration);
            System.out.println("其余各线程工作的总时间为：" + WORKING_DURATION);
        }).start();
        // TODO 这里Main线程sleep了100ms，目的是确保新建线程【先】去join这些【没有启动的线程】，然后再启动所有的线程。
        Thread.sleep(100);
        threads.forEach(Thread::start);
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


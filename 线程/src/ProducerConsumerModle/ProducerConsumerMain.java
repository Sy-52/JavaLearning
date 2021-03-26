package ProducerConsumerModle;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

public class ProducerConsumerMain {
    public static void main(String[] args) {
        Queue<String> urls = new LinkedList<>();

        Producer<String> producer = new Producer<>(urls, 128);
        Consumer<String> consumer = new Consumer<>(urls);

        for(int i = 0; i < 10; i++){
            Thread consumerThread = new Thread(() -> {
                // TODO 为了保证处理完url的线程不会结束，所以在外面套了一层while循环，以保证一直有1024个线程在取出queue中的元素。
            while(true) {
                String url = null;
                try {
                    url = consumer.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    processURL(url);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            }, "消费者线程" + i);
            consumerThread.start();
        }

        for(int i = 0; i < 2; i++){
            Thread producerThread = new Thread(() -> {
                // TODO 为了保证生产完url的线程不会结束，所以在外面套了一层while循环，以保证一直有3个线程在添加queue中的元素。
                while(true) {
                    try {
                        String url = produceURL();
                        producer.produce(url);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            },"生产者线程" + i);
            producerThread.start();
        }
    }

    private static void processURL(String url) throws InterruptedException {
        System.out.println("开始处理" + url);
        Thread.sleep(TimeUnit.SECONDS.toMillis(1));
        System.out.println(url + "处理完成");
    }

    //随机生成“www.6个字符.com”字符串
    private static String produceURL(){
        StringBuilder ret = new StringBuilder();
        ret.append("www.");
        for(int i = 0; i < 5; i++){
            int rand = (int)(Math.random() * 1000) % 26;
            char ch = (char)(rand + 'a');
            ret.append(ch);
        }
        ret.append(".com");
        return ret.toString();
    }
}

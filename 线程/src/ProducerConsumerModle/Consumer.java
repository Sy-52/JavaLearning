package ProducerConsumerModle;

import java.util.Queue;

public class Consumer<T> {
    private Queue<T> tasks;

    public Consumer(Queue<T> tasks){ this.tasks = tasks;}

    public T consume() throws InterruptedException {
        synchronized (tasks){
            // TODO 此处如果用if，当100个Consumer线程被唤醒后，重新获得锁的第一个线程会从Queue中取出元素，然后释放锁让下一个线程进。但如果在下一个
            // TODO 消费者线程取出元素之前，生产者那边还没来得及add元素进去，那么消费者线程只能从Queue中取出null。所以这里要加while，再做一次检查。
            while(tasks.size() == 0){
                System.out.println(Thread.currentThread().getName() + "进入等待...");
                tasks.wait();
            }
            // TODO poll（）会返回第一个元素，并从队列中删除。
            T ret = tasks.poll();
            tasks.notifyAll();
            return ret;
        }
    }
}

package ProducerConsumerModle;

import java.util.Queue;

public class Producer<T> {
    // TODO LinkedList类实现了Deque、List接口，所以可以被作为实例创建。
    private Queue<T> tasks;

    private int maxTaskCount = 0;

    public Producer(Queue<T> tasks, int maxTaskCount){
        this.tasks = tasks;
        this.maxTaskCount = maxTaskCount;
    }

    public void produce(T url) throws InterruptedException {
        synchronized(tasks){
            while(tasks.size() >= maxTaskCount){
                System.out.println(Thread.currentThread().getName() + "进入等待...");
                tasks.wait();
            }
            tasks.add(url);
            tasks.notifyAll();
        }
    }
}

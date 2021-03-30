package Concurrent;

import java.util.concurrent.LinkedBlockingQueue;

// TODO LinkedBlockingQueue、ConcurrentHashMap是concurrent包中的两个数据结构，都是线程安全的，非常好用。
public class LinkedBlockingQueueMain {
    public static void main(String[] args) throws InterruptedException {
        // TODO linked：一个节点一个节点的拼接。Blocking：同步的。Queue：先进先出。
        LinkedBlockingQueue<String> linkedBlockingQueue = new LinkedBlockingQueue<>(128);
        // TODO 检索，但是不取出元素。
        linkedBlockingQueue.peek();
        // TODO 将传入的元素放进队列，并返回成功/失败。offer有超时版本的重载，等一段时间，行就放不行就拉倒了。
        boolean isAddedSuccessfully = linkedBlockingQueue.offer("");
        // TODO 将元素从队列中拿出，如果队列中没有元素，返回null。poll也有超时版本的重载，等一段时间，行就取出不行也拉倒了。
        linkedBlockingQueue.poll();

        // TODO 将传入的元素放进队列，如果队列满了，等待。
        linkedBlockingQueue.put("");
        // TODO 从队列中取出元素，如果队列为空，则无限等待。一定要从队列中拿一个。
        linkedBlockingQueue.take();
    }
}

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class AtomicNumberMain {
    // TODO concurrent包中为每种基本类型、引用类型都提供了Atomic类，这些Atomic类为基本类型数据操作提供了高效的同步操作；为引用类型在多线程下的引用重新指派保驾护航。
    // TODO 使用Atomic类族可以保证线程的安全。只要能保证线程安全，慢一些毫秒是完全值得的。
    private AtomicLong atomicLong = new AtomicLong(0);
    private volatile long longVar = 0;

    public static void main(String[] args) throws InterruptedException {
        AtomicNumberMain atomicNumberMain = new AtomicNumberMain();
        int loop = Integer.MAX_VALUE / 10;

        int parallel = 10;
        List<Thread> atomicThreads = new ArrayList<>();
        for(int p = 0; p < parallel; p++){
            int loopCount = loop / parallel;
            if(p == parallel - 1){
                loopCount = loop - (loop / parallel) * (parallel - 1);
            }
            int finalLoopCount = loopCount;
            Thread atomic = new Thread(() -> {
                long start = System.currentTimeMillis();
                for(int i = 0; i < finalLoopCount; i++){
                    atomicNumberMain.atomicLong.incrementAndGet();
                }
                System.out.println("Atomic类型变量花费了：" + (System.currentTimeMillis() - start));
            });
            atomic.start();
            atomicThreads.add(atomic);
        }

        Thread primary = new Thread(() -> {
            long start = System.currentTimeMillis();
            for(int i = 0; i < loop; i++){
                atomicNumberMain.longVar++;
            }
            System.out.println("基本类型变量花费了：" + (System.currentTimeMillis() - start));
        });
        primary.start();

        // TODO 使用join，确保所有线程执行完毕后，在输出一个比较结果。
        for(Thread atomicThread : atomicThreads){
            atomicThread.join();
        }
        primary.join();
        System.out.println(atomicNumberMain.toString());
    }

    @Override
    public String toString() {
        return "AtomicNumberMain{" +
                "atomicLong=" + atomicLong +
                ", longVar=" + longVar +
                '}';
    }

}

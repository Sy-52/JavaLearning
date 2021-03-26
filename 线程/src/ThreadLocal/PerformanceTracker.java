package ThreadLocal;

import java.util.ArrayList;
import java.util.List;

public class PerformanceTracker {
    // TODO Phase为静态内部类，用以描述各阶段的名字，以及阶段执行的时间。
    private static class Phase{
        private String name;
        private long duration;

        public Phase(String name, long duration){
            this.name = name;
            this.duration = duration;
        }

        @Override
        public String toString() {
            return "Phase{" +
                    "name='" + name + '\'' +
                    ", duration=" + duration +
                    "}\n";
        }
    }
    // TODO ThreadLocalMap中处理hash冲突的机制和HashMap不同，它采用的是另一种经典的处理方式，即：沿着冲突的索引，向后寻找空闲的位置。
    // TODO ThreadLocal通常用于跨类、跨方法传递一些值，不需要把它作为参数去传递，类似于静态变量。
    // TODO 在一些框架、底层类中可能会用到。避免上层业务代码为了集成这个框架而到处传参数。（业务代码不用这些数据，只为了给框架用）
    private static final ThreadLocal<List<Phase>> PHASE = new ThreadLocal<>();
    private static final ThreadLocal<Long> PHASE_START_TIME = new ThreadLocal<>();

    public static void reset(){
        PHASE.set(new ArrayList<>());
        PHASE_START_TIME.set(-1L);
    }

    public static void startPhase(){
        // TODO 3个线程都调了静态方法startPhase，但是由于线程都有各自的threadlocalmap，所以set的value互不影响。
        // TODO InputHandler、DBQuery、ContentProcess类的getInput、query、process都调了静态方法startPhase，但由于这三个方法会在同一个线程中次序调用，所以也不会产生混乱。
        PHASE_START_TIME.set(System.currentTimeMillis());
    }

    public static void endPhase(String phaseName){
        long start = PHASE_START_TIME.get();
        PHASE.get().add(new Phase(phaseName, System.currentTimeMillis() - start));
    }

    public static void finish(){
        List<Phase> phases = PHASE.get();
        PHASE.set(null);
        PHASE_START_TIME.set(null);

        StringBuilder out = new StringBuilder("--- " + Thread.currentThread().getName() + "各阶段执行时间 ---\n");
        phases.forEach(out::append);
        out.append("------");
        System.out.println(out.toString());
    }
}

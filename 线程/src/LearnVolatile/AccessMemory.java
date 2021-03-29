package LearnVolatile;

public class AccessMemory {
    public volatile long counterV = 0;

    public long counter = 0;

    public static void main(String[] args) {
        int loopCount = Integer.MAX_VALUE / 30;
        AccessMemory accessMemory = new AccessMemory();
        Thread volatileAdder = new Thread(() -> {
            long start = System.currentTimeMillis();
            for(int i = 0; i < loopCount; i++){
                accessMemory.counterV++;
            }
            System.out.println("volatileAdder花费了：" + (System.currentTimeMillis() - start));
        });
        volatileAdder.start();

        Thread justAdder = new Thread(() -> {
            long start = System.currentTimeMillis();
            for(int i = 0; i < loopCount; i++){
                accessMemory.counter++;
            }
            System.out.println("justAdder花费了：" + (System.currentTimeMillis() - start));
        });
        justAdder.start();
    }
}

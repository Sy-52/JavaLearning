package DeadLock;

import java.util.concurrent.TimeUnit;

public class Task2 implements Runnable{
    private Resources resources;

    public Task2(Resources resources){ this.resources = resources;}

    @Override
    public void run() {
        synchronized(resources.getResourcePrinter()){
            System.out.println("Task2获得了Printer资源...");
            System.out.println("Task2开始工作...");
            try {
                Thread.sleep(TimeUnit.SECONDS.toMillis(3));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Task2尝试去获取Input资源...");
            synchronized (resources.getResourceInput()){
                System.out.println("Task2获得了Input资源...");
                System.out.println("Task2继续工作...");
                try {
                    Thread.sleep(TimeUnit.SECONDS.toMillis(2));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
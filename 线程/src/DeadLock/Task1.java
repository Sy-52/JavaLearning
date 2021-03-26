package DeadLock;

import java.util.concurrent.TimeUnit;

public class Task1 implements Runnable{
    private Resources resources;

    public Task1(Resources resources){ this.resources = resources;}

    @Override
    public void run() {
        synchronized(resources.getResourceInput()){
            System.out.println("Task1获得了Input资源...");
            System.out.println("Task1开始工作...");
            try {
                Thread.sleep(TimeUnit.SECONDS.toMillis(2));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Task1尝试去获取Printer资源...");
            synchronized (resources.getResourcePrinter()){
                System.out.println("Task1获得了Printer资源...");
                System.out.println("Task1继续工作...");
                try {
                    Thread.sleep(TimeUnit.SECONDS.toMillis(3));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

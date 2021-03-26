package ThreadLocal;

import java.util.concurrent.TimeUnit;

public class DBQuery {

    public void query(){
        PerformanceTracker.startPhase();
        try {
            Thread.sleep(TimeUnit.SECONDS.toMillis(5));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        PerformanceTracker.endPhase("DataBaseQuery");
    }
}

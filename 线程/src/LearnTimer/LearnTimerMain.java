package LearnTimer;

import java.util.Timer;
import java.util.concurrent.TimeUnit;

public class LearnTimerMain {
    public static void main(String[] args) {
        Timer timer = new Timer();
        // TODO scheduleAtFixedRate会按照设定的时间、间隔去调度指定任务。
        timer.scheduleAtFixedRate(new FindABCTask(), 0, TimeUnit.SECONDS.toMillis(2));
    }
}

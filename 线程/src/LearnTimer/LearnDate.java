package LearnTimer;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class LearnDate {
    public static void main(String[] args) {
//        operateTime();

//        initCalendar();

        setTimeManually();
    }

    private static void operateTime(){
        // TODO Date是表示时间的类，如果要操作时间，就要用Calendar类。用getInstance得到的实例时间默认为当前时间、
        Calendar calender = Calendar.getInstance();
        calender.add(Calendar.DAY_OF_YEAR,30);
        calender.add(Calendar.MONTH,60);
        // TODO 用Calendar操作完时间后，用getTime()获得Date实例传给Date类型的引用，以便其作为参数去使用，非常方便。
        Date date = calender.getTime();
    }

    private static void initCalendar() {
        Calendar calendar = Calendar.getInstance();
        // TODO 如果先set过了calendar的时间，那么后面操作的时间都是基于这个修改过的calendar。
        calendar.setTimeInMillis(System.currentTimeMillis() - TimeUnit.DAYS.toMillis(10));
        calendar.setTime(new Date());
    }

    private static void setTimeManually() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2021,Calendar.JUNE,20,14,0,0);
        Date date = calendar.getTime();
        System.out.println(date);
        // TODO SimpleDateFormat类可以指定Date实例输出的格式，其会根据pattern参数，把其中的相位符替换为真正的时间。
        // TODO SimpleDateFormat类不是线程安全的，多线程公用一个实例就会出现问题。
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒", Locale.SIMPLIFIED_CHINESE);
        System.out.println(sdf.format(date));
    }
}

import supermarket.*;

import java.util.Date;

public class SpecialClassTestMain {
    public static void main(String[] args) {
        Date produceDate = new Date();
        Date expirationDate = new Date(System.currentTimeMillis() + 365L * 24 * 3600 * 1000);
        Phone phone = new Phone(
                "小米10", 10, 100, 3599, 0, Category.ELECTRIC,
                produceDate, expirationDate,
                6.67, 3.5, 8, 256, "小米", "MIUI", null);

        phone.describe();
        System.out.println();

        //在静态内部类外部的代码，就不可以访问其private的属性了。
        Phone.CPU cpu = new Phone.CPU(5.5, "AMD");
        //cpu.speed = 99;

        //将匿名类作为参数传递
        printSpec(new UnitSpec() {
            public int getNumSpec() { return 0; }
            public String getProducer() { return "Default"; }
        });

    }

    private static void printSpec (UnitSpec spec){
        System.out.println("Spec producer = " + spec.getProducer() + ". Num = " + spec.getNumSpec());
    }
}

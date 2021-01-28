import jdk.swing.interop.SwingInterOpUtils;
import supermarket.*;

//导入静态方法
import static supermarket.Merchandise.getDiscountOnDiscount;

public class RunLittleSuperMarketAppMainV3 {
    public static void main(String[] args) {
        // >> TODO 知识点：创建一个构造方法。简化main()中的逻辑
        LittleSuperMarket littleSuperMarket = new LittleSuperMarket("百伦广场","DJY大道211号",200,0.0);
        //将littleSuperMarket.merchandise这个引用赋给all，避免该长串字符的重复
        Merchandise[] all = littleSuperMarket.getMerchandise();


        System.out.println("超市开门啦！");
        System.out.println("本店叫做" + littleSuperMarket.getSuperMarketName());
        System.out.println("本店地址：" + littleSuperMarket.getAddress());
        System.out.println("共有停车位：" + littleSuperMarket.getParkingCount());
        System.out.println("今天的营业额:" + littleSuperMarket.getIncomingSum());
        System.out.println("共有商品:" + littleSuperMarket.getMerchandise().length + "种");

        System.out.println("LittleSuperMarket类的对象是：" + littleSuperMarket);
        Merchandise m = littleSuperMarket.getBiggestProfitMerchandise();

        System.out.println("下面请利润最高的商品做自我介绍：");
        m.describe();
        System.out.println("利润最高的Merchandise对象是：" + m);
        m.makeEnoughFor(500);
        //m.makeEnoughForOneByOne(500);

        // >> TODO 知识点：方法的重载
        double cost = m.buy(10);
        double costVip = m.buy(10,true);
        System.out.println("普通顾客购买10个" + m.getMerchandiseName() + "的费用为：" + cost);
        System.out.println("VIP顾客购买10个" + m.getMerchandiseName() + "的费用为：" + costVip);
        m.describe();

        // >> TODO 静态方法
        // >> TODO 静态方法也可重载，重载规则和普通方法、构造方法重载的规则一样
        System.out.println(getDiscountOnDiscount());
    }

    // >> TODO 静态变量被放在<clinit>方法中。
    public static double BASE_DISCOUNT;

    public static double VIP_DISCOUNT;

    // >> TODO 在main()外写一个静态代码块，java会在class（装载该.java文件到进程）初始化的时候先跑一遍
    static {
        BASE_DISCOUNT = 0.8;
        VIP_DISCOUNT = 0.75;
        System.out.println("静态代码块中VIP_DISCOUNT的值为：" + VIP_DISCOUNT);
    }
}

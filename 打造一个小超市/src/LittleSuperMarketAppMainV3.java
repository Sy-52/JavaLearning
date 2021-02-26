import supermarket.*;

//导入静态变量、方法
import static supermarket.Merchandise.DISCOUNT_FOR_VIP;
import static supermarket.Merchandise.getDiscountOnDiscount;

public class LittleSuperMarketAppMainV3 {
    public static void main(String[] args) {
        LittleSuperMarket littleSuperMarket = new LittleSuperMarket("百伦广场","DJY大道211号",200,0.0);
        Merchandise[] all = littleSuperMarket.getMerchandise();

        System.out.println();
        System.out.println("LittleSuperMarket类的对象是：" + littleSuperMarket);

        //下面这句就是父类的引用指向子类的实例
        Merchandise m = littleSuperMarket.getBiggestProfitMerchandise();
        System.out.println();
        System.out.println("下面请利润最高的商品做自我介绍：");
        //m.describe()中this自引用指向的是子类实例，所以会调子类实例的describe()
        m.describe();
        System.out.println();

        System.out.println("利润最高的Merchandise对象是：" + m);
        m.makeEnoughFor(500);
        //m.makeEnoughForOneByOne(500);
        System.out.println();

        // >> TODO 知识点：方法的重载
        //这里有一个坑没填。下面的cost为-2，costVip为17990.为何？
        double cost = m.buy(10);
        System.out.println();
        //下面这个方法我没有在Phone类中写override的方法，故而会去调用Merchandise类中的方法，导致购买成功。
        double costVip = m.buy(10,true);
        System.out.println();

        // >> TODO 知识点：多态。
        // >> TODO 下面三个describe()分别调用的是哪个类中的describe()？
        all[1].describe();
        all[10].describe();
        all[100].describe();
        System.out.println();

        // >> TODO 静态方法
        // >> TODO 静态方法也可重载，重载规则和普通方法、构造方法重载的规则一样
        System.out.println(getDiscountOnDiscount());
    }

    // >> TODO 知识点：静态变量。
    // >> TODO 静态变量被java放在<clinit>方法中，老早就初始化好可以用了。不依赖于实例的创建。
    public static double BASE_DISCOUNT;
    public static double VIP_DISCOUNT;

    // >> TODO 知识点：静态代码块。
    // >> TODO 在main()外写一个静态代码块，java会在class（装载该.java文件到进程）初始化的时候先跑一遍
    static {
        BASE_DISCOUNT = 0.8;
        VIP_DISCOUNT = 0.75;
        System.out.println("静态代码块中BASE_DISCOUNT的值为：" + BASE_DISCOUNT);
        System.out.println("静态代码块中VIP_DISCOUNT的值为：" + VIP_DISCOUNT);
        System.out.println("DISCOUNT_FOR_VIP的值为：" + DISCOUNT_FOR_VIP);
        System.out.println("getDiscountOnDisCount的返回值为：" + getDiscountOnDiscount());
    }
}

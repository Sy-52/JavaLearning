package supermarket;

// >> TODO 拿到一个问题，设计相应的java类的时候，不得不面对一个问题："XX(手机)是YY(手电筒)的一部分，还是只是组合了YY(可当手电的闪光灯)？"
// >> TODO 如果只是为了获得Merchandise类的属性和方法，那么在PhoneExtendsMerchandise的成员变量中再增加一个Merchandise类型的成员变量不就好了？（组合）
// >> TODO 但组合法有个问题，比如现在我要限制手机一次性只能买5个，那么如何用组合法？
// >>      TODO 1、你如果在PhoneExtendsMerchandise类中专门写一个限制方法，别人可以不用你这个方法，用getMerchandise().buy()绕过
// >>      TODO 2、如果你在用户输入做限制，那以为着每个买的地方你都要复制代码...并且如果突然又改成限制买10个，所有地方都要修改...
// >> TODO 所以，继承不是组合，并不是仅仅为了拿父类的属性和方法。与组合相比，继承更像是和父类"融合"，子类父类间互相影响。

public class PhoneExtendsMerchandise extends Merchandise{
    // >> TODO 知识点：继承
    // >> TODO 为什么要用继承？试想，如果把Merchandise类的成员变量复制到下面，如果Merchandise类以后增删了属性，那么还要到Phone这个子类来修改。
    // >> TODO 所以，直接用sub class去继承 parent class【所有的属性、方法】。

    private double screenSize;
    private double cpuHZ;
    private int memoryG;
    private int storageG;
    private String brand;
    private String os;

    public static double MAX_BUY_ONE_ORDER = 5;

    public PhoneExtendsMerchandise(
            String name, int Id, int count, double soldPrice, double purchasePrice, double screenSize,
            double cpuHZ, int memoryG, int storageG, String brand,String os){
        this.screenSize = screenSize;
        this.cpuHZ = cpuHZ;
        this.memoryG = memoryG;
        this.storageG = storageG;
        this.brand = brand;
        this.os = os;

        // >> TODO sub class并不能【直接】访问parent class的【private的属性和方法】。如下。
        this.setMerchandiseName(name);
        this.setMerchandiseId(Id);
        this.setMerchandiseCount(count);
        this.setMerchandiseSoldPrice(soldPrice);
        this.setMerchandisePurchasePrice(purchasePrice);
    }

    // >> TODO 知识点：覆盖(override)。覆盖才是继承的精髓。
    // >> TODO 子类使用和父类【方法签名一样、返回值也一样】的方法，可以让子类override掉父类的方法
    public double buy(int count){
        if(count > MAX_BUY_ONE_ORDER){
            System.out.println("抱歉！一次性最多只能购买" + MAX_BUY_ONE_ORDER + "个手机！");
            return -2;
        }
        if (this.getMerchandiseCount() < count){
            System.out.println("抱歉！库存不足！");
            return -1;
        }
        this.setMerchandiseCount(this.getMerchandiseCount() - count);
        double cost = count * this.getMerchandiseSoldPrice();
        System.out.println("购买成功！花费为：" + cost);
        return cost;
    }

    // >> TODO 知识点：多态 - 同一个方法，不同的行为
    // >> TODO 方法可以覆盖，本质上方法就是一段代码，一段逻辑。而属性只能读取，顾无法覆盖。
    // >> TODO 为什么推荐在方法中私有化成员变量，然后提供get/set方法？因为只有方法，才能覆盖，才有多态。
    public String getMerchandiseName(){
        return this.brand + ":" + this.os + ":" + this.getMerchandiseName();
    }
}

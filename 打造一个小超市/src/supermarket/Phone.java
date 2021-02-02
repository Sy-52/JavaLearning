package supermarket;

// >> TODO 拿到一个问题，设计相应的java类的时候，不得不面对一个问题："XX(手机)是YY(手电筒)的一部分，还是只是组合了YY(可当手电的闪光灯)？"
// >> TODO 如果只是为了获得Merchandise类的属性和方法，那么在PhoneExtendsMerchandise的成员变量中再增加一个Merchandise类型的成员变量不就好了？（组合）
// >> TODO 但组合法有个问题，比如现在我要限制手机一次性只能买5个，那么如何用组合法？
// >>      TODO 1、你如果在PhoneExtendsMerchandise类中专门写一个限制方法，别人可以不用你这个方法，用getMerchandise().buy()绕过
// >>      TODO 2、如果你在用户输入做限制，那以为着每个买的地方你都要复制代码...并且如果突然又改成限制买10个，所有地方都要修改...
// >> TODO 所以，继承不是组合，并不是仅仅为了拿父类的属性和方法。与组合相比，继承更像是和父类"融合"，子类父类间互相影响。

public class Phone extends Merchandise{
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

    public Phone(
            String name, int Id, int count, double soldPrice, double purchasePrice, double screenSize,
            double cpuHZ, int memoryG, int storageG, String brand,String os){
        //sub class并不能【直接】访问parent class的【private的属性和方法】，如下。
        //可以认为，创建子类对象的同时，也创建了一个隐藏的父类对象。所以我们才能setName，对name属性进行操作，如下
        //this.setMerchandiseName(name);this.setMerchandiseId(Id);this.setMerchandiseCount(count);
        //this.setMerchandiseSoldPrice(soldPrice);this.setMerchandisePurchasePrice(purchasePrice);
        // >> TODO 直接使用super调用父类的构造方法。和上述注释的语句是等价的。
        super(name, Id, count, soldPrice, purchasePrice);

        //将初始化的代码封装在一个init()中，在构造方法中调用。
        init(screenSize, cpuHZ, memoryG, storageG, brand, os);
    }

    public Phone(){
        //如果在main()中调用的是该无参的构造方法，java会默认隐式的调用super(),即父类中【程序员自己"显式"定义】的无参的构造方法，父类中没有则会出错。
        //若在父类中，程序员自己没有"显式"定义无参的构造方法，则在该子类的无参的构造方法中必须用super(参数1,参数2...)调用父类中写的重载的构造方法。
        super();
        init(4.5, 4.6, 6, 128, "Unknow", "Unknow");
    }

    public void init(double screenSize, double cpuHZ, int memoryG, int storageG, String brand,String os){
        this.screenSize = screenSize;
        this.cpuHZ = cpuHZ;
        this.memoryG = memoryG;
        this.storageG = storageG;
        this.brand = brand;
        this.os = os;
    }

    public void describe(){
        System.out.println("此商品的属性如下：");
        super.describe();
        System.out.println("手机厂商：" + this.brand + ";手机系统为:" + this.os + ";硬件配置如下:");
        System.out.println("屏幕:" + this.screenSize);
        System.out.println("CPU主频:" + this.screenSize + "GHZ");
        System.out.println("内存" + this.storageG + "Gb");
    }

    // >> TODO 知识点：覆盖(override)。覆盖才是继承的精髓。
    // >> TODO 子类使用和父类【方法签名一样、返回值也一样】的方法，可以让子类override掉父类的方法
    public double buy(int count){
        if(count > MAX_BUY_ONE_ORDER){
            System.out.println("抱歉！一次性最多只能购买" + (int)MAX_BUY_ONE_ORDER + "个手机！");
            return -2;
        }
        // >> TODO 直接调用父类的buy()，避免逻辑的重复
        return super.buy(count);
    }

    // >> TODO 知识点：多态 - 同一个方法，不同的行为
    // >> TODO 方法可以覆盖，本质上方法就是一段代码，一段逻辑。而属性只能读取，顾无法覆盖。
    // >> TODO 为什么推荐在方法中私有化成员变量，然后提供get/set方法？因为只有方法，才能覆盖，才有多态。
    public String getMerchandiseName(){
        // >> TODO 知识点：super
        // >> TODO 使用this.getMerchandiseName()会调用该方法本身，形成递归。而使用super则可以调用父类的【public的方法、属性】
        // >> TODO super是继承技术的一部分。它的用法就像是一个父类的引用，但super并不是父类的引用。
        //return this.brand + ":" + this.os + ":" + this.getMerchandiseName();
        return this.brand + ":" + this.os + ":" + super.getMerchandiseName();
    }


    //私有化成员变量，提供get、set方法
    public double getPhoneScreenSize(){return this.screenSize;}

    public void setPhoneScreenSize(double screenSize){this.screenSize = screenSize;}

    public double getPhoneCpuHZ(){return this.cpuHZ;}

    public void setPhoneCpuHZ(double cpuHZ){this.cpuHZ = cpuHZ;}

    public int getPhoneMemoryG(){return this.memoryG;}

    public void setPhoneMemoryG(int memoryG){this.memoryG = memoryG;}

    public int getPhoneStorageG(){return this.storageG;}

    public void setPhoneStorageG(double cpuHZ){this.storageG = storageG;}

    public String getPhoneBrand(){return this.brand;}

    public void setPhoneBrand(String brand){this.brand = brand;}

    public String getPhoneOs(){return this.os;}

    public void setPhoneOs(String os){this.os = os;}
}

package supermarket;

// >> TODO 拿到一个问题，设计相应的java类的时候，不得不面对一个问题："XX(手机)是YY(手电筒)的一部分，还是只是组合了YY(可当手电的闪光灯)？"
// >> TODO 如果只是为了获得Merchandise类的属性和方法，那么在Phone类的成员变量中再增加一个Merchandise类型的成员变量不就好了？（组合）
// >> TODO 但组合有个问题，比如现在我要限制手机一次性只能买5个，那么如何用组合法？
// >>      TODO 1、你如果在Phone类中专门写一个限制方法，别人可以不用你这个方法，用getMerchandise().buy()绕过
// >>      TODO 2、如果你在用户输入做限制，那以为着每个买的地方你都要复制代码...并且如果突然又改成限制买10个，所有地方都要修改。
// >> TODO 所以，继承不是组合，并不是仅仅为了拿父类的属性和方法。与组合相比，继承更像是和父类"融合"，子类父类间互相影响。

import java.util.Date;

public class Phone extends AbstractExpireDateMerchandise{
    // >> TODO 知识点：继承
    // >> TODO 为什么要用继承？试想，如果把Merchandise类的成员变量复制到下面，如果Merchandise类以后增删了属性，那么还要到Phone这个子类来修改。
    // >> TODO 所以，直接用子类去继承父类【所有的属性、方法】。

    private double screenSize;
    private CPU cpu;
    private int memoryG;
    private int storageG;
    private String brand;
    private String os = "安卓";
    // >> TODO 知识点：final修饰符
    //private final Merchandise gift;
    private Merchandise gift;

    // >> TODO 知识点：静态内部类（在类中使用static修饰的类，在继承、实现接口方面和普通类相同）
    // >> TODO 静态内部类和静态变量、静态方法一样，都是类的内部的静态组成部分之一。
    // >> TODO 静态内部类常用来实现单例模式。（利用java的类加载机制，在你用的时候再初始化静态实例，避免浪费）
    public static class CPU{
        private double speed;
        private String producer;

        public CPU(double speed, String producer){
            this.speed = speed;
            this.producer = producer;
        }
        // >> TODO 静态内部类中可以访问外部private的成员变量
        public double getSpeed(){
            //下面代码没有实际意义，仅演示可访问外部private的成员变量
            Phone phone = null;
            phone.memoryG = 256;
            return speed;
        }
        public void setSpeed(double speed){ this.speed = speed; }
        public String getProducer(){return producer;}
        public void setProducer(String producer){this.producer = producer;}

        @Override
        public String toString() {
            return "CPU{" +
                    "speed=" + speed +
                    ", producer='" + producer + '\'' +
                    '}';
        }

        //静态内部类中甚至可以再定义静态内部类....
    }

    // >> TODO 外部也可以访问静态内部类中private的成员变量
    public void accessStaticClass(){
        this.cpu.producer = "";
    }

    public Phone(){
        //如果在main()中调用的是该无参的构造方法，java会默认隐式的调用super(),即父类中【程序员自己"显式"定义】的无参的构造方法，父类中没有"显式"定义则会出错。
        //若在父类中，程序员自己没有"显式"定义无参的构造方法，则在该子类的无参的构造方法中必须用super(参数1,参数2...)调用父类中写的重载的构造方法。
        super();
        this.screenSize = 4.5;
        this.cpu = new CPU(5.5, "AMD");
        this.memoryG = 6;
        this.storageG = 128;
        this.brand = "Unknow";
        this.os = "Unknow";
        this.gift = null;
    }

    public Phone(
            String name, int Id, int count, double soldPrice, double purchasePrice, Category category, Date productDate, Date expirationDate, double screenSize,
            double cpuHZ, int memoryG, int storageG, String brand, String os, Merchandise gift){
        //子类并不能【直接】访问父类的【private的属性和方法】，如下。
        //可以认为，创建子类对象的同时，也创建了一个隐藏的父类对象。所以我们才能setName()，对name属性进行操作，如下
        //this.setName(name);this.setId(Id);this.setCount(count);this.setSoldPrice(soldPrice);this.setPurchasePrice(purchasePrice);
        // >> TODO 直接使用super调用父类的构造方法。和上述注释的语句是等价的。
        super(name, Id, count, soldPrice, purchasePrice,category, productDate, expirationDate);
        //将初始化的代码封装在一个init()中，在构造方法中调用。
        this.screenSize = screenSize;
        this.cpu = new CPU(cpuHZ, "骁龙865");
        this.memoryG = memoryG;
        this.storageG = storageG;
        this.brand = brand;
        this.os = os;
        this.gift = gift;
    }

    public void describe(){
        System.out.println("此商品的属性如下：");
        super.describe();
        System.out.println("手机厂商：" + this.brand + " ; 手机系统为:" + this.os +
                " ; 屏幕:" + this.screenSize + "寸 ; CPU信息:" + this.cpu  +
                " ; 内存:" + this.storageG + "Gb" + " ; 礼物:" + this.gift);
    }

    public double MAX_BUY_ONE_ORDER = 5;
    // >> TODO 知识点：覆盖(override)。覆盖才是继承的精髓。
    // >> TODO 子类使用和父类【方法签名一样、返回值也一样】的方法，可以让子类override掉父类的方法
    public double buy(int count){
        System.out.println("Phone类中的buy(int count)被调用了");
        if(count > MAX_BUY_ONE_ORDER){
            System.out.println("抱歉！一次性最多只能购买" + (int)MAX_BUY_ONE_ORDER + "个手机！");
            return -2;
        }
        // >> TODO 直接调用父类的buy()，避免逻辑的重复
        return super.buy(count);
    }

    // >> TODO 静态方法的"覆盖"要求签名相同，返回值也要相同。很像覆盖但却不是覆盖。
    public static void staticMethod(){
        System.out.println("staticMethod in Phone.");
    }

    @Override
    public String toString() {
        return "Phone{" +
                "name='" + super.getName() + '\'' +
                ", id=" + getId() +
                ", count=" + getCount() +
                ", soldPrice=" + getSoldPrice() +
                ", purchasePrice=" + getPurchasePrice() +
                ", screenSize=" + screenSize +
                ", cpuSpeed=" + cpu.speed +
                ", memoryG=" + memoryG +
                ", storageG=" + storageG +
                ", brand='" + brand + '\'' +
                ", os='" + os + '\'' +
                ", gift=" + gift +
                '}';
    }

    // >> TODO 知识点：多态（相同方法，不同行为）
    // >> TODO 方法可以覆盖，因为方法本质上是一段代码/一段逻辑；而属性只能读/取，顾无法覆盖。
    // >> TODO 为什么推荐在方法中私有化成员变量，然后提供get()/set()？因为只有方法才能覆盖，才有多态。
    public String getName(){
        // >> TODO 知识点：super
        // >> TODO 使用this.getName()会调用该方法本身，形成递归。而使用super则可以调用父类的【public的方法、属性】
        // >> TODO super是继承技术的一部分。它的用法就像是一个父类的引用，但super并不是父类的引用。
        //return this.brand + ":" + this.os + ":" + this.getName();
        return this.brand + ":" + this.os + ":" + super.getName();
    }

    public double actualValueNow(double leftDatePercentage){return getSoldPrice() * (leftDatePercentage + 0.5);};

    //私有化成员变量，提供get、set方法
    public double getScreenSize(){return this.screenSize;}

    public void setScreenSize(double screenSize){this.screenSize = screenSize;}

    public double getCpuSpeed(){return this.cpu.speed;}

    public void setCpuSpeed(double cpuHZ){this.cpu.speed = cpuHZ;}

    public int getMemoryG(){return this.memoryG;}

    public void setMemoryG(int memoryG){this.memoryG = memoryG;}

    public int getStorageG(){return this.storageG;}

    public void setStorageG(double cpuHZ){this.storageG = storageG;}

    public String getBrand(){return this.brand;}

    public void setBrand(String brand){this.brand = brand;}

    public String getOs(){return this.os;}

    public void setOs(String os){this.os = os;}

    public Merchandise getGift(){return this.gift;}

    public void setGift(Merchandise gift){this.gift = gift;}
}

// >> TODO 写一个非公有类Memory，与静态内部类的区别就在于能否访问类的private的成员变量
class Memory{
    private long capacity;
    private String producer;

    public Memory(long capacity, String producer){
        this.capacity = capacity;
        this.producer = producer;
    }

    public void test(){
        Phone phone = null;
        //下面这句报错
        //phone.screenSize = 9;
    }

    //提供get()、set()
    public long getCapacity() { return capacity; }
    public void setCapacity(long capacity) { this.capacity = capacity; }
    public String getProducer() { return producer; }
    public void setProducer(String producer) { this.producer = producer; }
}
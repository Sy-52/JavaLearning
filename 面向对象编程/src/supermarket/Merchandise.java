package supermarket;

import static supermarket.Category.*;

import java.util.Objects;

public class Merchandise {
    // >> TODO 成员变量赋初值的代码在java内部会变成<init>方法,即："所有的代码均在方法里".
    private String name;
    private int id;
    private int count;
    private double soldPrice;
    private double purchasePrice;
    private Category category;

    // >> TODO 知识点：构造方法
    // >> TODO 构造方法的方法名必须与类名一样，且没有返回值。（有返回值也没有意义，因为new操作符永远返回的是创建出来的对象的引用）
    // >> TODO 若没有显示的添加一个构造方法，java的每个类都会自己添加一个无参数的构造方法（ps:我们一直都在使用构造方法）
    // >> TODO 若我们自己添加了类构造方法，java就不会再添加无参数的构造方法了(即：我们不能直接new一个对象而不传递参数了)
    public Merchandise(String name, int id, int count, double soldPrice, double purchasePrice, Category category){
        this.name = name;
        this.id = id;
        this.count = count;
        this.soldPrice = soldPrice;
        this.purchasePrice = purchasePrice;
        this.category = category;
    }

    // >> TODO 知识点：构造方法的重载
    // >> TODO 在构造方法里，在【第一行用this(实参列表)】去调用重载的构造方法。
    // >> TODO 构造方法中不可自己调用自己、也不可使用成员变量。因为从语意上讲，这个对象还没有被初始化完成，处于中间状态。
    public Merchandise(String name, int id, int count, double soldPrice, Category category){
        this(name, id, count, soldPrice,soldPrice * 0.8,category);
    }

    public Merchandise(){
        this("空", 0, 0, 0, 0, FOOD);
    }

    // >> TODO 方法名可取任意合法的标识符。一个方法只能有一种返回值，用return语句返回方法的返回值。无需返回值则用void表示。（return、void为java中的关键字）
    //describle()，用以输出调用此方法的对象的基本信息。
    public void describe(){
        //describe()中可使用类的成员变量，也可使用自定义的局部变量。
        //若没有在该方法中定义类似soldPrice这种重名的局部变量，那么下面的soldPrive，java会默认其为this.soldPrice.
        double netIncome = soldPrice - purchasePrice;
        System.out.println("商品的类别是" + category.name() +  " ; 商品的名字叫做" + name + " ; id是" + id + " ; 商品的售价是" + soldPrice +
                " ; 商品的进价是" + purchasePrice + " ; 商品的净利润为" + netIncome + " ; 商品的库存为" + count);
    }

    public static String getNameOf(Merchandise m){
        return m.getName();
    }

    // >> TODO 返回值必须要能用来给返回值类型的变量赋值
    //calculateProfit()用以返回调用此方法的对象的净利润。
    public double calculateProfit(){
        double profit = this.soldPrice - this.purchasePrice;
        // >> TODO 知识点：return必须是所在代码块的最后一个语句，否则就会语法错误
        if(profit <= 0 ){
            return 0;
        }
        //这里秀波操作。若返回值为基本类型，低精度会自动往高精度转换
        return (int)profit;
    }

    // >> TODO 参数的定义格式为：类型名 + 标识符。countToBug叫halfPriceBuy()的形式参数。
    //halfPriceBuy()用以返回【在超市半价活动时】，客户购买的商品的总价
    public double halfPriceBuy(int countToBuy){
        //若商品库存不足，返回负数。
        if(countToBuy > this.count){
            return -1;
        }else{
            double totalCost = 0.0;
            int fullPriceCount = countToBuy/2 + countToBuy%2;
            int halfPriceCount = countToBuy - fullPriceCount;
            totalCost = this.soldPrice * fullPriceCount + (this.soldPrice/2 * halfPriceCount);
            return totalCost;
        }
    }

    // >> TODO 在一个方法可传递多个参数，且参数的类型可以各不相同。
    //halfPriceBuyAndPrintLeft()为halfPriceBuy()方法的进阶版。
    public double halfPriceBuyAndPrintLeft(int countToBuy, boolean printLeft){
        if(this.count < countToBuy){
            if(printLeft){
                System.out.println(this.name + "的剩余库存为：" + this.count);
            }
            return -1;
        }
        double totalCost = 0.0;
        int fullPriceCount = countToBuy/2 + countToBuy%2;
        int halfPriceCount = countToBuy - fullPriceCount;
        totalCost = this.soldPrice * fullPriceCount + (this.soldPrice/2 * halfPriceCount);
        this.count -= countToBuy;
        if(printLeft){
            System.out.println(this.name + "的剩余库存为：" + this.count);
        }
        return totalCost;
    }

    // >> TODO 方法中的形参可以是自己本身类的类型
    //totalValueBiggerThan()用于判断【调用该方法的商品的目前总价值】与【传入商品的目前总价值】的大小
    public boolean totalValueBiggerThan(Merchandise merchandise){
        //这里直接return而非用if...else，注意这个细节
        return this.count * this.purchasePrice < merchandise.getCount() * merchandise.getPurchasePrice();
    }

    // >> TODO 方法中的形参可以是任何类型，包括自定义类型LittleSuperMarket
    //isTheBiggestTotalValueOne()用于判断【调用该方法的商品的总利润】是否是【超市中所有商品】中最大的
    public boolean isTheBiggestTotalValueOne(LittleSuperMarket littleSuperMarket){
        double totalValue = this.count * this.calculateProfit();
        for(int i = 0; i < littleSuperMarket.getMerchandise().length; i++){
            // >> TODO 为什么能用littleSuperMarket.getMerchandise()[i]这种格式？因为有返回值的类方法，你可以把它当做一个成员变量来使用。故而可以使用上述格式。
            Merchandise m = littleSuperMarket.getMerchandise()[i];
            double nowTotalValue = m.getCount() * m.calculateProfit();
            if(nowTotalValue > totalValue){
                return false;
            }
        }
        return true;
    }

    // >> TODO 知识点：方法的重载
    // >> TODO 方法签名(方法名 + 依次参数类型)是一个方法在一个类中的唯一标识。
    // >> TODO 若一个类中定义了名字相同，方法签名不同的方法，就叫做方法的重载。
    // >> TODO 注：返回值不算是方法签名，重载的方法可以有完全不同的返回值类型
    public double buy(int count, boolean isVIP){
        System.out.println("Merchandise类中的buy(int count,boolean isVIP)被调用了");
        if(this.count < count){
            System.out.println("抱歉！库存不足！");
            return -1;
        }
        System.out.println("恭喜您！购买成功！");
        this.count -= count;
        double totalCost = count * this.soldPrice;
        if(isVIP){
            return totalCost * DISCOUNT_FOR_VIP;
        }else{
            return totalCost;
        }
    }

    public double buy(int count){
        System.out.println("Merchandise类中的buy(int count)被调用了");
        return buy(count,false);
    }

    // >> TODO 重载的方法可以调用别的重载方法，也可以调用其他的非重载的方法
    public double buy(){return buy(1);}

    public double buy(boolean reallyBuy){
        System.out.println("Merchandise类中的buy(boolean reallyBuy)被调用了");
        if(reallyBuy){
            return this.buy(1);
        }else{
            return -1;
        }
    }

    // >> TODO 知识点：封装。将补充库存的逻辑封装在了makeEnoughFor()中
    public void makeEnoughFor(int count){
        System.out.println("Merchandise类的makeEnoughFor方法使用的对象是：" + this);
        // >> TODO 知识点：在同一个类的对象上调用同一个类的不同方法，如下面的hasEnoughCountFor()
        boolean hasEnough = hasEnoughCountFor(count);
        if(!hasEnough){
            int toBeAdded = count - this.count;
            addCount(toBeAdded);
        }
    }

    //hasEnoughCountFor()用以检查【调用方法的商品的库存】是否足够
    public boolean hasEnoughCountFor(int count){
        System.out.println("Merchandise类的hasEnoughCountFor方法使用的对象是：" + this);
        return this.count >= count;
    }

    //addCount()用以为【调用方法的商品】添加库存
    public void addCount(int count){
        System.out.println("Merchandise类的addCount方法使用的对象是：" + this);
        this.count += count;
        System.out.println("补充库存！库存剩余数量为：" + this.count);
    }

    //makeEnoughFor()的升级版，模拟工人一个接一个搬商品进库存的过程
    public void makeEnoughForOneByOne(int count){
        boolean hasEnough = hasEnoughCountFor(count);
        if(!hasEnough){
            addCount(1);
            //在类的方法中调用自己，递归
            makeEnoughForOneByOne(count);
        }
    }

    // >> TODO 知识点：静态变量。
    // >> TODO 命名规范通常为【全大写 + 下划线分割】，不要使用没有名字、不知道其干啥的数字。
    // >> TODO 注：下面的两个静态变量为所有包中的所有类公用。
    public static String STATIC_MEMBER = "Common Merchandise";
    public static double DISCOUNT_FOR_VIP = 0.9;

    // >> TODO 知识点：静态方法
    // >> TODO 静态方法没有this自引用，故不能【直接】访问成员变量.但是可以在静态方法中自己创建对象/通过传入对象的引用来间接的访问成员变量
    public static double getDiscountOnDiscount(){
        return DISCOUNT_FOR_VIP;
    }

    public static void staticMethod(){
        System.out.println("staticMethod in Merchandise.");
    }

    // >> TODO 其实用 + 做字符串拼接时，已经调用了toString()返回一个字符串拼起来
    @Override
    public String toString() {
        return "Merchandise{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", count=" + count +
                ", soldPrice=" + soldPrice +
                ", purchasePrice=" + purchasePrice +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Merchandise)) return false;
        Merchandise that = (Merchandise) o;
        return getId() == that.getId() &&
                getCount() == that.getCount() &&
                Double.compare(that.getSoldPrice(), getSoldPrice()) == 0 &&
                Double.compare(that.getPurchasePrice(), getPurchasePrice()) == 0 &&
                getName().equals(that.getName());
    }

    // >> TODO 哈希码的生成已经有一套相对标准的过程了，程序会综合考虑传入的参数生成一个哈希码。让IDE帮我们生成即可。
    @Override
    public int hashCode() {
        return Objects.hash(getName(), getId(), getCount(), getSoldPrice(), getPurchasePrice());
    }

    //封装的成员变量的get、set方法
    public String getName(){return this.name;}

    public void setName(String name){this.name = name;}

    public int getId(){return this.id;}

    public void setId(int id){this.id = id;}

    public int getCount(){return this.count;}

    public void setCount(int count){this.count = count;}

    public double getSoldPrice(){return this.soldPrice;}

    public void setSoldPrice(double soldPrice){this.soldPrice = soldPrice;}

    public double getPurchasePrice(){return this.purchasePrice;}

    public void setPurchasePrice(double purchasePrice){this.purchasePrice = purchasePrice;}
}

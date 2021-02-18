package supermarket;

import java.util.Date;

import static supermarket.Category.*;

public class LittleSuperMarket {
    //私有化LittleSuperMarket类的所有成员变量
    private String superMarketName;
    private String address;
    private int parkingCount;
    private double incomingSum;
    // >> TODO 知识点：final修饰符
    //private final Merchandise[] merchandise;
    private Merchandise[] merchandise;
    private int[] merchandiseSold;

    public LittleSuperMarket(String superMarketName, String address, int parkingCount, double incomingSum){
        this.superMarketName = superMarketName;
        this.address = address;
        this.parkingCount = parkingCount;
        this.incomingSum = incomingSum;
        this.merchandise = new Merchandise[200];
        this.merchandiseSold = new int[this.merchandise.length];

        Date productDate = new Date();
        Date expireDate = new Date(productDate.getTime() + 365L * 24 * 3600 * 1000);
        Merchandise giftForPhone = new Merchandise("手机赠品-64g内存卡", 2, 999, 60, 30,ELECTRIC);;

        //初始化200件商品，并对每10件、每100件商品做特殊赋值
        for(int i = 0; i < this.merchandise.length; i++){
            /*
                以下为老式初始化的注释：
                这里先创建一个Merchandise类的实例m再赋值给all[i]的原因为：
                new Merchandise[200]仅意味着创建了一个长度为200的Merchandise类型的数组。
                里面的数组元素littleSuperMarket.merchandise[i]的值仍然为null.
                所以如果直接用all[i].count = 200去给成员变量赋值的话，会报NPE
            */
            if(i > 0 && i % 100 == 0){
                //giftForPhone = new Merchandise("手机赠品-128g内存卡", 1, 999, 120, 60);
                this.merchandise[i] = new ShellColorChangePhone(
                        "手机" + i, i, 200, 1999, 999, ELECTRIC, productDate, expireDate,4.5, 3.5,
                        4, 128, "三星change", "Android", giftForPhone,false
                );
            }else if(i > 0 && i % 10 == 0){
                this.merchandise[i] = new Phone(
                        "手机" + i, i, 200, 1999, 999,  ELECTRIC,productDate, expireDate,4.5, 3.5,
                        4, 128, "索尼normal", "Android",giftForPhone
                );
            }else {
                this.merchandise[i] = new Merchandise("商品" + i, i, 200, (1 + Math.random()) * 200, Math.random() * 200, FOOD);
            }
        }
    }

    //getBiggestProfitMerchandise()用以返回LittleSuperMarket.merchandise[]中净利润最高的商品的引用。该方法的返回值为Merchandise类型。
    public Merchandise getBiggestProfitMerchandise(){
        // >> TODO 知识点：this自引用指向的是调用该方法的对象(保存的是该对象的地址）
        System.out.println("LittleSuperMarket类的getBiggestProfitMerchandise方法使用的对象是：" + this);
        Merchandise curr = null;
        // >> TODO 知识点：下面为什么能用private修饰的merchandise而不报错？
        // >> TODO 知识点: private修饰的成员变量只有该类能访问；缺省的访问修饰符修饰的成员变量只有包中的类能访问;public修饰的就不多赘述了
        for(int i = 0; i < merchandise.length; i++){
            Merchandise m = merchandise[i];
            //若超市只卖一种商品，做如下处理
            if(curr == null){
                curr = m;
            }else{
                if(m.calculateProfit() > curr.calculateProfit()){
                    curr = m;
                }
            }
        }
        return curr;
    }

    // >> TODO for循环的另一种写法：适用于只循环遍历。不赋值、不跳跃访问、不需要知道当前的元素是第几个的情况
    public double getBiggestPurchasePrice(){
        double maxPurchasePrice = -1;
        for(Merchandise m : this.merchandise){
            if(m.getPurchasePrice() > maxPurchasePrice){
                maxPurchasePrice = m.getPurchasePrice();
            }
        }
        return maxPurchasePrice;
    }

    public boolean findMerchandise(Merchandise target){
        int i = 0;
        for(Merchandise m : this.merchandise){
            boolean match = m.equals(target);
            //如果用 == 来比较的话，那么只要两个引用指向同一个实例，则返回true.
            //boolean match = (m == target);
            if(match){
                System.out.println("找到了商品，位置在:" + i);
                return true;
            }
            i++;
        }
        return false;
    }

    //对封装的成员变量提供get、set方法
    public String getSuperMarketName(){return this.superMarketName;}

    public void setSuperMarketName(String name){this.superMarketName = name;}

    public String getAddress(){return this.address;}

    public void setAddress(String address){this.address = address;}

    public int getParkingCount(){return this.parkingCount;}

    public void setParkingCount(int parkingCount){this.parkingCount = parkingCount;}

    public double getIncomingSum(){return this.incomingSum;}

    public void setIncomingSum(double incomingSum){this.incomingSum = incomingSum;}

    public Merchandise[] getMerchandise(){return this.merchandise;}

    public int[] getMerchandiseSold(){return this.merchandiseSold;}
}

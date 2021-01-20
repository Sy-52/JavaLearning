package supermarket;

public class LittleSuperMarket {
    public String superMarketName;
    public String address;
    public int parkingCount;
    public double incomingSum;
    //下面这句用Merchandise[]创建了一个类型为Merchandise的数组，表示有多少种商品
    public Merchandise[] merchandise;
    //对应上方的merchandise数组，表示每种商品分别卖了多少个
    public int[] merchandiseSold;
}

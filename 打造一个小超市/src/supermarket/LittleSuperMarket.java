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

    //计算利润最高的商品
    public Merchandise getBiggestProfitMerchandise(){
        double profit = 0.0;
        Merchandise m = new Merchandise();
        for(int i = 0; i < merchandise.length; i++){
            if(profit < (merchandise[i].soldPrice - merchandise[i].purchasePrice)){
                profit = merchandise[i].soldPrice - merchandise[i].purchasePrice;
                m = merchandise[i];
            }
        }
        return m;
    }
}

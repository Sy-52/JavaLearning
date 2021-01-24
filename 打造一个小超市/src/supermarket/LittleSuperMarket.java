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

    // >>TODO getBiggestProfitMerchandise()用以返回【超市中所有商品里净利润最高的商品】的引用。该方法的返回值为Merchandise()类型。
    public Merchandise getBiggestProfitMerchandise(){
        Merchandise curr = null;
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
}

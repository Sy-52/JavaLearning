package supermarket;

public class LittleSuperMarket {
    public String superMarketName;
    public String address;
    public int parkingCount;
    public double incomingSum;
    public Merchandise[] merchandise;
    //merchandiseSold表明每种商品分别卖了多少个
    public int[] merchandiseSold;

    // >> TODO 构造方法的方法名必须与类名一样，且没有返回值。（有返回值也没有意义，因为new操作符永远返回的是创建出来的对象的引用）
    // >> TODO 若没有显示的添加一个构造方法，java的每个类都会自带一个无参数的构造方法，所以我们一直都在使用构造方法。
    // >> TODO 若我们自己添加了类构造方法，java就不会再添加无参数的构造方法了，即：我们不能直接new一个对象而不传递参数了
    public LittleSuperMarket(String superMarketName, String address, int parkingCount, double incomingSum){
        this.superMarketName = superMarketName;
        this.address = address;
        this.parkingCount = parkingCount;
        this.incomingSum = incomingSum;
    }

    //getBiggestProfitMerchandise()用以返回【超市中所有商品里净利润最高的商品】的引用。该方法的返回值为Merchandise()类型。
    public Merchandise getBiggestProfitMerchandise(){
        // >> TODO 知识点：this自引用指向的是调用该方法的对象(保存的是该对象的地址）
        System.out.println("LittleSuperMarket类的getBiggestProfitMerchandise方法使用的对象是：" + this);
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

package supermarket;

import org.w3c.dom.ls.LSOutput;

public class Merchandise {
    public String name;
    public int id;
    //count:商品的库存
    public int count;
    //soldPrice:售价；purchasePrice:进价
    public double soldPrice;
    public double purchasePrice;

    // >> TODO describle()，用以输出调用此方法的对象的基本信息。
    // >> TODO 一个方法只能有一种返回值，用return语句返回方法的返回值。无需返回值则用void表示。（return、void为java中的关键字）
    // >> TODO 方法名：任意合法的标识符
    public void describe(){
        //netIncome为方法中定义的局部变量。describe方法中可使用类的成员变量，也可使用自定义的局部变量
        double netIncome = soldPrice - purchasePrice;
        System.out.println("商品的名字叫做：" + name + ".id是" + id + ".商品的售价是" + soldPrice +
                ".商品的进价是" + purchasePrice + ".商品的净利润为" + netIncome + ".商品的库存为" + count);
    }

    // >> TODO calculateProfit()用以返回调用此方法的对象的净利润。该方法的返回值为基础数据类型。
    // >> TODO 返回值必须要能用来给返回值类型的变量赋值
    public double calculateProfit(){
        double profit = soldPrice - purchasePrice;
        //return必须是所在代码块的最后一个语句，否则就会语法错误
        if(profit <= 0 ){
            return 0;
        }
        //这里秀波操作。若返回值为基本类型，低精度会自动往高精度转换
        return (int)profit;
    }

    // >> TODO buy()用以返回【在超市半价活动时】，客户购买的商品的总价
    // >> TODO 若返回值为负数，则代表商品库存不足
    // >> TODO 参数的定义格式为：类型名 + 标识符。countToBug叫形式参数。
    public double halfPriceBuy(int countToBuy){
        if(countToBuy > count){
            return -1;
        }else{
            double totalCost = 0.0;
            int fullPriceCount = countToBuy/2 + countToBuy%2;
            int halfPriceCount = countToBuy - fullPriceCount;
            totalCost = soldPrice * fullPriceCount + (soldPrice/2 * halfPriceCount);
            return totalCost;
        }
    }

    // >> TODO halfPriceBuyAndPrintLeft()为halfPriceBuy()方法的进阶版。
    public double halfPriceBuyAndPrintLeft(int countToBuy, boolean printLeft){
        if(count < countToBuy){
            if(printLeft){
                System.out.println(name + "的剩余库存为：" + count);
            }
            return -1;
        }
        double totalCost = 0.0;
        int fullPriceCount = countToBuy/2 + countToBuy%2;
        int halfPriceCount = countToBuy - fullPriceCount;
        totalCost = soldPrice * fullPriceCount + (soldPrice/2 * halfPriceCount);
        count -= countToBuy;
        if(printLeft){
            System.out.println(name + "的剩余库存为：" + count);
        }
        return totalCost;
    }

    // >> TODO totalValueBiggerThan()用于判断【调用该方法的商品的目前总价值】与【传入的商品的目前总价值】的大小
    public boolean totalValueBiggerThan(Merchandise merchandise){
        //这里直接return而非用if...else，注意这个小技巧
        return count * purchasePrice < merchandise.count * merchandise.purchasePrice;
    }

    // >> TODO isTheBiggestTotalValueOne()用于判断【调用该方法的商品的总利润】是否是】超市中所有商品】中最大的
    public boolean isTheBiggestTotalValueOne(LittleSuperMarket littleSuperMarket){
        double totalValue = count * this.calculateProfit();
        for(int i = 0; i < littleSuperMarket.merchandise.length; i++){
            Merchandise m = littleSuperMarket.merchandise[i];
            double nowTotalValue = m.count * m.calculateProfit();
            if(nowTotalValue > totalValue){
                return false;
            }
        }
        return true;
    }

    //hasEnoughCountFor()用以检查调用方法的商品的库存是否足够
    public boolean hasEnoughCountFor(int count){
        System.out.println("Merchandise类的hasEnoughCountFor方法使用的对象是：" + this);
        return this.count > count;
    }

    //addCount()用以在调用该方法的对象的成员变量上添加库存
    public void addCount(int count){
        System.out.println("Merchandise类的addCount方法使用的对象是：" + this);
        this.count += count;
        System.out.println("补充库存！库存剩余数量为：" + this.count);
    }
}

package supermarket;

public class Merchandise {
    public String name;
    public int id;
    //count:商品的库存
    public int count;
    //soldPrice:售价；purchasePrice:进价
    public double soldPrice;
    public double purchasePrice;
    // >> TODO 一个方法只能有一种返回值，用return语句返回方法的返回值。无需返回值则用void表示。（return、void为java中的关键字）
    // >> TODO 方法名：任意合法的标识符
    public void describe(){
        //netIncome为方法中定义的局部变量。describe方法中可使用类的成员变量，也可使用自定义的局部变量
        double netIncome = soldPrice - purchasePrice;
        System.out.println("商品的名字叫做：" + name + ".id是" + id + ".商品的售价是" + soldPrice +
                ".商品的进价是" + purchasePrice + "商品的库存为" + count);
    }

    // >> TODO 返回值必须要能够用来给返回值类型的变量赋值
    public double calculateProfit(){
        double profit = soldPrice - purchasePrice;
        //return必须是所在代码块的最后一个语句，否则就会语法错误
        if(profit <= 0 ){
            return 0;
        }
        //这里秀波操作。若返回值为基本类型，低精度会自动往高精度转换
        return (int)profit;
    }
}

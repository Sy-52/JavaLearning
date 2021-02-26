package supermarket;

import java.util.Date;

// >> TODO 抽象类可以继承别的类/抽象类/实现接口
// >> TODO 抽象类中可以有：实现接口的方法；自己定义的方法、抽象方法。
// >> TODO 抽象类不可被实例化。（后面用匿名类实例化了... = = ）
public abstract class AbstractExpireDateMerchandise extends Merchandise implements ExpirationDate {
    private Date productDate;
    private Date expirationDate;

    // >> TODO 这里有一个小知识点，为何抽象类不可被实例化却还要写构造方法？
    public AbstractExpireDateMerchandise(){ super(); }

    public AbstractExpireDateMerchandise(String name, int id, int count, double soldPrice, double purchasePrice, Category category, Date productDate, Date expirationDate){
        super(name, id, count, soldPrice, purchasePrice, Category.ELECTRIC);
        this.productDate = productDate;
        this.expirationDate = expirationDate;
    }

    // >> TODO 下面注释掉的代码是课程中，用抽象类去实现java8前的接口中的方法。
    /*
    // >> TODO @是java中的注解(annotation)，属于Java源代码中的一部分
    // >> TODO @Override表明下面的方法实现/覆盖了接口的方法(java会进行检测、报错)
    @Override
    public boolean notExpireInDays(int days) { return daysBeforeExpire() > days; }

    public double leftDatePercentage() {
        return (1.0 * daysBeforeExpire())/(daysBeforeExpire() + daysAfterProduce());
    }

    // >> TODO 编程习惯 -- 将这两个方法设置为private，就可以随意改（不用考虑其他人会使用）。
   private long daysBeforeExpire(){
        return daysBetween(System.currentTimeMillis(), this.getExpirationDate().getTime());
    }

    private long daysAfterProduce(){
        return daysBetween(this.getProductDate().getTime(), System.currentTimeMillis());
    }

    public static long daysBetween(long from, long to){
        long gap = to - from;
        if(gap < 0){return -1;}
        return gap;
    }
    */

    public Date getProductDate() { return this.productDate; }

    public Date getExpirationDate() { return this.expirationDate; }

    // >> TODO 因为接口中没有getSoldPrice()，所以无法直接在接口中使用this.getSoldPrice()，只能在实现接口的类中去分别实现actualValueNow()。
    // >> TODO 而抽象类则可以把actualValueNow()给抽象出来。（其实点卡、手机的算实际价值的逻辑是一样的，应该抽象出来）
    public double actualValueNow(double leftDatePercentage) { return leftDatePercentage * this.getSoldPrice(); }

    // >> TODO 抽象类中可以自己定义抽象方法（protected/缺省)。而普通类中不能定义abstract类型的方法
    //protected abstract void test();
}

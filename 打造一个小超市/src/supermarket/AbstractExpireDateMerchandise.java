package supermarket;

import java.util.Date;

// >> TODO 抽象类可以继承别的类/抽象类/实现接口
// >> TODO 抽象类中可以有：实现接口的方法；自己定义的方法。也可不定义任何抽象方法
// >> TODO 抽象类不可被实例化。
public abstract class AbstractExpireDateMerchandise extends Merchandise implements ExpireDateMerchandise{
    private Date productDate;
    private Date expirationDate;

    // >> TODO 思考：为何抽象类不可被实例化却还要写构造方法？
    public AbstractExpireDateMerchandise(){
        super();
    }

    public AbstractExpireDateMerchandise(String name, int id, int count, double soldPrice, double purchasePrice, Category category, Date productDate, Date expirationDate){
        super(name, id, count, soldPrice, purchasePrice, Category.ELECTRIC);
        this.productDate = productDate;
        this.expirationDate = expirationDate;
    }

    // >> TODO @是java中的注解(annotation)，属于Java源代码中的一部分
    // >> TODO @Override表明下面的方法实现/覆盖了接口的方法(java会进行检测、报错)
    @Override
    public boolean notExpireInDays(int days) { return daysBeforeExpire() > days; }

    public double leftDatePercentage() {
        return (1.0 * daysBeforeExpire())/(this.getExpireDate().getTime() - this.getProductDate().getTime());
    }

    // >> TODO 编程习惯 -- 将这两个方法设置为private，就可以随意改（没有其他人使用）。
    private long daysBeforeExpire(){
        long expireMS = getExpireDate().getTime();
        long left = expireMS - System.currentTimeMillis();
        if(left < 0){return -1;}
        return left/(24 * 3600 * 1000);
    }

    private long daysAfterProduce(){
        long produceMS = getProductDate().getTime();
        long left = System.currentTimeMillis() - produceMS;
        if(left < 0){return -1;}
        return left/(24 * 3600 * 1000);
    }

    public Date getProductDate() { return productDate; }

    public Date getExpireDate() { return expirationDate; }

    // >> TODO 抽象类中可以自己定义抽象方法，可以是protected/缺省。而普通类中不能定义abstract类型的方法
    //protected abstract void test();
}

package supermarket;

import java.util.Date;

// >> TODO implements了有缺省方法的接口后，面对每个缺省的方法，一个类有三个选择：
// >> TODO 1）默默继承
// >> TODO 2）覆盖，重新实现
// >> TODO 3）如果是抽象类，可以重新声明此方法为abstract，使该抽象类的子类无法跳过此abstract的方法来继承接口中的缺省方法。
public class GamePointCard extends Merchandise implements ExpireDateMerchandise, VirtualMerchandise{

    private Date productDate;
    private Date expirationDate;

    public GamePointCard(String name, int id, int count, double soldPrice, double purchasePrice, Category category, Date productDate, Date expirationDate){
        super(name, id, count, soldPrice, purchasePrice, Category.ELECTRIC);
        this.productDate = productDate;
        this.expirationDate = expirationDate;
    }

    public Date getProductDate(){return this.productDate;}

    public Date getExpireDate(){return this.expirationDate;}

    public double actualValueNow(double leftDatePercentage) { return getSoldPrice(); }
}

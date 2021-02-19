package supermarket;

import java.util.Date;

// >> TODO 若一个类implements了有缺省方法的接口后，面对每个缺省的方法，一个类有三个选择：
// >> TODO 1）默默继承
// >> TODO 2）覆盖，重新实现
// >> TODO 3）如果是抽象类继承了接口，可以重新声明此方法为abstract，使该抽象类的子类无法跳过抽象类中的此抽象方法来继承接口中的缺省方法。
public class GamePointCard extends Merchandise implements ExpirationDate, VirtualMerchandise{

    private Date productDate;
    private Date expirationDate;

    public GamePointCard(String name, int id, int count, double soldPrice, double purchasePrice, Category category, Date productDate, Date expirationDate){
        super(name, id, count, soldPrice, purchasePrice, Category.ELECTRIC);
        this.productDate = productDate;
        this.expirationDate = expirationDate;
    }

    public double actualValueNow(double leftDatePercentage) { return leftDatePercentage * this.getSoldPrice(); }

    //提供get、set方法/也是对接口中抽象方法的实现。
    public Date getProductDate(){return this.productDate;}

    public Date getExpirationDate(){return this.expirationDate;}
}

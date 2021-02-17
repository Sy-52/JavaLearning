package supermarket;

import java.util.Date;

// >> TODO 可以让接口的引用指向一个实现了接口的实例，从而调用接口定义的方法
public class GamePointCard extends Merchandise implements ExpireDateMerchandise, VirtualMerchandise{

    private Date productDate;
    private Date expirationDate;

    public GamePointCard(String name, int id, int count, double soldPrice, double purchasePrice, Category category, Date productDate, Date expirationDate){
        super(name, id, count, soldPrice, purchasePrice, Category.ELECTRIC);
        this.productDate = productDate;
        this.expirationDate = expirationDate;
    }

    public boolean notExpireInDays(int days) { return daysBeforeExpire() > days; }

    public Date getProductDate() { return productDate; }

    public Date getExpireDate() { return expirationDate; }

    public double leftDatePercentage() {
        return (1.0 * daysBeforeExpire())/(expirationDate.getTime() - productDate.getTime());
    }

    public double actualValueNow(double leftDatePercentage) { return getSoldPrice(); }

    public long daysBeforeExpire(){
        long expireMS = expirationDate.getTime();
        long left = expireMS - System.currentTimeMillis();
        if(left < 0){return -1;}
        return left/(24 * 3600 * 1000);
    }
}

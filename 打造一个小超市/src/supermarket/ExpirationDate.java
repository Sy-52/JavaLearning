package supermarket;

import java.util.Date;

// >> TODO 接口只是定义了一个规范，无法被实例化。
// >> TODO 有方法的接口，并不是多继承。接口只能继承接口而不能继承类，所以无论你implements多少接口，都不会继承任何成员变量，只能获得一些方法，仅此而已。
// >> TODO 接口和抽象类，最大的区别就是，接口中不能声明实例变量。接口只能间接通过实现接口的类来获取数据。
public interface ExpirationDate {

    // >> TODO java8中，允许有【缺省实现的抽象方法】，用default修饰，可以有方法体。
    // >> TODO 缺省的方法中也有this自引用，但这个this自引用指向的是实现了该接口的类的实例。
    /**
     * 截止到当前，商品的保质期天数是否超过传递的天数
     * @param days 截止到当前，保质期超过这么多天
     * @return 截止到当前，true如果保质期剩余天数比参数长，false如果保质期不到这么多天
     */
    default boolean notExpireInDays(int days){
        return daysBeforeExpire() > days;
    };

    /**
     * @return 截止到当前，剩余保质期还剩下总保质期长度的百分比
     */
    default double leftDatePercentage(){
        return (1.0 * daysBeforeExpire())/(getExpirationDate().getTime() - getProductDate().getTime());
    };

    // >> TODO java8中,接口中可以有【私有方法】，不需要用default修饰
    private long daysBeforeExpire(){
        return daysBetween(System.currentTimeMillis(), this.getExpirationDate().getTime());
    }

    private long daysAfterProduce(){
        return daysBetween(this.getProductDate().getTime(), System.currentTimeMillis());
    }

    // >> TODO 接口中可以有静态方法，不需要用default修饰。可以被实现这个接口的类继承.
    public static long daysBetween(long from, long to){
        long gap = to - from;
        if(gap < 0){return -1;}
        return gap;
    }

    /**
     * @return 生产日期
     */
    Date getProductDate();

    /**
     * @return 过期日期
     */
    Date getExpirationDate();

    /**
     * 根据剩余的有效百分比，得出商品现在实际的价值
     * @param leftDatePercentage 剩余有效百分比
     * @return 剩余的实际价值
     */
    // >> TODO java8前，接口中的方法都是抽象方法，默认为public abstract（只定义方法签名、返回值，不定义方法体）。
    public abstract double actualValueNow(double leftDatePercentage);

    // >> TODO 接口里无法定义局部变量，变量默认为public static final.（写不写都一样）
    // >> TODO 接口中不定义局部变量，因为不会创建它的实例；创建它的实例也没有意义，因为没有方法。
    public static final int VAL_IN_INTERFACE = 999;
}

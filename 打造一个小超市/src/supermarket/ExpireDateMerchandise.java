package supermarket;

import java.util.Date;

// >> TODO 接口中只定义方法签名、返回值，不定义方法体。即接口中的方法都是抽象方法，默认为public abstract.（写不写都一样）
// >> TODO 接口只是定义了一个规范，无法被实例化。
// >> TODO 接口注释最好写一下
public interface ExpireDateMerchandise {
    /**
     * 截止到当前，商品的保质期天数是否超过传递的天数
     * @param days 截止到当前，保质期超过这么多天
     * @return 截止到当前，true如果保质期剩余天数比参数长，false如果保质期不到这么多天
     */
    boolean notExpireInDays(int days);

    /**
     * @return 生产日期
     */
    Date getProductDate();

    /**
     * @return 过期日期
     */
    public abstract Date getExpireDate();

    /**
     * @return 截止到当前，剩余保质期还剩下总保质期长度的百分比
     */
    public abstract double leftDatePercentage();

    /**
     * 根据剩余的有效百分比，得出商品现在实际的价值
     * @param leftDatePercentage 剩余有效百分比
     * @return 剩余的实际价值
     */
    public abstract double actualValueNow(double leftDatePercentage);

    // >> TODO 接口里不能定义局部变量，变量默认为public static final.（写不写都一样）
    // >> TODO 接口中不定义局部变量，因为不会创建它的实例；创建它的实例也没有意义，因为没有方法。
    public static final int VAL_IN_INTERFACE = 999;
}

package interfaces;

import impI.Category;
import impI.ShoppingCart;

public interface Customer {

    String getCusId();

    /**
     * @return 顾客消费的总金额
     */
    double getMoneySpent();

    /**
     * 顾客购物前，将其想逛的种类数guangCount赋值给计数器guangLeft
     */
    void startShopping();

    /**
     * @return 顾客购物时，第一次返回顾客提前想采购的商品的种类；其余情况返回随机的品类
     */
    Category chooseCategory();

    /**
     * 导购推荐一种商品，顾客到底买不买？
     * @param merchandise 导购推荐的商品
     * @return 对于随缘顾客和带有明确目的的客户，返回值情况不同。
     */
    int buyJudge(Merchandise merchandise);

    /**
     * 顾客还是否继续逛？（默认逛5次）
     * @return true：顾客该去结账了， false：顾客留下来继续逛
     */
    boolean wantToCheckout();

    /**
     * 付款
     * @param shoppingCart 顾客此次购物的购物车
     * @param totalCost 经过超市折扣策略打折后的总价格
     * @return 支付成功：返回支付的钱，支付失败；返回-1
     */
    double payFor(ShoppingCart shoppingCart, double totalCost);
}

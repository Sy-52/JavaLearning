package interfaces;// >> TODO 某一个种类的商品，满多少减多少
// >> TODO 某一个种类的商品，第二件半价

import impI.ShoppingCart;

// >> TODO 某一种类的商品，满减
// >> TODO 某一种类的商品，第二件半价

public interface DiscountStrategy {
    /**
     * @param shoppingCart
     * @return 经过超市的折扣策略所【扣掉的钱】
     */
    double discount(ShoppingCart shoppingCart);
}

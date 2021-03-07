package interfaces;

import impI.*;

public interface Card {
    /**
     * @param totalCost 顾客所要购买的商品的原价之和
     * @param totalCostAfterDiscount 顾客在经过上一轮优惠后（打折策略后、VIP卡后、打折卡后、赠品卡后...），这一轮实际需要支付的钱
     * @return 优惠的金额，非优惠后需要支付的价格
     */
    //>> TODO 知识点：类似于电脑的USB接口，既能供电，也能传输数据。但你使用的时候不一定既要用它来充电，也要同时用它来传数据。
    double processCardDiscount( double totalCost, double totalCostAfterDiscount, Customer customer, ShoppingCart shoppingCart);
}

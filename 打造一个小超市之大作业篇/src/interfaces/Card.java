package interfaces;

public interface Card {
    /**
     * @param totalCostAfterDiscount 顾客在经过上一轮优惠后（打折策略后、VIP卡后、打折卡后、赠品卡后...），这一轮实际需要支付的钱
     * @return 优惠的金额，非优惠后需要支付的价格
     */
    double processCardDiscount( double totalCostAfterDiscount);
}

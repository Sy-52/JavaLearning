package interfaces;

public interface Merchandise {

    String getName();

    int getCount();

    double getPurchasePrice();

    double getSoldPrice();

    Category getCategory();

    void buy(int buy);

    /**
     * 在结账时，如果突发意外(顾客不想买了/钱不够...)，将购物车中的商品退回
     * @param count 顾客的购物车中所购买的该商品的件数
     */
    void putBack(int count);
}

public interface Merchandise {
    String getName();

    double getSoldPrice();

    double getPurchasePrice();

    void buy(int buy);

    /**
     * 在结账时，如果突发意外(顾客不想买了/钱不够...)导致交易无法完成，将购物车中的所有商品退回
     * @param count
     */
    void putBack(int count);

    Category getCategory();

    int getCount();
}

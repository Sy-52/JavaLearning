public interface SuperMarket {

    Merchandise[] getAllMerchandise();

    /**
     * 根据消费者选择的分类去返回相应分类的商品
     * @param category 商品的类别
     */
    Merchandise[] getRandomMerchandiseOfCategory(Category category);

    /**
     * 如果有交易完成，超市会收到一笔钱
     * @param earnedMoney 交易所得的钱
     */
    void addEarnedMoney(double earnedMoney);

    /**
     * 将每天的营业情况生成一个日报
     */
    void dailyReport();

}

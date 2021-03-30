package interfaces;

import impI.Category;

public interface SuperMarket {

    Merchandise[] getMerchandises();

    /**
     * 根据顾客选择的种类 ，返回5个随机的该种类的商品
     * @param category 顾客选择的商品种类
     */
    Merchandise[] getRandomMerchandiseOfCategory(Category category);

    /**
     * 每有一笔交易完成，超市会将这笔账累加到总营业额上
     * @param earnedMoney 交易所收入的钱
     */
    void addEarnedMoney(double earnedMoney);

    /**
     * 每天的营业日报
     */
    void dailyReport();

}

package impI;

import interfaces.*;

public class ThinkAndBuyCustomer extends AbsCustomer implements HasCard{
    private Card card = VIPCard.Level0;

    public ThinkAndBuyCustomer(String custId, Category category){
        super(custId, category, DEFAULT_GUANG_COUNT);
    }

    public int buyJudge(Merchandise merchandise){
        Category category = merchandise.getCategory();
        if(category == getShouldBuy()){
            return 1;
        }
        double soldPrice = merchandise.getSoldPrice();
        double avgPrice = (category.getHigherPrice() + category.getLowerPrice()) / 2;
        if(soldPrice < avgPrice){
            return 1;
        }else{
            return 0;
        }
    }

    public Card getCard(){ return card; }

    public void setCard(Card card){this.card = card;}
}

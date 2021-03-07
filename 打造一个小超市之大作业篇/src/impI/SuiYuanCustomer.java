package impI;

import interfaces.Merchandise;

public class SuiYuanCustomer extends AbsCustomer{

    private static final double MUST_BUY_CHANCE = 0.8;
    private static final double GUANG_BUY_CHANCE = 0.1;

    public SuiYuanCustomer(String custId, Category shouldBuy){
        super(custId,shouldBuy,DEFAULT_GUANG_COUNT);
    }

    public int buyJudge(Merchandise merchandise){
        double chanceToBuy = (merchandise.getCategory() == getShouldBuy()) ? MUST_BUY_CHANCE : GUANG_BUY_CHANCE;
        if(chanceToBuy < Math.random()){
            return 0;
        }else{
            return 1 + (int)(Math.random() * 3);
        }
    }
}

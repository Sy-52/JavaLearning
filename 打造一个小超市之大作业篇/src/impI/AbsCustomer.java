package impI;

import interfaces.*;

import static util.ShoppingUtil.getRandomCategory;

public abstract class AbsCustomer implements Customer{
    private String custId;
    //正常情况下，应该是不同的顾客带不同金额的钱去进行消费。这里不考虑顾客带钱问题，只考虑顾客花了多少。
    private double moneySpent;
    //有些顾客在购物前可能就带有明确的目的：如家里食物没有了，那么他会优先逛FOOD类商品
    private Category shouldBuy;
    //通常来讲，顾客去超市购物只会逛几类商品。guangCount为顾客想逛的总类数，guangLeft为剩余的逛的类数。
    private int guangLeft;
    private int guangCount;

    public static int DEFAULT_GUANG_COUNT = 5;

    public AbsCustomer(String custId, Category shouldBuy, int guangCount){
        this.custId = custId;
        this.shouldBuy = shouldBuy;
        this.guangCount = guangCount;
    }

    public void startShopping(){ guangLeft = guangCount; }

    public Category chooseCategory(){
        if(guangLeft + 1 > guangCount){
            return shouldBuy;
        }else{
            return getRandomCategory();
        }
    }

    public boolean wantToCheckout(){
        guangLeft--;
        return guangLeft >= 0;
    }

    public double payFor(ShoppingCart shoppingCart, double totalCost){
        // >> TODO 思考题：如果顾客买不起咋办？
        moneySpent += totalCost;
        return totalCost;
    }

    public String getCusId(){ return custId;}

    public double getMoneySpent(){ return moneySpent;}

    public void setMoneySpent(double moneySpent){ this.moneySpent = moneySpent;}

    public Category getShouldBuy(){return shouldBuy;}

    public int getGuangCount(){ return guangCount;}
}

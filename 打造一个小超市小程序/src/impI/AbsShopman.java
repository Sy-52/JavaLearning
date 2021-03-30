package impI;

import interfaces.*;

import static util.ShoppingUtil.output;

public abstract class AbsShopman implements Shopman {
    private SuperMarket superMarket;

    protected static final int MAX_BUY_DEFAULT = 5;

    public AbsShopman(SuperMarket superMarket){ this.superMarket = superMarket; }

    public void checkOut(Customer customer, ShoppingCart shoppingCart){
        //结账
        double originCost = shoppingCart.calculateOriginCost();
        double finalCost = originCost;
        double savedMoney = 0;

        if(customer instanceof HasCard){
            Card card = ((HasCard) customer).getCard();
            savedMoney = card.processCardDiscount(originCost, finalCost, customer, shoppingCart);
            finalCost = originCost - savedMoney;
        }
        //顾客结账，超市记账
        double moneyEarned = customer.payFor(shoppingCart,finalCost);
        superMarket.addEarnedMoney(moneyEarned);

        output("顾客" + customer.getCusId() + "购物清单如下：");
        output(shoppingCart.toString());
        output("优惠金额为：" + savedMoney);
        output("实付金额为：" + moneyEarned);
    }

    public SuperMarket getSuperMarket(){return this.superMarket;};
}

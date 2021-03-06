package impI;

import interfaces.*;
import static util.ShoppingUtil.output;

public class SimpleShopman implements Shopman {
    private SuperMarket superMarket;

    private static int MAX_BUY_DEFAULT = 5;

    public SimpleShopman(SuperMarket superMarket){ this.superMarket = superMarket; }

    @Override
    public void serveCustomer(Customer customer) {
        // >> TODO 知识点：保护性编程。仔细品位下面这4句。
        int maxTypeToChoose = MAX_BUY_DEFAULT;
        if(customer instanceof AbsCustomer){
            maxTypeToChoose = ((AbsCustomer)customer).getGuangCount();
        }
        ShoppingCart shoppingCart = new ShoppingCart(maxTypeToChoose);
        customer.startShopping();

        while(customer.wantToCheckout() && shoppingCart.canHolder()){
            Category category = customer.chooseCategory();
            Merchandise[] toChoose = superMarket.getRandomMerchandiseOfCategory(category);
            for(Merchandise m : toChoose){
                //按照getRandomMerchandiseOfCategory()的算法，不一定能返回"足够5个"商品，可能会有null
                if(m == null)continue;
                int buyCount = customer.buyMerchandise(m);
                if(buyCount > 0){
                    shoppingCart.add(m,buyCount);
                    //一个品类商品中只买一种，所以买了即break出去。
                    break;
                }
            }
        }
        //结账
        double originCost = shoppingCart.calculateOriginCost();
        double finalCost = originCost;

        double savedMoney = 0;
        if(customer instanceof HasCard){
            Card card = ((HasCard) customer).getCard();
            savedMoney = card.processCardDiscount(originCost);
            finalCost = originCost - savedMoney;
        }
        double moneyEarned = customer.payFor(shoppingCart,finalCost);

        superMarket.addEarnedMoney(moneyEarned);

        output("顾客" + customer.getCusId() + "购物清单如下：");
        output(shoppingCart.toString());
        output("优惠金额为：" + savedMoney);
        output("实付金额为：" + moneyEarned);

        // >> TODO 这个方法是不是太长了？能否重构shopman这个接口更清晰一些？多切几刀，抽出公用的代码，再给不同类型的shopman定制一些代码
    }
}

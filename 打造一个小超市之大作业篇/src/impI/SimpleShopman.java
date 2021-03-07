package impI;

import interfaces.*;
import static util.ShoppingUtil.output;

public class SimpleShopman extends AbsShopman {

    public SimpleShopman(SuperMarket superMarket){ super(superMarket); }

    @Override
    public void serveCustomer(Customer customer) {
        // >> TODO 知识点：保护性编程。仔细品位下面这4行。
        int maxTypeToChoose = MAX_BUY_DEFAULT;
        if(customer instanceof AbsCustomer){
            maxTypeToChoose = ((AbsCustomer)customer).getGuangCount();
        }

        //开始购物
        ShoppingCart shoppingCart = new ShoppingCart(maxTypeToChoose);
        customer.startShopping();
        while(customer.wantToCheckout() && shoppingCart.canHolder()){
            Category category = customer.chooseCategory();
            Merchandise[] toChoose = getSuperMarket().getRandomMerchandiseOfCategory(category);
            for(Merchandise m : toChoose){
                //按照getRandomMerchandiseOfCategory()的逻辑，不一定能返回"足够5个"商品，可能会有null
                if(m == null)continue;
                int buyCount = customer.buyJudge(m);
                if(buyCount > 0){
                    shoppingCart.add(m,buyCount);
                    //一个品类商品中买到一种即可break出去看下一种类
                    break;
                }
            }
        }

        // >> TODO 这个方法是不是太长了？能否重构shopman这个接口使其更清晰一些？多切几刀，抽出公用的代码，以后使用时再为不同类型的shopman定制代码
        checkOut(customer,shoppingCart);
    }
}

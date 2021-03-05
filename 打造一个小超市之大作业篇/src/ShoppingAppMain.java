import impI.*;
import interfaces.*;

import static util.ShoppingUtil.output;
import static util.ShoppingUtil.input;
import static util.ShoppingUtil.createCustomer;
import static util.ShoppingUtil.createSuperMarket;

public class ShoppingAppMain {
    public static void main(String[] args) {
        SuperMarket superMarket = createSuperMarket();

        Shopman shopman = new SimpleShopman(superMarket);

        boolean open = true;
        while(open){
            new ShoppingTask(shopman).executeTask();
            output("是否继续营业？");
            open = !input().next().trim().equalsIgnoreCase("no");
        }

        superMarket.dailyReport();
    }
}

class ShoppingTask{
    private Shopman shopman;

    public ShoppingTask(Shopman shopman){this.shopman = shopman;}

    public void executeTask(){
        Customer customer = createCustomer(true);
        shopman.serveCustomer(customer);
    }
}

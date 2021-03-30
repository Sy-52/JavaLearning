import impI.*;
import interfaces.*;
import static util.ShoppingUtil.*;

public class ShoppingAppMain {
    public static void main(String[] args) {
        SuperMarket superMarket = createSuperMarket();

        Shopman shopman = new SimpleShopman(superMarket);

        boolean open = true;
        while(open){
            Customer customer = createCustomer(true);
            shopman.serveCustomer(customer);
            output("是否继续营业？");
            open = !input().next().trim().equalsIgnoreCase("no");
        }

        superMarket.dailyReport();
    }
}

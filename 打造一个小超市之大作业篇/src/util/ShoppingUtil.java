package util;

import impI.*;
import interfaces.*;

import java.util.Scanner;

public class ShoppingUtil {
    private static final Scanner in = new Scanner(System.in);

    public static Scanner input(){ return in; }

    public static void output(Object obj){
        System.out.println(String.valueOf(obj));
    }

    public static SimpleSuperMarket createSuperMarket(){
        int merchandisePerCategory = 10;
        // >> TODO 知识点：Merchandise接口中并没有构造方法，为什么能new？
        Merchandise[] all = new Merchandise[Category.values().length * 10];
        for(Category category : Category.values()){
            for(int i = 0; i < merchandisePerCategory; i++){
                double soldPrice = Math.random() * (category.getHigherPrice() - category.getLowerPrice()) + category.getLowerPrice();
                double purchasePrice = soldPrice * 0.7;
                all[category.ordinal() * 10 + i] = new SimpleMerchandise(
                        category.name() + i, 200, purchasePrice, soldPrice, category
                );
            }
        }

        SimpleSuperMarket superMarket = new SimpleSuperMarket(all);
        // >> TODO 知识点：引用指派。试着把下面三句拿到主程序中去。
        output("请输入超市的名字");
        String s = input().next();
        if(s.length() > 0){ superMarket.setName(s.trim()); }

        return superMarket;
    }

    public static Customer createCustomer(boolean auto){
        if(auto){
            String custId = "CUST" + (int)(Math.random() * 10000);
            Category shouldBuy = getRandomCategory();
            if(Math.random() < 0.5){
                return new SuiYuanCustomer(custId, shouldBuy);
            }else{
                ThinkAndBuyCustomer ret = new ThinkAndBuyCustomer(custId,shouldBuy);
                ret.setCard(getRandomVIPCard());
                return ret;
            }
        }
        return null;
    }

    public static Card getRandomVIPCard(){
        return VIPCard.values()[(int)(Math.random() * 1000) % VIPCard.values().length];
    }

    public static Category getRandomCategory(){
        return Category.values()[(int)(Math.random()*1000) % Category.values().length];
    }
}

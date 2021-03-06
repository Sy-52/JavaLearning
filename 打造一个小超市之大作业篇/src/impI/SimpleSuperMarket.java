package impI;

import interfaces.*;
import static util.ShoppingUtil.output;

public class SimpleSuperMarket implements SuperMarket{
    private String name;
    private double totalMoneyEarn;
    private Merchandise[] all;
    //allCount：超市开门营业前，所有商品的初始库存。
    private int[] allCount;
    //customerCount：超市共计服务过多少客户
    private int customerCount;

    public SimpleSuperMarket(Merchandise[] all){
        this.all = all;
        allCount = new int[all.length];
        for(int i = 0; i < all.length; i++){
            if(all[i] == null)continue;
            allCount[i] = all[i].getCount();
        }
    }

    public Merchandise[] getRandomMerchandiseOfCategory(Category category){
        Merchandise[] ret = new Merchandise[5];
        int pos = 0;
        for(Merchandise m : all){
            if(m.getCategory() == category && Math.random() > 0.5 && pos < all.length - 1){
                ret[pos] = m;
                pos++;
            }
        }
        return ret;
    }

    public void addEarnedMoney(double earnedMoney){
        customerCount++;
        totalMoneyEarn += earnedMoney;
    }

    public void dailyReport(){
        output("营业额为" + totalMoneyEarn);
        output("商品的售出情况如下：");
        for(int i = 0; i < all.length; i++){
            if(allCount[i] != all[i].getCount()){
                System.out.println(
                        all[i].getCategory().name() + "\t" + all[i].getName() + "\t" +
                                (allCount[i] - all[i].getCount()));
            }
        }
    }

    public void setName(String name){this.name = name;}

    public Merchandise[] getMerchandises(){ return all;}
}

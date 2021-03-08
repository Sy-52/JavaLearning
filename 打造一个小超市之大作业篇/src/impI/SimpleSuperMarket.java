package impI;

import interfaces.*;
import static util.ShoppingUtil.output;

public class SimpleSuperMarket implements SuperMarket{
    private String name;
    private double turnover;
    private Merchandise[] allMerchandise;
    private int[] allMerchandiseCount;
    //customerCount：超市共计服务了多少位顾客
    private int customerCount;

    public SimpleSuperMarket(Merchandise[] all){
        this.allMerchandise = all;
        allMerchandiseCount = new int[all.length];
        for(int i = 0; i < all.length; i++){
            allMerchandiseCount[i] = all[i].getCount();
        }
    }

    public Merchandise[] getRandomMerchandiseOfCategory(Category category){
        Merchandise[] ret = new Merchandise[5];
        int pos = 0;
        for(Merchandise m : allMerchandise){
            if(m.getCategory() == category && Math.random() > 0.5 && pos < ret.length){
                ret[pos] = m;
                pos++;
            }
        }
        return ret;
    }

    public void addEarnedMoney(double earnedMoney){
        customerCount++;
        turnover += earnedMoney;
    }

    public void dailyReport(){
        output("今天总共服务了" + customerCount + "位顾客");
        output("营业额为" + turnover);
        output("商品的售出情况如下：");
        for(int i = 0; i < allMerchandise.length; i++){
            if(allMerchandiseCount[i] != allMerchandise[i].getCount()){
                System.out.println(
                        allMerchandise[i].getCategory().name() + "\t" + allMerchandise[i].getName() + "\t" +
                                (allMerchandiseCount[i] - allMerchandise[i].getCount()));
            }
        }
    }

    public void setName(String name){this.name = name;}

    public Merchandise[] getMerchandises(){ return allMerchandise;}
}

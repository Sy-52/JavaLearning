package impI;

import interfaces.*;

public class SimpleSuperMarket implements SuperMarket{
    private String name;
    private double totalMoneyEarn;
    private Merchandise[] all;
    //allCount：超市开门营业前，所有商品的初始库存。
    private int[] allCount;
    //customerCount：超市共计服务过多少客户
    private int customerCount;

    //没想到超市中的商品居然是在外部初始化好，然后传引用进来的...
    public SimpleSuperMarket(Merchandise[] all){
        this.all = all;
        allCount = new int[all.length];
        for(int i = 0; i < all.length; i++){
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
        //臧萌老师单独弄了一个工具package，后面回头再来写该方法
    }

    public void setName(String name){this.name = name;}

    public Merchandise[] getMerchandises(){ return all;}
}

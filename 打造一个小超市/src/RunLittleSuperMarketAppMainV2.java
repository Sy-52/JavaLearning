import supermarket.*;

import java.util.Scanner;

public class RunLittleSuperMarketAppMainV2 {
    public static void main(String[] args) {
        // >> TODO 知识点：创建一个构造方法。简化main()中的逻辑
        LittleSuperMarket littleSuperMarket = new LittleSuperMarket("百伦广场","DJY大道211号",200,0.0);
        littleSuperMarket.merchandise = new Merchandise[200];
        littleSuperMarket.merchandiseSold = new int[littleSuperMarket.merchandise.length];

        //将littleSuperMarket.merchandise这个引用赋给all，避免该长串字符的重复
        Merchandise[] all = littleSuperMarket.merchandise;

        //初始化200件商品
        for(int i = 0; i < all.length; i++){
            Merchandise m = new Merchandise();
            m.count = 200;
            m.id = i;
            m.name = "商品" + (i + 1);
            m.purchasePrice = Math.random() * 200;
            m.soldPrice = (1 + Math.random()) * 200;
            all[i] = m;
        }

        System.out.println("超市开门啦！");
        System.out.println("本店叫做" + littleSuperMarket.superMarketName);
        System.out.println("本店地址：" + littleSuperMarket.address);
        System.out.println("共有停车位：" + littleSuperMarket.parkingCount);
        System.out.println("今天的营业额:" + littleSuperMarket.incomingSum);
        System.out.println("共有商品:" + littleSuperMarket.merchandise.length + "种");

        // >> TODO 知识点：方法的返回值。
        System.out.println("下面请净利润最高的商品做自我介绍：");
        littleSuperMarket.getBiggestProfitMerchandise().describe();

        // >> TODO 知识点：方法的参数
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.println("今天本超市全场半价优惠！本超市提供" + all.length + "种商品。请选择您要购买的商品编号：(退出购买请输入-1)");
            int index = scanner.nextInt() - 1;
            Merchandise m = all[index];
            // >> TODO 方法的形参类型 -- 自定义类型
            System.out.println("该商品的总利润是超市里所有商品中最高的：" + m.isTheBiggestTotalValueOne(littleSuperMarket));
            if (index < 0) {
                break;
            }
            if (index >= all.length) {
                System.out.println("没有此种商品！请您重新选择");
                continue;
            } else {
                System.out.println("请输入您要购买的数量：");
                int countToBuy = scanner.nextInt();
                // >> TODO halfPriceBuy()中的countToBuy叫做实际参数。
                //double totalCost = m.halfPriceBuy(countToBuy);
                // >> TODO 一个方法中传递多个参数
                double totalCost = m.halfPriceBuyAndPrintLeft(countToBuy,true);
                if(totalCost == -1){
                    System.out.println("抱歉！商品库存不足!");
                    continue;
                }else{
                    System.out.println("您购买的" + countToBuy + "件" + m.name + "的单价为：" + m.soldPrice + ".今天超市全场商品第二件半价优惠！总价为：" + totalCost);
                    // >> TODO 方法的形参类型 -- 自己本身这种类型
                    Merchandise judgeMerchandise = all[199];
                    System.out.println("商品200目前的总价值比用户购买商品目前的总价值大：" + m.totalValueBiggerThan(judgeMerchandise));
                    continue;
                }
            }
        }
    }
}

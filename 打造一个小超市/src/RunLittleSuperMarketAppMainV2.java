import supermarket.*;

import java.util.Scanner;

public class RunLittleSuperMarketAppMainV2 {
    public static void main(String[] args) {
        LittleSuperMarket littleSuperMarket = new LittleSuperMarket();
        littleSuperMarket.superMarketName = "大融城";
        littleSuperMarket.address = "精文·国际广场";
        littleSuperMarket.parkingCount = 200;
        littleSuperMarket.incomingSum = 0.0;
        littleSuperMarket.merchandise = new Merchandise[200];
        littleSuperMarket.merchandiseSold = new int[littleSuperMarket.merchandise.length];

        //将littleSuperMarket.merchandise这个引用赋给all，避免前面那个长串字符的重复
        Merchandise[] all = littleSuperMarket.merchandise;

        //给200件商品添加基本信息
        for(int i = 0; i < all.length; i++){
            /*
                这里先创建一个Merchandise类的实例m再赋值给all[i]的原因为：
                new Merchandise[200]仅意味着创建了一个长度为200的Merchandise类型的数组。
                里面的数组元素littleSuperMarket.merchandise[i]的值仍然为null.
                所以如果直接用all[i].count = 200去给成员变量赋值的话，会报NPE
            */
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

        //知识点1 - 方法的返回值。
        littleSuperMarket.getBiggestProfitMerchandise().describe();

        //知识点2 - 方法的参数
        Scanner scanner = new Scanner(System.in);
        System.out.println("今天本超市全场半价优惠！本超市提供" + all.length + "种商品。请选择您要购买的商品编号：(退出购买请输入-1)");
        while(true) {
            int index = scanner.nextInt() - 1;
            if (index < 0) {
                break;
            }
            if (index >= all.length) {
                System.out.println("没有此种商品！请您重新选择");
                continue;
            } else {
                System.out.println("请输入您要购买的数量：");
                int countToBuy = scanner.nextInt();
                Merchandise m = all[index];
                // >> TODO 注意下面这个buy().countToBuy叫做实际参数。
                double totalCost = m.buy(countToBuy);
                if(totalCost == -1){
                    System.out.println("抱歉！商品库存不足!");
                }else{
                    System.out.println("您要购买的" + countToBuy + "件" + m.name + "的单价为：" + m.soldPrice + ".今天超市全场商品第二件半价优惠！总价为：" + totalCost);
                    break;
                }
            }
        }
    }
}

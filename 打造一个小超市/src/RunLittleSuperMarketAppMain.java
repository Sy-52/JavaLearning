import supermarket.*;
import person.*;
import java.util.Scanner;

public class RunLittleSuperMarketAppMain {
    public static void main(String[] args) {
        // >> TODO 注明：RunLittleSuperMarketAppMain.java是最初的版本，该程序主要写的是开超市的逻辑，基本没有语法点。

        // >> TODO 知识点：创建一个构造方法。简化main()中的逻辑
        LittleSuperMarket littleSuperMarket = new LittleSuperMarket("百伦广场","DJY大道211号",200,0.0);
        littleSuperMarket.merchandise = new Merchandise[200];
        littleSuperMarket.merchandiseSold = new int[littleSuperMarket.merchandise.length];

        //将littleSuperMarket.merchandise这个引用赋给all，避免该长串字符的重复
        Merchandise[] all = littleSuperMarket.merchandise;

        //初始化200件商品
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

        Scanner scanner = new Scanner(System.in);

        boolean open = true;
        while(open){
            //开始营业
            Customer customer = new Customer();
            customer.name = "顾客编号" + (int)(Math.random() * 10000);
            customer.money = (1 + Math.random()) * 1000;
            customer.isDrivingCar = Math.random() > 0.5;

            if(customer.isDrivingCar){
                if(littleSuperMarket.parkingCount <= 0){
                    System.out.println("非常抱歉！本店停车位已停满！期待您的下次光临！");
                    continue;
                }else{
                    System.out.println("欢迎顾客" + customer.name + "光临本超市！本超市停车位为免费使用！祝您购物愉快！");
                    littleSuperMarket.parkingCount--;
                }
            }else{
                System.out.println("欢迎顾客" + customer.name + "光临本超市！祝您购物愉快！");
            }

            //顾客开始购物
            double totalCost = 0;
            while(true) {
                System.out.println("本超市提供" + all.length + "种商品。请选择您要购买的商品编号：(退出购买请输入-1)");
                int index = scanner.nextInt() - 1;
                if(index < 0){
                    break;
                }
                if (index >= all.length) {
                    System.out.println("没有此种商品！请您重新选择");
                    continue;
                } else {
                    Merchandise m = all[index];
                    if(m.count <= 0){
                        System.out.println(m.name + "已经售空了！请您重新选择！");
                        continue;
                    }else{
                        System.out.println(m.name + "还有货源！单价为" + m.soldPrice + ".请输入您要购买的数量:");
                        int numToBuy = scanner.nextInt();
                        if(numToBuy <= 0){
                            System.out.println("不买看看也好。欢迎您继续选购！");
                            continue;
                        }
                        if(numToBuy > m.count){
                            System.out.println(m.name + "没有这么多库存！请您重新选择！");
                            continue;
                        }
                        if(numToBuy * m.soldPrice > customer.money){
                            System.out.println("很抱歉，您钱没带够。");
                            continue;
                        }else {
                            System.out.println("恭喜您购买成功！");
                            totalCost += numToBuy * m.soldPrice;
                            customer.money -= totalCost;
                            m.count -= numToBuy;
                            littleSuperMarket.merchandiseSold[index] += numToBuy;
                        }
                    }
                }
            }
            //顾客完成购买后,离开
            if(customer.isDrivingCar){
                littleSuperMarket.parkingCount++;
            }
            System.out.println(customer.name + "共消费了" + totalCost + "元。");
            littleSuperMarket.incomingSum += totalCost;
            System.out.println("超市还继续营业吗？");
            open = scanner.nextBoolean();
        }
        System.out.println("超市关门啦！");
        System.out.println("今日总的营业额为" + littleSuperMarket.incomingSum + ".营业情况如下：");
        //拉一个销售清单
        for(int i = 0;i < littleSuperMarket.merchandise.length; i++){
            //这里重新创建m的原因是因为变量的作用域问题
            Merchandise m = all[i];
            int numSold = littleSuperMarket.merchandiseSold[i];
            if( numSold!= 0){
                double incomming = m.soldPrice * numSold;
                double netIncomming = (m.soldPrice - m.purchasePrice) * numSold;
                System.out.println(m.name + "售出了" + littleSuperMarket.merchandiseSold[i] + "个。"
                                    + "总的销售额为：" + incomming + ".净利润为：" + netIncomming);
            }
        }
    }
}

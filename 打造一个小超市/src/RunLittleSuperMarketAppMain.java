import supermarket.*;
import person.*;
import java.util.Scanner;

public class RunLittleSuperMarketAppMain {
    public static void main(String[] args) {
        // >> TODO 注明：RunLittleSuperMarketAppMain.java是最初的版本，该程序主要写的是开超市的逻辑，基本没有语法点。

        // >> TODO 知识点：创建一个构造方法。简化main()中的逻辑
        LittleSuperMarket littleSuperMarket = new LittleSuperMarket("百伦广场","DJY大道211号",200,0.0);

        Merchandise[] all = littleSuperMarket.getMerchandise();

        System.out.println("超市开门啦！");
        System.out.println("本店叫做" + littleSuperMarket.getSuperMarketName());
        System.out.println("本店地址：" + littleSuperMarket.getAddress());
        System.out.println("共有停车位：" + littleSuperMarket.getParkingCount());
        System.out.println("今天的营业额:" + littleSuperMarket.getIncomingSum());
        System.out.println("共有商品:" + all.length + "种");

        Scanner scanner = new Scanner(System.in);

        boolean open = true;
        while(open){
            //开始营业
            Customer customer = new Customer();

            if(customer.getCustomerIsDrivingCar()){
                if(littleSuperMarket.getParkingCount() <= 0){
                    System.out.println("非常抱歉！本店停车位已停满！期待您的下次光临！");
                    continue;
                }else{
                    System.out.println("欢迎顾客" + customer.getCustomerName() + "光临本超市！本超市停车位为免费使用！祝您购物愉快！");
                    littleSuperMarket.setParkingCount(littleSuperMarket.getParkingCount() - 1);
                }
            }else{
                System.out.println("欢迎顾客" + customer.getCustomerName() + "光临本超市！祝您购物愉快！");
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
                    if(m.getMerchandiseCount() <= 0){
                        System.out.println(m.getMerchandiseName() + "已经售空了！请您重新选择！");
                        continue;
                    }else{
                        System.out.println(m.getMerchandiseName() + "还有货源！单价为" + m.getMerchandiseSoldPrice() + ".请输入您要购买的数量:");
                        int numToBuy = scanner.nextInt();
                        if(numToBuy <= 0){
                            System.out.println("不买看看也好。欢迎您继续选购！");
                            continue;
                        }
                        if(numToBuy > m.getMerchandiseCount()){
                            System.out.println(m.getMerchandiseName() + "没有这么多库存！请您重新选择！");
                            continue;
                        }
                        if(numToBuy * m.getMerchandiseSoldPrice() > customer.getCustomerMoney()){
                            System.out.println("很抱歉，您钱没带够。");
                            continue;
                        }else {
                            System.out.println("恭喜您购买成功！");
                            totalCost += numToBuy * m.getMerchandiseSoldPrice();
                            customer.setCustomerMoney(customer.getCustomerMoney() - totalCost);
                            m.setMerchandiseCount(m.getMerchandiseCount() -  numToBuy);
                            littleSuperMarket.getMerchandiseSold()[index] += numToBuy;
                        }
                    }
                }
            }
            //顾客完成购买后,离开
            if(customer.getCustomerIsDrivingCar()){
                littleSuperMarket.setParkingCount(littleSuperMarket.getParkingCount() + 1);
            }
            System.out.println(customer.getCustomerName() + "共消费了" + totalCost + "元。");
            littleSuperMarket.setIncomingSum(littleSuperMarket.getIncomingSum() + totalCost);
            System.out.println("超市还继续营业吗？");
            open = scanner.nextBoolean();
        }
        System.out.println("超市关门啦！");
        System.out.println("今日总的营业额为" + littleSuperMarket.getIncomingSum() + ".营业情况如下：");
        //拉一个销售清单
        for(int i = 0;i < all.length; i++){
            //这里重新创建m的原因是因为变量的作用域问题
            Merchandise m = all[i];
            int numSold = littleSuperMarket.getMerchandiseSold()[i];
            if( numSold!= 0){
                double incomming = m.getMerchandiseSoldPrice() * numSold;
                double netIncomming = (m.getMerchandiseSoldPrice() - m.getMerchandisePurchasePrice()) * numSold;
                System.out.println(m.getMerchandiseName() + "售出了" + littleSuperMarket.getMerchandiseSold()[i] + "个。"
                                    + "总的销售额为：" + incomming + ".净利润为：" + netIncomming);
            }
        }
    }
}

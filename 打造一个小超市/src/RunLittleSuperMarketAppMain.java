import supermarket.*;

public class RunLittleSuperMarketAppMain {
    public static void main(String[] args) {
        LittleSuperMarket littleSuperMarket = new LittleSuperMarket();
        littleSuperMarket.superMarketName = "大润发";
        littleSuperMarket.address = "莲花南路1号";
        littleSuperMarket.parkingCount = 200;
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
            m.name = "商品" + i;
            m.purchasePrice = Math.random() * 200;
            m.soldPrice = (1 + Math.random()) * 200;
            all[i] = m;
        }

        System.out.println("超市开门啦！");

        boolean open = true;
        while(open){
            System.out.println("本店叫做" + littleSuperMarket.superMarketName);
            System.out.println("本店地址：" + littleSuperMarket.address);
            System.out.println("共有停车位：" + littleSuperMarket.parkingCount);
            System.out.println("今天的营业额:" + littleSuperMarket.incomingSum);
            System.out.println("共有商品:" + littleSuperMarket.merchandise.length + "种");
            break;
        }
    }
}

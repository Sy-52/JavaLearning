import supermarket.*;

public class RunLittleSuperMarketAppMainV3 {
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

        System.out.println("LittleSuperMarket类的对象是：" + littleSuperMarket);
        Merchandise m = littleSuperMarket.getBiggestProfitMerchandise();
        System.out.println("下面请利润最高的商品做自我介绍：");
        m.describe();
        System.out.println("利润最高的Merchandise对象是：" + m);
        //m.makeEnoughFor(500);
        m.makeEnoughForOneByOne(500);
    }
}

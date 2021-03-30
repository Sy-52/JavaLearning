import supermarket.*;

public class ToStringMain {
    public static void main(String[] args) {
        LittleSuperMarket littleSuperMarket = new LittleSuperMarket("大卖场","世纪大道1号",500,0);

        Merchandise m100 = littleSuperMarket.getMerchandise()[100];

        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append("商品100是：").append(m100);

        // >> TODO 如果你不在类中写toString()去覆盖Object类中的toStirng()的话，会打印getClass() + @ + 16进制的hashcode
        System.out.println(strBuilder.toString());
        System.out.println(m100);
    }
}

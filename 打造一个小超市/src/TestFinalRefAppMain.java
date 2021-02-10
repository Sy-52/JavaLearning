import supermarket.*;

public class TestFinalRefAppMain {
    public static void main(String[] args) {
        LittleSuperMarket littleSuperMarket = new LittleSuperMarket("大卖场", "世纪大道1号", 500, 0);
        // >> TODO getMerchandise()[10]指向的是一个Phone类的实例，为什么还要转类型？
        // >> TODO 因为getMerchandise()返回的是一个Merchandise类型的数组，引用是Merchandise类型的。
        Phone ph = (Phone)littleSuperMarket.getMerchandise()[10];
        ShellColorChangePhone ph1 = (ShellColorChangePhone)littleSuperMarket.getMerchandise()[100];

        Merchandise gift = ph.getGift();
        gift.describe();
        gift.setName("礼物改名了！");
        gift.setCount(99);
        gift.describe();
        System.out.println();
        // >> TODO 在LittleSuperMarket初始化的时候，无论有壳还是无壳手机，其成员变量gift均指向引用giftForPhone指向的实例对象.
        // >> TODO 虽然他们的成员变量gift无法更改，但是它们指向的这个实例对象的内容是可以改变的
        Merchandise gift1 = ph1.getGift();
        gift1.describe();
        System.out.println();

        // >> TODO final修饰符修饰数组类型也和修饰引用类型的成员变量类似。虽然不能让数组的引用指向其它，但是可以改变里面的数组元素的值。
        Merchandise m0 = littleSuperMarket.getMerchandise()[0];
        m0.describe();
        m0 = gift;
        m0.describe();
    }
}

import supermarket.LittleSuperMarket;
import supermarket.*;

public class OverloadTestMain {
    public static void main(String[] args) {
        LittleSuperMarket littleSuperMarket = new LittleSuperMarket(
                "胥家菜市", "石龙下街17号", 50, 0.0
        );

        Merchandise m = littleSuperMarket.getMerchandise()[100];

        MerchandiseTest merchandiseTest = new MerchandiseTest();

        // >> TODO m这个引用指向的是一个ShellColorChangePhone的实例，但是，调用哪种重载方法仅仅只和传入实参的类型有关。
        merchandiseTest.testMerchandiseOverload(m);
        merchandiseTest.testMerchandiseOverload((Phone) m);
        merchandiseTest.testMerchandiseOverload((ShellColorChangePhone) m);
        // >> TODO 甚至你可以传String类型的实参
        merchandiseTest.testMerchandiseOverload("");
        System.out.println();

        // >> TODO 可以看到，引用就算让其指向null也无妨。java在编译期就会根据实参的类型知道该调用哪个"确定的"方法，这叫静态多态。
        m = null;
        merchandiseTest.testMerchandiseOverload(m);
        merchandiseTest.testMerchandiseOverload((Phone)m);
        merchandiseTest.testMerchandiseOverload((ShellColorChangePhone) m);
        System.out.println();

        // >> TODO null是所有引用类型的缺省值，所以让其转换成任何的类型都行。
        // >> TODO 如果引用类型没有完全匹配时，会根据继承关系，沿着参数当前类型往上撸着找，找到最贴近参数类型的那个方法。
        // >> TODO （特别注明：java没有多继承！）
        merchandiseTest.testMerchandiseOverloadNotExactlyMatchType((ShellColorChangePhone)null);
        System.out.println();

        // >> TODO 知识点：静态多态:重载 + 动态多态：覆盖
        // >> TODO 分别用true、1、3、6来运行试试
        Merchandise m1 = littleSuperMarket.getMerchandise()[100];
        m1.buy(3);
    }
}
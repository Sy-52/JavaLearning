import supermarket.*;

import static supermarket.Category.*;

public class ReferenceAssignMain {
    public static void main(String[] args) {
        // >> TODO 可以让一个父类的引用指向一个子类的实例，因为父类能做的子类都能做。但是只能像操作父类实例一样操作子类实例。
        Merchandise m = new Phone(
                "手机001", 2, 100,1999, 999, ELECTRIC,
                4.5, 3.5, 4, 128, "华为", "安卓", null
        );

        //System.out.println(m.getPhoneBrand());

        // >> TODO 怎么解决呢？强制类型转换即可。
        Phone ph = (Phone)m;
        System.out.println(ph.getBrand());

        // >> TODO 相反，让一个子类的引用指向一个父类的实例则不行,因为父类没有子类的属性、方法,java会报错
        //Phone ph = new Merchandise();

        ShellColorChangePhone shellColorChangePhone = new ShellColorChangePhone(
                "手机002", 2, 100,1999, 999, ELECTRIC,
                4.5, 3.5, 4, 128, "索尼", "安卓", null, false
        );

        // >> TODO 思考，为何下面三行都可以。
        Merchandise m1 = shellColorChangePhone;
        Phone ph1 = (Phone)m1;
        ShellColorChangePhone ccp = (ShellColorChangePhone)m1;

        //出错。因为m指向的是一个Phone类型的实例
        //ShellColorChangePhone ccp1 = (ShellColorChangePhone)m;

        // >> TODO 知识点：for循环的另一种写法
        System.out.println();
        LittleSuperMarket littleSuperMarket = new LittleSuperMarket("大卖场", "世纪大道1号", 500, 0);
        System.out.println(littleSuperMarket.getBiggestPurchasePrice());
    }
}

import supermarket.*;

public class StaticMethodDoesNotBelieveOverride {
    public static void main(String[] args) {
        LittleSuperMarket littleSuperMarket = new LittleSuperMarket("大卖场", "世纪大道1号", 500, 0);
        // >> TODO getMerchandise()[10]指向的是一个Phone类的实例，为什么还要转类型？
        // >> TODO 因为getMerchandise()返回的是一个Merchandise类型的数组，引用是Merchandise类型的。
        Merchandise m = littleSuperMarket.getMerchandise()[100];
        Phone ph = (Phone)m;
        ShellColorChangePhone ccp = (ShellColorChangePhone)m;

        // >> TODO 静态方法可以被继承。所以Phone如果没有定义staticMethod()则会调用Merchandise中的staticMethod()方法
        Merchandise.staticMethod();
        Phone.staticMethod();
        ShellColorChangePhone.staticMethod();
        System.out.println();

        // >> TODO 非静态方法中，能调用什么方法取决于引用类型，实际调用哪个方法取决于实例的类型
        // >> TODO 而下面的三行程序，java则根据引用类型去找对应的staticMethod方法，和你实例是什么类型无关。
        // >> TODO 不推荐这种方法，就应该用类名去调用静态方法
        m.staticMethod();
        ((Phone)m).staticMethod();
        ((ShellColorChangePhone)m).staticMethod();
        System.out.println();

        // >> TODO 从本质上说，覆盖就是在方法的this自引用上做文章。而静态方法没有this自引用，其根本没有访问this自引用。
        ((Merchandise)null).staticMethod();
        ((Phone)null).staticMethod();
        ((ShellColorChangePhone)null).staticMethod();

    }
}

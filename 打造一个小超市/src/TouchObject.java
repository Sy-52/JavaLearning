import supermarket.*;

public class TouchObject {
    public static void main(String[] args) {
        // >> TODO Object是java对"对象"的一个最基本的抽象。getClass()让其知道自己是什么类；hashCode()可以相对唯一的标识一个对象
        Object obj = new Object();
        printObj(obj);

        System.out.println();
        LittleSuperMarket littleSuperMarket = new LittleSuperMarket("大卖场", "世纪大道1号", 500, 0);

        Merchandise t0 = new Merchandise("商品" + 0, 0, 200,1999, 999);
        Merchandise t10 = new Merchandise("手机" + 10, 10, 200,1999, 999);
        Merchandise t100 = new Merchandise("手机" + 100, 100, 200,1999, 999);

        System.out.println(littleSuperMarket.findMerchandise(t0));
        System.out.println(littleSuperMarket.findMerchandise(t10));
        System.out.println(littleSuperMarket.findMerchandise(t100));
    }
    private static void printObj(Object obj){
        System.out.println("-----" + obj + "的详细内容-----");
        System.out.println(obj);
        //试试方法
        System.out.println(obj.toString());
        // >> TODO java类库中有很多native方法，其方法通过方法名映射到本地代码（C/C++）实现，供其跑在对应系统上。其自身没有方法体
        System.out.println(obj.getClass());
        // >> TODO hashCode可以翻为哈希码/散列码。
        System.out.println(obj.hashCode());
    }
}

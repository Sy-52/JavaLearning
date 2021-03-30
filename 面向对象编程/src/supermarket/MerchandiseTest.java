package supermarket;

public class MerchandiseTest {

    public void testMerchandiseOverload(Merchandise me){
        System.out.println("参数类型为Merchandise的testMerchandiseOverload()被调用了！");
    }

    public void testMerchandiseOverload(Phone ph){
        System.out.println("参数类型为Phone的testMerchandiseOverload()被调用了！");
    }

    public void testMerchandiseOverload(ShellColorChangePhone shellColorChangePhone){
        System.out.println("参数类型为ShellColorChangePhone的testMerchandiseOverload()被调用了！");
    }

    public void testMerchandiseOverload(String str){
        System.out.println("参数类型为String的testMerchandiseOverload()被调用了！");
    }

    public void testMerchandiseOverloadNotExactlyMatchType(Phone ph){
        System.out.println("参数类型为Phone的testMerchandiseOverloadNotExactlyMatchType()被调用了");
    }
}

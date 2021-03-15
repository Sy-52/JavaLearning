package annotation;

public class CommonAnnotations implements TestIntf{
    @Override
    public void test() {
        // >> TODO java在编译时会根据注解进行相应操作。
        testOld();
    }

    //下面这个注解的含义是：该方法快过期了，不建议使用。当然用也可以。
    @Deprecated
    public void testOld() { }
}

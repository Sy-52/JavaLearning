package Annotation;

public class CommonAnnotations implements TestIntf{
    @Override
    public void test() {
        // >> TODO java编译器在编译时会根据注解进行相应操作。
        testOld();
    }

    //@Deprecated：该方法即将过期，不建议使用（虽然也能用）
    @Deprecated
    public void testOld() { }
}

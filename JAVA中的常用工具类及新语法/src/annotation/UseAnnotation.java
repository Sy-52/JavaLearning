package annotation;

public class UseAnnotation {
    // >> TODO 该注解是不能用在FIELD(变量)上的，否则会出错
//    @DefineAnnotation(primaryType = 0 , clazz = TestUseAnnotation.class,
//            stringValue = "随便写", stringArrayValues = {"a", "b"})
    private Object obj;

    @Deprecated
    @DefineAnnotation(primaryType = 0 , clazz = UseAnnotation.class, stringValue = "随便写", stringArrayValues = {"a", "b"})
    public void test(){}
}

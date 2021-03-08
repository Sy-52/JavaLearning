public class NewAndThrowIt {
    public static void main(String[] args) throws Exception{
        causeException();
    }

    public static void causeException() throws Exception{
        // >> TODO 知识点：在代码里创建异常
        // >> TODO 如果根据程序的逻辑，判断自己在该代码的范围内已经不能处理了。可以创建一个checked exception，然后用throws关键字抛出，让方法的调用者来处理
        // >> TODO 对应的，需要方法也有throws语句，同样，throws语句的类型要能覆盖实际异常的类型。
        throw new Exception("");
    }

    public static void causeRuntimeException(){
        // >> TODO 可以创建一个unchecked exception，然后用throw关键字抛出。此时方法签名可有可没有throws语句。
        throw new RuntimeException("");
    }
}

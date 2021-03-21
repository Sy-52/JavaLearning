package Generic;

import Generic.MyGenericClass;

import java.lang.reflect.Field;

public class UseDefineGenericTypesMain {
    public static void main(String[] args) throws NoSuchFieldException {
        Field field = MyGenericClass.class.getDeclaredField("first");
        System.out.println("first的类型是：" + field.getType());

        MyGenericClass<String, Object> test = new MyGenericClass<>("instance1", new Object());
        // >> TODO "instance2"实例类型为string，可以用Object引用指向它。
        MyGenericClass<String, Object> test1 = new MyGenericClass<>("instance1", "instance2");

        String first = test.getFirst();
        System.out.println(first);
        // >> TODO 出错，因为返回值后会被强转为Object类型
        //String second = test1.getSecond();

        // >> TODO 泛型方法的类型取决于前面引用的类型。
        String another = test.getAnother("param");
        // >> TODO 强制类型转换发生在java编译时期，无论return那句做不做强转，java编译器都会像下面第二句一样在那个地方做强转处理。
        String another1 = test.getAnother(new Object());
        //String another2 = (String)test.getAnother(new Object());
    }
}

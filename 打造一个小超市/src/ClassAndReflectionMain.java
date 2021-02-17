import supermarket.*;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ClassAndReflectionMain {
    // >> TODO 变长参数定义方法：String... args.
    // >> TODO 变长参数必须位于方法的参数列表的最后一个。可以不传入具体实参，只要不传实参，就等价于new String[0].
    public static void dyncArgs(int a, String... args){
    }

    public static void main(String... args) throws NoSuchFieldException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        dyncArgs(1);
        dyncArgs(1,new String[0]);
        dyncArgs(1,"aaa","bbb","ccc");
        dyncArgs(1,new String[]{"aaa","bbb","ccc"});

        LittleSuperMarket littleSuperMarket = new LittleSuperMarket("大卖场","世纪大道1号",500,0);
        Merchandise m100 = littleSuperMarket.getMerchandise()[100];

        // >> TODO Class类是描述类的类。通过Class类的实例基本能看到类中除了方法的逻辑代码外的所有信息。
        // >> TODO 除了通过【引用.getClass()】来获取Class类的实例外，还可以通过【类名.class】来获取实例。
        Class clazz = m100.getClass();
        //Class clazz = ShellColorChangePhone.class;

        System.out.println(clazz.getName());
        System.out.println(clazz.getSimpleName());
        System.out.println();

        // >> TODO 知识点：反射（反射在寻找对应的属性、方法时慢，但在执行时时间是一样的）
        // >> TODO 使用getField()来得到类中为public的成员变量的"定义"
        // >> TODO 使用getDeclaredField()来得到类中所有声明过的成员变量的"定义"，然后强制用代码设为可以访问。（反射特有）
        Field field = clazz.getDeclaredField("enableShellColorChange");
        field.setAccessible(true);
        // >> TODO 用反射访问属性时，field必须是传进来的这个实例的父类/本身这个类所获取的Field.
        System.out.println(field.get(m100));
        field.set(m100, true);
        System.out.println(field.get(m100));
        System.out.println();

        printFields(clazz);
        System.out.println();

        //通过反射来获取public的静态变量的值，private的静态变量的访问参看上面
        field = clazz.getField("STATIC_MEMBER");
        System.out.println(field.get(null));
        System.out.println();

        // >> TODO 使用getMethod()来得到类中的方法.invoke a method on an object.
        // >> TODO 使用反射去调用方法，该多态的还是多态，该重载的还是重载。
        Method method = clazz.getMethod("buy", int.class);
        method.invoke(m100, 1);
        //m100.buy(1);
        //m100.buy(10);
        System.out.println();

        //通过反射来调用public的静态方法，private的静态方法调用参看上面
        method = clazz.getMethod("getNameOf", Merchandise.class);
        String str = (String)method.invoke(null, m100);
        System.out.println(str);
    }

    public static void printFields(Class clazz){
        System.out.println(clazz.getName() + "里的所有的Field:");
        for(Field field : clazz.getFields()){
            System.out.println(field.getType() + ":" + field.getName());
        }
    }
}

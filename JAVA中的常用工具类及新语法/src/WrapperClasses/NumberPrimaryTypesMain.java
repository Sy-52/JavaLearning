package WrapperClasses;

import java.util.HashMap;
import java.util.Map;

public class NumberPrimaryTypesMain {
    public static void main(String[] args) {
        // >> TODO 问题：像List、Map这些类，都是操作的Object，无法操作基本数据类型
        // >> TODO 所以，从Java第一个版本开始，Java就为每种基本数据类型提供了封装的类。如Byte、Short、Integer、Long、Float、Double
        // >> TODO 所有封装类，都是【不可变的】。
        int a = 99;
        // >> TODO valueOf返回的Integer类实例(封装类实例)，被自动拆箱取出了其中的值，赋给了int类型变量b。
        int b = Integer.valueOf(a);
        // >> TODO parseInt返回的就是int类型，不存在自动拆箱过程
        int c = Integer.parseInt("99");

        // >> TODO "自动"封箱：把一个基本数据类型，转换为对应类的实例。java实现自动封箱要不是通过创建实例，要不就是返回缓存的实例，如缓存了一定数量的Integer实例
        // >> TODO "自动"拆箱：把类的实例，转换为基本数据类型。
        Integer a1 = 987;
        int b1 = a1;

        Map<Integer, String> mapInst1 = new HashMap<>();
        mapInst1.put(1,"壹");
        mapInst1.put(2,"贰");
        mapInst1.put(3,"叁");
        System.out.println(mapInst1.get(1));

        for(int key : mapInst1.keySet()){
            System.out.println(key);
        }

        // >> TODO 一些常用方法
        System.out.println();
        System.out.println(Integer.toBinaryString(1024));
        System.out.println(Integer.toOctalString(1024));
        System.out.println(Integer.toHexString(1024));

        // >> TODO 所有和数字相关的封装类，都继承自Number类(自动拆装箱)
        Number num = 9;
        Number num1 = 9.9;

        // >> TODO Number类的常用方法。自动拆箱其实就是调Number类中的xxxValue()
        System.out.println();
        System.out.println(num1.longValue());

        // >> TODO 自动拆箱可能的NPE。null上是没有intValue()的，所以会导致NPE，其他类推。
        mapInst1.put(null,"无");
        for(int key : mapInst1.keySet()){
            System.out.println(key);
        }
    }
}

package Lambda;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class IterateListLambdaMain {
    public static void main(String[] args) {
        List<String> myList = addElementsToList(new ArrayList<>());
        String outside = "outside string:";

        // >> TODO 下面几种形式代码的功能都是一样的。

//                for(String str : myList){
//            processString(outside + str);
//        }

        // >> TODO 匿名内部类版
//        myList.forEach(new Consumer<String>(){
//            public void accept(String str){
//                processString(outside + str);
//            }
//        });

        // >> TODO 函数式编程是把函数作为基本运算单元。函数可作为变量、可接受函数、可返回函数。把支持函数式编程的编码风格称为Lambda表达式。
        // >> TODO 用lambda表达式去替代繁琐的只有单个抽象方法的接口。参数类型、返回值类型编译器可自动推断，故可以省略。
//        myList.forEach((str) -> {  processString(outside + str);  });
//        myList.forEach(str -> processString(outside + str));
        // >> TODO 若某方法的参数类型、返回值类型和接口中定义的抽象方法一样，我们也可直接传入方法引用。
        myList.forEach(IterateListLambdaMain::processString);
        // >> TODO 实例方法调用时第一个隐藏参数会传入this。我们可以可以像这样new一个类的实例去调实例方法。
//        IterateListLambdaMain inst = new IterateListLambdaMain();
//        myList.forEach(inst::processStringNonStatic);
        // >> TODO toUpperCase为String的实例方法，传入一个参数。
        myList.forEach(String::toUpperCase);
        System.out.println();

        Map<String, String> myMap = new HashMap<>();
        myMap.put("k1","v1");
        myMap.put("k2","v2");
        myMap.put("k3","v3");
        // >> TODO Map的forEach和List的forEach又不一样了。
        myMap.forEach((k,v) -> processTwoStrings(k,v));
        myMap.forEach(IterateListLambdaMain::processTwoStrings);
    }

    public static List<String> addElementsToList(List<String> list){
        for(int i = 0; i < 22; i++){
            list.add("str" + i);
        }
        return list;
    }

    public static void processString(String str){
//        System.out.println(str);
        // >> TODO 可以发现，action.accept直接跳到了下面这句。其实这中间Java在后台一顿操作帮我们生成了一个接口的匿名实现类，并在其中调用了我们的方法。
        throw new RuntimeException();
    }


    //非静态方法
    public void processStringNonStatic(String str){
        System.out.println(str);
    }

    public static void processTwoStrings(String str1, String str2){
        System.out.println("s=" + str1 + ", v=" + str2);
    }
}

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

        // >> TODO Lambda完整版：(形参) -> {代码块}
        // >> TODO Lambda表达式必须能够符合接口中定义的抽象方法(参数、返回值、异常都必须匹配）
        // >> TODO 只要符合方法对接口的要求，就可以不在乎那些细节(有无接口名、accept...都不管)
//        myList.forEach((str) -> {processString(outside + str);});
        // >> TODO lambda简化版：如果只有单个代码行，可省略大括号、分号
//        myList.forEach(str -> processString(outside + str));
        // >> TODO lambda终极简化版：如果你不需要访问外部变量，只跑个方法。那（类名::方法名）即可。
        myList.forEach(IterateListLambdaMain::processString);
        // >> TODO 如果调实例方法，可以像这样new一个方法所在的类的实例去调
//        IterateListLambdaMain inst = new IterateListLambdaMain();
//        myList.forEach(inst::processStringNonStatic);
        // >> TODO 我们和java如果知道forEach中的每个元素都是String类型，则可以调它的实例方法。可用如下格式。
//        myList.forEach(String::toUpperCase);
        System.out.println();

        Map<String, String> myMap = new HashMap<>();
        myMap.put("k1","v1");
        myMap.put("k2","v2");
        myMap.put("k3","v3");
        // >> TODO 方法形参为两个的情况，用如下格式
        myMap.forEach((k,v) -> processTwoStrings(k,v));
        // >> TODO 参数的顺序是按规定的顺序来的，所以可以省略参数
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

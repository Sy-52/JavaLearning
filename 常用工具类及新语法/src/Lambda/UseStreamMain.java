package Lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static Lambda.IterateListLambdaMain.addElementsToList;

public class UseStreamMain {
    public static void main(String[] args) {
        List<String> myList = addElementsToList(new ArrayList<>());
        System.out.println("---------- Lambda的奥义 -------------");
        // TODO stream与List不同于，List存储的元素是存储在内存中的java对象，而stream输出的元素可能没有事先存储，而是实时计算出来的。
        // TODO Stream.map()的功能是将一种操作运算映射到序列的每一个元素上，并返回一个新的stream实例.
        myList.stream().filter(s -> s.length() > 4).map(String::toUpperCase).forEach(System.out::println);
        //myList.stream().filter(s -> {return s.length() > 4;}).map(String::toUpperCase).forEach(System.out::println);

        // TODO Stream.collect会把stream实例中的每一个元素收集到一个ArrayList实例中。(细节代码我看不懂，只能看表面的功能了...)
        System.out.println("---------- 使用collector ------------");
        List<String> longerStrList = myList.stream().filter(s -> s.length() > 4)
                .map(String::toUpperCase).collect(Collectors.toList());
        longerStrList.forEach(System.out::println);
    }
}

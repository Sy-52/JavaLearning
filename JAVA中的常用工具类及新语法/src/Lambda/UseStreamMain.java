package Lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static Lambda.IterateListLambdaMain.addElementsToList;

public class UseStreamMain {
    public static void main(String[] args) {
        List<String> myList = addElementsToList(new ArrayList<>());
        System.out.println("---------- Lambda的奥义 -------------");
        // >> TODO stream()是java专门为lambda而暴露出来的东西。它让我们把对数据的处理封装为lambda，然后传进去进行一个一个的处理。
        // >> TODO 这里的map()是Stream类中的一个方法，含义是映射、转换。功能是将stream中的元素按参数中指定的函数进行转换/映射，得到一个新的stream.
        myList.stream().filter(s -> s.length() > 4).map(String::toUpperCase).forEach(System.out::println);
        //myList.stream().filter(s -> {return s.length() > 4;}).map(String::toUpperCase).forEach(System.out::println);

        // >> TODO 使用collector让数据重新生成一个List
        System.out.println("---------- 使用collector ------------");
        List<String> longerStrList = myList.stream().filter(s -> s.length() > 4)
                .map(String::toUpperCase).collect(Collectors.toList());
        longerStrList.forEach(System.out::println);
    }
}

import java.util.ArrayList;
import java.util.List;

public class UseJavaGenericClassAppMain {
    public static void main(String[] args) {
        //useStringList();
        useStringListGenerics();
    }

    private static void useStringList(){
        List strList = createStringList();
        for(int i = 0; i < strList.size(); i++){
            // >> TODO 泛型要解决的就是这个问题：我知道我在List中放的是String类型的元素，但是用的时候我还要强转。
            String str = (String)strList.get(i);
            str = str.toUpperCase();
            System.out.println(str);
        }
    }

    private static List createStringList(){
        List ret = new ArrayList();
        for(int i = 0; i < 10; i++){
            ret.add("str" + (i % 5));
        }
        return ret;
    }

    private static void useStringListGenerics(){
        List<String> strList = createStringListGenerics();
        for(int i = 0; i < strList.size(); i++){
            // >> TODO 这里会报错。2、java编译器在生成的字节码中强转strList.get(i)。但这里Object实例无法强转为String，故导致错误。
            String str = strList.get(i).toUpperCase();
            System.out.println(str);
        }
    }

    private static List<String> createStringListGenerics(){
        // >> TODO List支持泛型。List<E>泛型类做两件事，1、告知编译器ArrayList类里面Object引用只能指向E类型的实例，编译时帮忙检查。
        // >> TODO 不指定，那么List<E>泛型类默认告知编译器Object引用只能指向Object及其子类实例，编译时帮忙检查
        List<String> ret = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            // >> TODO Boolean add(E e); 这里的参数必须为String，传Object对象则java编译器在编译时会检测到错误。
            ret.add("str" + (i % 5));
            //ret.add(new Object());
        }
        // >> TODO 这里采取一个移花接木的方式，往List中添加Object类型元素。
        List causeError = ret;
        causeError.add(new Object());
        return ret;
    }
}

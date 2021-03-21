import java.util.*;

public class UseSetMain {
    public static void main(String[] args) {
        // >> TODO Collection接口的另一重要接口Set，HashSet为Set的实现类。顾名思义，其借助了hash值来做去重。
        // >> TODO 每个Object实例都有hashCode、equals方法。这两个方法符合这样一个规定，equals若返回true，hashCode的返回值必须相等。
        // >> TODO HashSet中的实现就遵守了这种约定，所以要保证HashSet的不可重复性，放入HashSet的对象也要【符合上述规定且不可变】。
        printCollection(addElementsToCollection(new HashSet()));
    }

    public static Collection addElementsToCollection(Collection collection){
        for(int i = 0; i < 10; i++){
            collection.add("str" + (i % 5));
        }
        return collection;
    }

    public static void printCollection(Collection collection){
        System.out.println();
        System.out.println("输出" + collection.getClass() + "中的元素，共" + collection.size() + "个");
        // >> TODO 任何实现了Iterable接口的类/接口都可以用for...each来遍历
        try{
            for(Object element : collection){
                System.out.println(element);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}

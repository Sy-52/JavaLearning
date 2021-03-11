import java.util.*;

public class UseSetAppMain {
    public static void main(String[] args) {
        // >> TODO Collection接口的另一重要实现类HashSet。顾名思义，HashSet借助了hash值来帮忙做去重。
        // >> TODO 每个Object实例都有hashCode()、equals()。这两个方法符合这样一个规定：equals()返回true，hashCode()返回值必须相等。
        // >> TODO 很多java类库中的代码都按这种约定使用这两个方法的，如HashSet。所以要保证HashSet的不可重复性，放入HashSet的对象也要【符合上述规定且不可变】。
        printCollection(addElementsToCollection(new HashSet()));
    }

    public static Collection addElementsToCollection(Collection collection){
        for(int i = 0; i < 10; i++){
            // >> TODO 知识点：不用关心Collection接口是ArrayList/LinkedList/MYArrayList/MyLinkedList谁实现的,只要能用add()即可。
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

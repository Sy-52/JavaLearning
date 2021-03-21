import MyList.*;

import java.util.*;

public class UseListMain {
    public static void main(String[] args) {
//        printCollection(addElementsToCollection(new ArrayList()));
//        printCollection(addElementsToCollection(new LinkedList()));

        printList((List)addElementsToCollection(new MyArrayList()));
//        printList((List)addElementsToCollection(new MyLinkedList()));
    }

    public static Collection addElementsToCollection(Collection collection){
        for(int i = 0; i < 10; i++){
            // >> TODO 知识点：不用关心Collection接口是ArrayList/LinkedList/MYArrayList/MyLinkedList谁实现的,我们关心能否实现添加元素功能就好。
            collection.add("str" + (i % 5));
        }
        return collection;
    }

    public static void printCollection(Collection collection){
        System.out.println();
        System.out.println("输出" + collection.getClass() + "中的元素，共" + collection.size() + "个");
        // >> TODO 任何实现了Iterable接口的类都可以用for...each来遍历
        try{
            for(Object element : collection){
                System.out.println(element);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public static void printList(List list){
        System.out.println("输出" + list.getClass() + "中的元素，共" + list.size() + "个");
        //因为我们写的MYArrayList/MyLinkedList没有实现Iterable接口，所以不能使用for...each
//        for(int i = 0; i < list.size(); i++){
//            System.out.println(list.get(i));
//        }
        // >> TODO 在MyArrayList实现了Iterator接口后，我们便可以使用for...each进行遍历
        try {
            for (Object element : list) {
                System.out.println(element);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}

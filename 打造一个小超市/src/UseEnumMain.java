import supermarket.Category;

import java.util.Scanner;

public class UseEnumMain {
    public static void main(String[] args) {
        for(Category category : Category.values()){
            System.out.println("----" + category.getId() + "----");
            // >> TODO ordinal()输出枚举中实例的序号.
            System.out.println(category.ordinal());
            System.out.println(category.name());
            System.out.println(category.toString());
        }
        System.out.println();

        Scanner in = new Scanner(System.in);
        System.out.println("请输入枚举的名字：");
        String inputName = in.next();
        Category enumInstance = Category.valueOf(inputName.trim().toUpperCase());
        System.out.println("枚举的信息：" + enumInstance.toString());

        System.out.println("请输入要比较枚举的名字：");
        String inputName2 = in.next();
        Category enumInstance2 = Category.valueOf(inputName2.trim().toUpperCase());
        System.out.println("枚举的信息：" + enumInstance2.toString());

        // >> TODO 枚举就这么几个实例，可以使用==去比较.
        System.out.println("两个引用是否指向同一实例?" + (enumInstance == enumInstance2));
        System.out.println();
    }
}

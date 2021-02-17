import supermarket.Category;
import supermarket.LittleSuperMarket;

import java.util.Scanner;

public class UseEnumMain {
    public static void main(String[] args) {
        for(Category category : Category.values()){
            System.out.println("----" + category.getId() + "----");
            // >> TODO ordinal()输出定义实例的序号.
            System.out.println(category.ordinal());
            System.out.println(category.name());
            System.out.println(category.toString());
        }
        System.out.println();

        Scanner in = new Scanner(System.in);
        System.out.println("请输入枚举的名字：");
        String categoryName = in.next();
        Category enumInput = Category.valueOf(categoryName.trim().toUpperCase());
        System.out.println("枚举的信息：" + enumInput.toString());

        System.out.println("请输入要比较枚举的名字：");
        String categoryName2 = in.next();
        Category enumInput2 = Category.valueOf(categoryName2.trim().toUpperCase());
        System.out.println("枚举的信息：" + enumInput2.toString());

        // >> TODO 枚举就这么几个实例，可以使用==去比较.
        System.out.println("两个引用是否指向同一实例?" + (enumInput == enumInput2));
        System.out.println();

        LittleSuperMarket littleSuperMarket = new LittleSuperMarket("大卖场", "世纪大道1号", 500, 0);

        littleSuperMarket.getBiggestProfitMerchandise().describe();
    }
}

package supermarket;

import supermarket.*;

// >> TODO 枚举继承自Enum类，并继承了一些【我们常用的】方法.
public enum Category{
    // >> TODO 必须在开始时以这种方式调用构造方法，创建【所有的】实例.
    // >> TODO 实例名字不强制要求大写，不过一般大写
    FOOD(1),COOK(3),SNACK(5),CLOTHES(7),ELECTRIC(9);

    private int id;

    // >> TODO 枚举的构造方法不允许外部调用，故用private修饰，通常不写。
    private Category(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                '}';
    }
}

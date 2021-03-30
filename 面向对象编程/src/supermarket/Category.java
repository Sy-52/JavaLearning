package supermarket;

import supermarket.*;

// >> TODO 枚举继承自Enum类，程序员通常会使用一些【继承的常用】方法.
public enum Category{
    // >> TODO 必须在开始时就以这种方式调用构造方法，在枚举中创建【所有的】实例.
    // >> TODO 实例名字不强制要求大写，不过一般大写
    FOOD(1),COOK(3),SNACK(5),CLOTHES(7),ELECTRIC(9);

    private int id;

    // >> TODO 枚举的构造方法不允许外部调用，故为private，通常不写。（java默认）
    private Category(int id){
        this.id = id;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                '}';
    }

    //提供get()
    public int getId(){
        return id;
    }
}

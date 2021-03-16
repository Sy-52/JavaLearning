package Generic;

import ext.GrandParent;

// >> TODO 如果用下列句式定义泛型，等于告知java，MyType是GrandParent本身/GrandParent的子类，我们就可以使用MyType类型的实例去调GrandParent的方法。
public class MyGenericClassBounded<MyType extends GrandParent> {
    // >> TODO 实际上这个引用是GrandParent类型而非MyType，见Main()。如果不写extends，默认extends Object.
    private MyType myType;

    public MyGenericClassBounded(MyType myType){
        // >> TODO 泛型类型不可调用方法，因为不知是什么类型。但为了使用某个类的方法，可以像上面这样继承。
        myType.getNum();
        this.myType = myType;
    }
}

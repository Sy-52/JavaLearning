package Generic;

import ext.*;

import java.util.ArrayList;
import java.util.List;

public class UseMoreGenericsMain {
    public static void main(String[] args) {
        List<Children> g3List = new ArrayList<>();
        // >> TODO Children是Parent的子类，而List<Children>不是List<Parent>的子类，所以下面这句出错。
        //justG2Method(g3List);
        extMethod(g3List);

        // >> TODO 创建协变的引用，可以用Parent/Parent的子类的泛型引用给其赋值。
        List<? extends Parent> g2ListExt = null;
        g2ListExt = g3List;
        g2ListExt = new ArrayList<Parent>();
        //g2ListExt = new ArrayList<GrandParent>();
        // >> TODO 为了避免下面三句这样移花接木造成的问题，java不允许用第三句的方式来添加元素/实例。
        List<Children> g3OnlyList = new ArrayList<>();
        g2ListExt = g3OnlyList;
        //g2ListExt.add(new Parent());

        // >> TODO 除了协变，java还有逆变，与协变反向。语法如下。
        List<? super Parent> g2ListSup = null;
        g2ListSup = new ArrayList<Parent>();
        g2ListSup = new ArrayList<GrandParent>();
        // >> TODO 因为同样的原因，java不允许逆变用这种方式来添加元素/实例。
        //g2ListSup.add(new GrandParent());

        // >> TODO 无论逆变/协变，都只能用在引用上。引用可以使用一个比较宽泛的类型，但是创建实例时必须指定清楚。
        //List<? extends Parent> g2ListExt1 = new ArrayList<? extends Parent>();
        //List<? super Parent> g2ListSub1 = new ArrayList<? super Parent>();
    }

    public static void justG2Method(List<Parent> Parent){}
    // >> TODO 协变语法如下，表明参数可接受的List引用的范围为Parent/Parent的子类。(协变让参数的范围更大)
    public static void extMethod(List<? extends Parent> extParam){
        // >> TODO 我虽然不能插入元素，但是读取元素是可以的
        for(Parent parent : extParam){ }
    }
}

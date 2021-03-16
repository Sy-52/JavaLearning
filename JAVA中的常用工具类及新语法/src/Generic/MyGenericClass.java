package Generic;

// >> TODO 泛型类的定义，如下
public class MyGenericClass<First, Second> {
    private First first;
    private Second second;

    public MyGenericClass(First first, Second second){
        this.first = first;
        this.second = second;
    }

    public <Another>Another getAnother(Object val){ return (Another)val; }

    public First getFirst(){return first;}

    public Second getSecond(){return second;}
}

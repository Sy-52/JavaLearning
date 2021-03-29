package LearnVolatile;

public class DataHolder {
    // TODO 成员变量不用赋初值，缺省值为0、
    int a, b, c, d, f, g;
//    long e;
    // TODO volatile可以修饰成员变量，强制JVM在读写成员变量时去主存中读写，而不用CPU核的缓存进行读写。
    // TODO 在这里使用volatile，就是告诉编译器，我关心这里的顺序和数据一致性，让编译器别优化、重排。
    volatile long e;
    int counter;

    public void operateData(){
        a += 1;
        b += 1;
        c += 1;
        d += 1;

        e += 1;

        f += 1;
        g += 1;
    }

    public void check(){
        // TODO g += 1发生在了e += 1之前。
        // TODO 指令有可能是乱序的...java为了优化之类的目的，在不违反JMM规范的前提下，可能会调换指令的运行顺序。
        if(g > e){
            System.out.println("g大于e的情况：" + (counter++));
        }
    }

}

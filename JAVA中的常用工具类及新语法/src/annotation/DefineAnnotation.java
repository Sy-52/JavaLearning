package annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
// >> TODO 以上两个元素是每个注解必须要用的。（描述注解的注解）
// >> TODO @Target表示可以在哪些元素上使用注解。@Retention表示注解可以被留存到什么阶段，如SOURCE表示只留存到编译期。(其实英文写的很清楚...)

// >> TODO 定义一个自己的annotation，主要@interface
public @interface DefineAnnotation {
    // >> TODO annotation支持的类型有primary type、Class、String、Enum、其他注解，以及前面这些类型的数组.
    int primaryType();

    Class clazz();

    String stringValue() default "N/A";

    // TODO 还要定义注解类型的缺省值
    Override suibianqidemingzi() default @Override;

    String[] stringArrayValues();
}

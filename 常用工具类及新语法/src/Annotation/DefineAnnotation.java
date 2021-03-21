package Annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// >> TODO @Target：可以在哪些元素上使用注解。
@Target(ElementType.METHOD)
// >> TODO @Retention表示注解可以被留存到什么阶段。(详见英文注解)
@Retention(RetentionPolicy.RUNTIME)
// >> TODO 以上两个元素是描述注解的注解，每个注解必须要有。

// >> TODO 自定义一个注解，用@interface
public @interface DefineAnnotation {
    // >> TODO 注解支持的类型：primary type、Class、String、前面这些类型的数组、枚举、其他的注解。
    int primaryType();

    Class clazz();

    String stringValue() default "N/A";

    // TODO 还要定义注解类型的缺省值
    Override otherAnnotations() default @Override;

    String[] stringArrayValues();
}

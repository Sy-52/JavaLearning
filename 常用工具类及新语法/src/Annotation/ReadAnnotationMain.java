package Annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;

public class ReadAnnotationMain {
    public static void main(String[] args) throws NoSuchMethodException {
        Class clazz = UseAnnotation.class;
        Method method = clazz.getMethod("test");
        for(Annotation annotation : method.getAnnotations()){
            System.out.println(annotation.annotationType());
        }

        System.out.println();
        DefineAnnotation readDefineAnnotation = method.getAnnotation(DefineAnnotation.class);
        System.out.println(readDefineAnnotation.primaryType());
        System.out.println(readDefineAnnotation.clazz());
        System.out.println(readDefineAnnotation.stringValue());
        // >> TODO 下面这句比较新，需积累一下
        System.out.println(Arrays.asList(readDefineAnnotation.stringArrayValues()));
    }
}

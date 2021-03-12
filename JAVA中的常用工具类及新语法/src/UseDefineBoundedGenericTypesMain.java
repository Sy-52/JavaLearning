import MyGeneric.MyGenericClassBounded;

import java.lang.reflect.Field;

public class UseDefineBoundedGenericTypesMain {
    public static void main(String[] args) throws NoSuchFieldException {
        Field field = MyGenericClassBounded.class.getDeclaredField("myType");
        System.out.println(field.getType());
    }
}

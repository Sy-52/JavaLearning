public class MustHandel {
    // >> TODO 如果错误处理不了，可以直接用throws把错误抛出去
    // >> TODO 抛出的异常类型，可以是实际异常的父类/本身.
    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException {
        Class clazz = Class.forName("abc");
        clazz.getField("");
    }
}

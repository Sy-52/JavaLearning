package AI;

public class LearnStringBuilder {
    public static void main(String[] args) {
        // >> TODO StringBuilder类创建的实例对象是可变的.且对它进行操作的方法都会返回this自引用，顾可以一直调用方法。
        StringBuilder strBuilder = new StringBuilder();

        long longVal = 123456789;

        strBuilder.append(true).append("abc").append(longVal);

        System.out.println(strBuilder.toString());
        System.out.println(strBuilder.reverse().toString());
        // >> TODO 这里再次反转，字符串恢复原样。佐证了我们操作的的确是同一个变量。
        System.out.println(strBuilder.reverse().toString());
        System.out.println(strBuilder.toString());

        System.out.println(strBuilder.delete(0,4).toString());
        // >> TODO 这里的true已经被删除了，所以输出的字符串为：abcInsert123456789
        System.out.println(strBuilder.insert(3,"Insert").toString());
    }
}

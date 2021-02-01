package AI;

public class LearnString {
    public static void main(String[] args) {
        String content = "01234567ABCDefgh";

        //String的length()是方法而非成员变量
        System.out.println(content.length());

        // >> TODO 知识点：String类用来存储字符串的变量是private的，且不提供任何修改变量的方法。
        // >> TODO 所以String对象一旦生成，其内容就不可能被修改
        // >> TODO 下面的方法，都不会改变原来的字符串content，全是返回一个new出来的新的String类的对象
        System.out.println(content.toUpperCase());
        System.out.println(content.toLowerCase());
        System.out.println(content);

        System.out.println(content.charAt(1));

        System.out.println(content.substring(5));

        System.out.println(content.substring(1,5));

        String content2 = "Orange_Apple_Banana";

        char[] chars = content2.toCharArray();
        for(int i = 0; i < chars.length; i++){
            System.out.println(chars[i]);
        }

        String[] s = content2.split("_");
        for(int i = 0; i < s.length; i++){
            System.out.println(s[i]);
        }

        int indexOf = content2.indexOf("_");
        System.out.println(indexOf);
        System.out.println(content2.substring(indexOf + 1,content2.length()));

        int lastIndexOf = content2.lastIndexOf("_");
        System.out.println(lastIndexOf);
        System.out.println(content2.substring(0,lastIndexOf));

        System.out.println(content2.contains("apple"));
        System.out.println(content2.contains("Apple"));
        System.out.println(content2.startsWith("Orange"));
        System.out.println(content2.endsWith("Banana"));

        String content2_1 = "Orange_Apple_Banana";
        String content2_2 = "orange_Apple_banana";
        System.out.println(content2.equals(content2_1));
        System.out.println(content2.equals(content2_2));

        System.out.println(content2.equalsIgnoreCase(content2_2));

        System.out.println(content2.replace("Banana","Fan"));
    }

}

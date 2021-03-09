
public class TryFinallyAppMain {
    public static void main(String[] args) {
        System.out.println(withFinally());
    }

    public static int withFinally() {
        int len = 0;
        try{
            String s = null;
            return s.length();
        }finally{
            // >> TODO finally语句会在异常返回后，后面的方法执行开始前执行
            System.out.println("执行finally语句.");
            // >> TODO finally中如果有return语句，会打乱exception的传递
            return -2;
        }
    }
}

public class TryCatchFinallyAppMain {

    public static int VAL = 0;

    public static void main(String[] args) {
        System.out.println(withFinally());
        System.out.println(VAL);
    }

    public static int withFinally() {
        int len = 0;
        try{
            String s = null;
            s.toString();
        }catch(Exception ex){
            len = -1;
            System.out.println("执行catch里的return语句.");
            return len;
            // >> TODO 知识点：无论是return返回还是catch中又抛异常返回，finally语句都会执行.
        }finally{
            System.out.println("执行finally语句.");
            // >> TODO return语句执行后，已经把-1从变量len中读出放在一个地方，只等finally执行结束就把该值返回回去，所以重新赋值是无效的。
            len = -2;
            VAL = 999;
            System.out.println("finally语句执行完毕.");
            // >> TODO finally语句块中如果有return语句，则会把之前return的值给覆盖掉。
            return -2;
        }
    }
}

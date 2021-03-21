public class TouchException {
    public static void main(String[] args) {
        // >> TODO try语句块中如果发生了异常，则程序会跳转到catch语句块。(catch语句会根据异常类的类型来捕获相应类型的异常)
        // >> TODO Java会将异常的相关信息封装在一个异常类的实例中，ex是指向这个异常实例的引用。
        // >> TODO catch语句执行完毕后，程序会继续向下执行。
        try {
            int[] arr = new int[1];
            arr[1] = 9;
        } catch(ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
        }
        System.out.println("程序执行结束。");
    }
}

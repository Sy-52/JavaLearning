package AutoClosableResource;

public class TryWithResource {
    public static void main(String[] args) {
        try(
                MyAutoClosableResource res1 = new MyAutoClosableResource("res1");
                MyAutoClosableResource res2 = new MyAutoClosableResource("res2");
            ){
            while(true){
                System.out.println(res1.read());
                System.out.println(res2.read());
            }
        }catch (Exception e){
            // >> TODO 自动调用close()方法后，还是会将异常的信息打印出来。
            e.printStackTrace();
        }
    }
}

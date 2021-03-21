package AutoClosableResource;

import java.io.IOException;

public class MyAutoClosableResource implements AutoCloseable{
    // >> TODO 知识点 java中可以被自动关闭的资源，定义了一个接口叫AutoClseable。可以让try语句帮我们做自动关闭的操作，自动去调接口中定义的close().
    private String resName;
    private int counter;

    public MyAutoClosableResource(String resName){ this.resName = resName; }

    public String read() throws IOException {
        counter++;
        if(Math.random() > 0.1){
            return "you got lucky to read from " + resName + " for " + counter + " times...";
        }else{
            throw new IOException("资源读取异常...抛出Exception！");
        }
    }

    @Override
    public void close() throws Exception {
        System.out.println("资源释放了：" + resName);
    }
}

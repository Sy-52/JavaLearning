package MethodCallStack;

import MyExceptions.MyRuntimeException;

import static MethodCallStack.CallStack.output;

public class CallerRTExceptionAppMain {
    public static void main(String[] args) {
        makeCall();
    }

    public static void makeCall(){
        // >> TODO unchecked exception不强制处理，所以冷不丁吃到一个RuntimeException，程序会失败
        Caller1 caller1 = new Caller1();
        output("调用开始");
        try {
            caller1.caller2RTException();
        }catch(MyRuntimeException ex){
            output("使用异常进行跳转成功！继续写逻辑代码！");
        }
        output("调用结束");
    }
}

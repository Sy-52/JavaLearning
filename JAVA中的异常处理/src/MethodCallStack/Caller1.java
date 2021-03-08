package MethodCallStack;

import MyExceptions.MyException;

import static MethodCallStack.CallStack.*;

public class Caller1 {
    Caller2 caller2 = new Caller2();

    public void caller2RTException(){
        output("Caller1.call2RTException开始");
        caller2.caller3RTException();
        output("Caller1.call2RTException结束");
    }

    public void caller2Exception() throws MyException {
        output("Caller1.call2Exception开始");
//        try {
            caller2.caller3Exception();
//        }catch (Exception ex){
//            output("got exception in Caller1：" + ex.getMessage());
//        }
        output("Caller1.call2Exception结束");
    }
}

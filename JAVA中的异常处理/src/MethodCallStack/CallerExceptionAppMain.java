package MethodCallStack;

import MyExceptions.MyException;

import static MethodCallStack.CallStack.output;

public class CallerExceptionAppMain {
    public static void main(String[] args) throws MyException{
        makeCall();
    }

    public static void makeCall() throws MyException {
        // >> TODO checked exception需要明确的throws或者catch
        Caller1 caller1 = new Caller1();
        output("调用开始");
        caller1.caller2Exception();
        output("调用结束");
    }
}

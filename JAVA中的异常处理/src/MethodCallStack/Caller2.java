package MethodCallStack;

import MyExceptions.MyException;

import static MethodCallStack.CallStack.output;

public class Caller2 {
    Caller3 caller3 = new Caller3();

    public void caller3RTException(){
        output("Caller2.call3RTException开始");
        caller3.callThrowRTException();
        output("Caller2.call3RTException结束");
    }

    public void caller3Exception() throws MyException {
        output("Caller2.call3Exception开始");
        caller3.callThrowException();
        output("Caller2.call3Exception结束");
    }
}

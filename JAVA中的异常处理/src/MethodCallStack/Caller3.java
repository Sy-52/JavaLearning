package MethodCallStack;

import MyExceptions.*;

import static MethodCallStack.CallStack.output;

public class Caller3 {

    public void callThrowRTException(){
        output("Caller3.callThrowRTException开始");
        try {
            Object n = null;
            n.toString();
        }catch(NullPointerException ex){
            output("这种情况很正常！开始使用异常来跳转到指定地点处理程序！");
            throw new MyRuntimeException("Caller3中执行callThrowRTException出错.",ex);
        }
        output("Caller3.callThrowRTException结束");
    }

    public void callThrowException() throws MyException {
        output("Caller3.callThrowException开始");
        try {
            Class.forName("Get Class by name.");
            //>> TODO 随意throws一个不会发生的checked exception/unchecked exception，java程序都不会报错。
            //>> TODO 若catch一个不会发生的checked exception，java程序会报错；但如果catch一个unchecked exception，java程序则不会报错。
        }catch (ClassNotFoundException ex){
            throw new MyException("Caller3的callThrowException()抛出的异常",ex);
        }
        output("Caller3.callThrowException结束");
    }
}

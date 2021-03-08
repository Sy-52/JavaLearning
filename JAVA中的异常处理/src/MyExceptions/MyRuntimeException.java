package MyExceptions;

public class MyRuntimeException extends RuntimeException{
    public MyRuntimeException() { super(); }

    public MyRuntimeException(String message) { super(message); }

    // >> TODO Throwable cause：引起异常的异常实例
    public MyRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyRuntimeException(Throwable cause) {
        super(cause);
    }
}

import java.io.IOException;

public class CatchMultiException {
    public static void main(String[] args) {
        catchMultiNew();
        catchMultiOld();
    }

    private static void catchMultiNew(){
        // >> TODO 新语法，简化模式。处理方式和旧的一样。
        try{
            throwMultiException(0);
        }catch (ClassNotFoundException | IOException e){
            e.printStackTrace();
        }
    }

    private static void catchMultiOld(){
        // >> TODO 如果一个方法抛出多种异常，可以针对性的去设置多种catch语句
        try{
            throwMultiException(0);
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private static void catchMultiTypeMultiMatch(){
        // >> TODO 异常是从上到下去catch，不能有多种匹配可能
        try{
            throwMultiException(0);
        }catch (Exception e){
            e.printStackTrace();
        }
//        catch (ClassNotFoundException e){
//            e.printStackTrace();
//        }catch (IOException e){
//            e.printStackTrace();
//        }
    }

    private static void throwMultiException(int i) throws ClassNotFoundException, IOException {
        switch (i){
            case 1:
                throw new NullPointerException();
                case 2:
                    throw new ClassNotFoundException();
            case 3:
                throw new IOException();
        }
    }
}

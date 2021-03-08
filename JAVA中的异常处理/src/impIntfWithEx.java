public class impIntfWithEx implements IntfWithEx{
    @Override
    public void danger() throws Exception {
        // >> TODO 接口中若声明要抛出异常，实现类中可抛/可不抛异常。（但抛的话必须是接口中声明的类或其子类）
        throw new Exception("");
    }

    @Override
    public void safe() {
        // >> TODO 接口中若没有声明抛出异常，实现类中可抛/可不抛uncheckedException。
        //throw new RuntimeException();
        // >> TODO 不能抛checked exception，否则会出错。（但是可以catch到异常封在RuntiomeException中，如下）
        try{
            throw new Exception();
        }catch (Exception e){
            throw new RuntimeException("",e);
        }
    }
}

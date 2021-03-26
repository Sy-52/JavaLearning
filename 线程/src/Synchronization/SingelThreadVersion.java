package Synchronization;

public class SingelThreadVersion {
    public static void main(String[] args) {
        DataHolder dataHolder = new DataHolder();
        // TODO 单线程版本increase.run（）的代码执行完毕后，才会继续往下执行后面代码.
        ChangeData increase = new ChangeData(2, Integer.MAX_VALUE, dataHolder);
        increase.run();

        ChangeData decrease = new ChangeData(-2, Integer.MAX_VALUE, dataHolder);
        decrease.run();
    }
}

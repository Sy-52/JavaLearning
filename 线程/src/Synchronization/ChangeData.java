package Synchronization;

public class ChangeData implements Runnable{
    private long delta;

    private long boundary;

    private DataHolder  dataHolder;

    public ChangeData(long delta, long loopCounter, DataHolder dataHolder){
        this.delta = delta;
        this.boundary = loopCounter;
        this.dataHolder = dataHolder;
    }

    @Override
    public void run() {
        for(int i = 0; i < boundary; i++){
            //dataHolder.change(delta);
            DataHolder.changeStatic(delta);
        }
        //dataHolder.print();
        DataHolder.printStatic();
    }
}

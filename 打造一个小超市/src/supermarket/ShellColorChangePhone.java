package supermarket;

public class ShellColorChangePhone extends Phone{
    private boolean enableShellColorChange;

    public ShellColorChangePhone(
            String name, int Id, int count, double soldPrice, double purchasePrice, double screenSize,
            double cpuHZ, int memoryG, int storageG, String brand,String os, Merchandise gift,boolean enableShellColorChange
    ){
        super(name, Id, count, soldPrice, purchasePrice, screenSize, cpuHZ, memoryG, storageG, brand, os, gift);
        init(enableShellColorChange);
    }

    public void init(boolean enableShellColorChange){
        this.enableShellColorChange = enableShellColorChange;
    }

    public void describe(){
        super.describe();
        System.out.println("壳色随屏幕变色功能的开启状态：" + this.enableShellColorChange);
    }

    public double buy(int count){
        System.out.println("ShellColorChangePhone类里的buy(int count)被调用了");
        if(count < 2){
            System.out.println("抱歉！生意不好做！该变色壳手机不单卖！");
            return -1;
        }
        return super.buy(count);
    }

    //私有化成员变量，提供is、set方法
    public boolean isEnableShellColorChange(){return this.enableShellColorChange;}

    public void setEnableShellColorChange(boolean isEnableShellColorChange){this.enableShellColorChange = enableShellColorChange;}
}

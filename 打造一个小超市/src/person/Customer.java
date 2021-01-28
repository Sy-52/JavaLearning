package person;

public class Customer {
    private String name;
    private double money;
    private boolean isDrivingCar;

    public Customer(){
        this.name = "顾客编号" + (int)(Math.random() * 10000);
        this.money = (1 + Math.random()) * 1000;
        this.isDrivingCar = Math.random() > 0.5;
    }

    //封装了成员变量，写了接口方法
    public String getCustomerName(){return this.name;}

    public void setCustomerName(String name){this.name = name;}

    public double getCustomerMoney(){return this.money;}

    public void setCustomerMoney(double money){this.money = money;}

    public boolean getCustomerIsDrivingCar(){return this.isDrivingCar;}

    public void setCustomerIsDrivingCar(boolean isDrivingCar){this.isDrivingCar = isDrivingCar;}
}

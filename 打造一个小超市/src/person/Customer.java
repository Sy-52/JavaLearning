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

    //提供get()、set()
    public String getName(){return this.name;}

    public void setName(String name){this.name = name;}

    public double getMoney(){return this.money;}

    public void setMoney(double money){this.money = money;}

    public boolean getIsDrivingCar(){return this.isDrivingCar;}

    public void setIsDrivingCar(boolean isDrivingCar){this.isDrivingCar = isDrivingCar;}
}

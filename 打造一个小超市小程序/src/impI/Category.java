package impI;

public enum Category {
    FOOD(10, 300),
    COOK(200,200),
    SNACK(5,100),
    CLOTHES(200,1000),
    ELECTRIC(200,10000);

    int lowerPrice;
    private int higherPrice;

    Category(int lowerPrice, int higherPrice){
        this.lowerPrice = lowerPrice;
        this.higherPrice = higherPrice;
    }

    public int getLowerPrice(){return this.lowerPrice;}

    public void setLowerPrice(int lowerPrice){this.lowerPrice = lowerPrice;}

    public int getHigherPrice(){return this.higherPrice;}

    public void setHigherPrice(int higherPrice){this.higherPrice = higherPrice;}
}

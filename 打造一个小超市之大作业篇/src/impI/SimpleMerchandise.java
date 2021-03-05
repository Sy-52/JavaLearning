package impI;

import interfaces.*;

public class SimpleMerchandise implements Merchandise{
    private String name;
    private int count;
    private double purchasePrice;
    private double soldPrice;
    private Category category;

    public SimpleMerchandise(String name, int count, double purchasePrice, double soldPrice, Category category){
        this.name = name;
        this.count = count;
        this.purchasePrice = purchasePrice;
        this.soldPrice = soldPrice;
        this.category = category;
    }

    public void buy(int count){
        // >> TODO 思考题：如果顾客所要购买的商品数量超过商品库存，怎么写？
        this.count -= count;
    }

    public void putBack(int count){ this.count += count; }

    @Override
    public String getName() { return name; }

    @Override
    public int getCount() { return count; }

    @Override
    public double getPurchasePrice() { return purchasePrice; }

    @Override
    public double getSoldPrice() { return soldPrice; }

    @Override
    public Category getCategory() { return category; }
}

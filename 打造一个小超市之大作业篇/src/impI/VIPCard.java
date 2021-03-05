package impI;

import interfaces.*;

// >> TODO 枚举其实就是一个类，只是说实例的个数是固定的。这种特性是比较契合打折卡的这种需求的，VIP卡就那么几个级别...
public enum VIPCard implements Card{
    Level0(1),
    Level1(0.95),
    Level2(0.9),
    Level3(0.85),
    Level4(0.8),
    Level5(0.75);

    private double discount;

    VIPCard(double discount){ this.discount = discount;}

    @Override
    public double processCardDiscount(double totalCostAfterDiscount) {
        return totalCostAfterDiscount * (1 - discount);
    }
}

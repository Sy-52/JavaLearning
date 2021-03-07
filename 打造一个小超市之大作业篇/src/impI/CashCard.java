package impI;

import interfaces.Card;
import interfaces.Customer;

public class CashCard implements Card {

    private double point;

    public CashCard(double point){this.point = point;}

    @Override
    public double processCardDiscount(double totalCost, double totalCostAfterDiscount, Customer customer, ShoppingCart shoppingCart) {
        if(point < totalCostAfterDiscount){
            point = 0;
            return point;
        }else{
            point -= totalCostAfterDiscount;
            return totalCostAfterDiscount;
        }
    }
}

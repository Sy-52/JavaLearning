package interfaces;

import impI.ShoppingCart;

import javax.crypto.CipherSpi;

public interface Shopman {
    void serveCustomer(Customer customer);

    void checkOut(Customer customer, ShoppingCart shoppingCart);
}

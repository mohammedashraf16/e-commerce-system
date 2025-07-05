package org.example;

import java.util.Calendar; // CHANGED: Import Calendar
import java.util.Date;     // CHANGED: Import Date

public class Main {

    private static Date createDate(int field, int amount) {
        Calendar cal = Calendar.getInstance();
        cal.add(field, amount);
        return cal.getTime();
    }

    public static void main(String[] args) {
        ECommerceFacade eCommerce = new ECommerceFacade();
        Product cheese = new ExpirableProduct("Cheese", 50.0, 10, 200, createDate(Calendar.MONTH, 6)); // Expires in 6 months
        Product biscuits = new ExpirableProduct("Biscuits", 25.0, 20, 350, createDate(Calendar.YEAR, 1)); // Expires in 1 year
        Product tv = new ShippableProduct("4K TV", 2000.0, 5, 15000);
        Product scratchCard = new ConcreteProduct("Mobile Scratch Card", 100.0, 50);
        Product expiredYogurt = new ExpirableProduct("Expired Yogurt", 10.0, 5, 150, createDate(Calendar.DAY_OF_MONTH, -1)); // Expired yesterday


        Customer customer = new Customer("Ahmed", 5000.0);
        System.out.println("Initial Balance for " + customer.getName() + ": " + customer.getBalance() + "\n");

        System.out.println("======= [TEST CASE 1: Successful Checkout] =======");
        Cart cart1 = new Cart();
        cart1.addProduct(cheese, 2);
        cart1.addProduct(tv, 1);
        cart1.addProduct(scratchCard, 3);

        try {
            eCommerce.checkout(customer, cart1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("\n======= [END TEST CASE 1] =======\n");

        System.out.println("======= [TEST CASE 2: Insufficient Balance] =======");
        Cart cart2 = new Cart();
        cart2.addProduct(tv, 2);

        try {
            eCommerce.checkout(customer, cart2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("\n======= [END TEST CASE 2] =======\n");


        System.out.println("======= [TEST CASE 3: Product Out of Stock] =======");
        Cart cart3 = new Cart();
        cart3.addProduct(tv, 10);

        try {
            eCommerce.checkout(customer, cart3);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("\n======= [END TEST CASE 3] =======\n");


        System.out.println("======= [TEST CASE 4: Expired Product] =======");
        Cart cart4 = new Cart();
        cart4.addProduct(expiredYogurt, 1);

        try {
            eCommerce.checkout(customer, cart4);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("\n======= [END TEST CASE 4] =======\n");


        System.out.println("======= [TEST CASE 5: Empty Cart] =======");
        Cart cart5 = new Cart();
        try {
            eCommerce.checkout(customer, cart5);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("\n======= [END TEST CASE 5] =======\n");
    }
}
package org.example;

import java.util.LinkedHashMap;
import java.util.Map;

class ECommerceFacade {
    private final ShippingService shippingService;
    private static final double SHIPPING_FEE = 30.0;

    public ECommerceFacade() {
        this.shippingService = new ShippingService();
    }

    public void checkout(Customer customer, Cart cart) throws CartEmptyException, ProductUnavailableException, InsufficientBalanceException {
        if (cart.isEmpty()) {
            throw new CartEmptyException("Error: Cart is empty. Cannot checkout.");
        }
        Map<Product, Integer> items = cart.getItems();
        double subtotal = 0;
        Map<Product, Integer> shippableItems = new LinkedHashMap<>();

        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            if (!product.isAvailable(quantity)) {
                throw new ProductUnavailableException("Error: Product '" + product.getName() + "' is out of stock or expired.");
            }
            subtotal += product.getPrice() * quantity;
            if (product instanceof Shippable) {
                shippableItems.put(product, quantity);
            }
        }

        double shippingFee = shippableItems.isEmpty() ? 0 : SHIPPING_FEE;
        double totalAmount = subtotal + shippingFee;

        if (customer.getBalance() < totalAmount) {
            throw new InsufficientBalanceException("Error: Insufficient balance. Required: " + totalAmount + ", Available: " + customer.getBalance());
        }

        customer.debit(totalAmount);
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            entry.getKey().reduceStock(entry.getValue());
        }
        shippingService.ship(shippableItems);
        printReceipt(items, subtotal, shippingFee, totalAmount, customer.getBalance());
    }

    private void printReceipt(Map<Product, Integer> items, double subtotal, double shippingFee, double totalAmount, double newBalance) {
        System.out.println("** Checkout Receipt **");
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            System.out.printf("%dx %-15s %.2f\n", entry.getValue(), entry.getKey().getName(), entry.getKey().getPrice() * entry.getValue());
        }
        System.out.println("-------------------------");
        System.out.printf("%-18s %.2f\n", "Subtotal", subtotal);
        System.out.printf("%-18s %.2f\n", "Shipping", shippingFee);
        System.out.printf("%-18s %.2f\n", "Paid Amount", totalAmount);
        System.out.println("-------------------------");
        System.out.printf("%-18s %.2f\n", "New Balance", newBalance);
    }
}
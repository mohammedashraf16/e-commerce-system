package org.example;

import java.util.Date;

class ExpirableProduct extends Product implements Shippable {
    private final Date expiryDate;
    private final double weight;

    public ExpirableProduct(String name, double price, int quantity, double weight, Date expiryDate) {
        super(name, price, quantity);
        this.weight = weight;
        this.expiryDate = expiryDate;
    }

    public boolean isExpired() {
        return new Date().after(this.expiryDate);
    }

    @Override
    public boolean isAvailable(int requestedQuantity) {
        return this.quantity >= requestedQuantity && !isExpired();
    }

    @Override
    public double getWeight() {
        return this.weight;
    }
}
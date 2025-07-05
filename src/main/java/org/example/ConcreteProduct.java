package org.example;

class ConcreteProduct extends Product {
    public ConcreteProduct(String name, double price, int quantity) {
        super(name, price, quantity);
    }

    @Override
    public boolean isAvailable(int requestedQuantity) {
        return this.quantity >= requestedQuantity;
    }
}

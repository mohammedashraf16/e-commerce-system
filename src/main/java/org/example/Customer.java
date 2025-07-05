package org.example;

class Customer {
    private final String name;
    private double balance;

    public Customer(String name, double balance) {
        this.name = name;
        this.balance = balance;
    }

    public void debit(double amount) {
        if (amount <= balance) {
            this.balance -= amount;
        }
    }

    public String getName() { return name; }
    public double getBalance() { return balance; }
}

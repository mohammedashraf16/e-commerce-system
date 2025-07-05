package org.example;

class CartEmptyException extends Exception {
    public CartEmptyException(String message) { super(message); }
}
class ProductUnavailableException extends Exception {
    public ProductUnavailableException(String message) { super(message); }
}
class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String message) { super(message); }
}

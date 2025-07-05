package org.example;

import java.util.Map;

class ShippingService {
    public void ship(Map<Product, Integer> shippableItems) {
        if (shippableItems.isEmpty()) {
            return;
        }
        System.out.println("** Shipment Notice **");
        double totalWeight = 0;
        for (Map.Entry<Product, Integer> entry : shippableItems.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            double itemWeight = ((Shippable) product).getWeight() * quantity;
            totalWeight += itemWeight;
            System.out.printf("%dx %-15s %dg\n", quantity, product.getName(), (int)itemWeight);
        }
        System.out.printf("Total package weight: %.2fkg\n", totalWeight / 1000.0);
        System.out.println("-------------------------");
    }
}
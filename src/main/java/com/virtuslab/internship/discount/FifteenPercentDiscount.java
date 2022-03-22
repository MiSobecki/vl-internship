package com.virtuslab.internship.discount;

import com.virtuslab.internship.product.Product;
import com.virtuslab.internship.receipt.Receipt;

import java.math.BigDecimal;

public class FifteenPercentDiscount implements Discount {

    public static String NAME = "FifteenPercentDiscount";

    @Override
    public Receipt apply(Receipt receipt) {
        var totalPrice = receipt.totalPrice().multiply(BigDecimal.valueOf(0.85));
        var discounts = receipt.discounts();
        discounts.add(NAME);
        return new Receipt(receipt.entries(), discounts, totalPrice);
    }

    @Override
    public boolean shouldApply(Receipt receipt) {
        int numberOfGrainProducts = receipt.entries().stream().reduce(0, (subtotal, element) -> element
                .product()
                .type()
                .compareTo(Product.Type.GRAINS) == 0 ? subtotal + element.quantity() : subtotal, Integer::sum);

        return numberOfGrainProducts >= 3;
    }
}

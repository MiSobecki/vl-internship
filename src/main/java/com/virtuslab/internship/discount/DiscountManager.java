package com.virtuslab.internship.discount;

import com.virtuslab.internship.receipt.Receipt;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DiscountManager {

    private List<Discount> discounts;

    private Receipt applyDiscount(Receipt receipt) {
        for (Discount discount : discounts) {
            if (discount.shouldApply(receipt)) receipt = discount.apply(receipt);
        }

        return receipt;
    }

    public Receipt applyAllDiscounts(Receipt receipt) {
        discounts = Stream.of(
                new FifteenPercentDiscount(),
                new TenPercentDiscount()
        ).collect(Collectors.toList());

        return applyDiscount(receipt);
    }

    public Receipt applyTenPercentDiscount(Receipt receipt) {
        discounts = Stream.of(
                new TenPercentDiscount()
        ).collect(Collectors.toList());

        return applyDiscount(receipt);
    }
}

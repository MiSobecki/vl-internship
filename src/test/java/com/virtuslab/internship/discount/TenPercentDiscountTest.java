package com.virtuslab.internship.discount;

import com.virtuslab.internship.product.ProductDb;
import com.virtuslab.internship.receipt.Receipt;
import com.virtuslab.internship.receipt.ReceiptEntry;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TenPercentDiscountTest {

    @Test
    void shouldApply10PercentDiscountWhenPriceIsAbove50() {
        // Given
        var productDb = new ProductDb();
        var cheese = productDb.getProduct("Cheese");
        var steak = productDb.getProduct("Steak");
        List<ReceiptEntry> receiptEntries = new ArrayList<>();
        receiptEntries.add(new ReceiptEntry(cheese, 1));
        receiptEntries.add(new ReceiptEntry(steak, 1));

        var receipt = new Receipt(receiptEntries);
        var discountManager = new DiscountManager();
        var expectedTotalPrice = cheese.price().add(steak.price()).multiply(BigDecimal.valueOf(0.9));

        // When
        var receiptAfterDiscounts = discountManager.applyTenPercentDiscount(receipt);

        // Then
        assertEquals(expectedTotalPrice, receiptAfterDiscounts.totalPrice());
        assertEquals(1, receiptAfterDiscounts.discounts().size());
    }

    @Test
    void shouldNotApply10PercentDiscountWhenPriceIsBelow50() {
        // Given
        var productDb = new ProductDb();
        var cheese = productDb.getProduct("Cheese");
        List<ReceiptEntry> receiptEntries = new ArrayList<>();
        receiptEntries.add(new ReceiptEntry(cheese, 2));

        var receipt = new Receipt(receiptEntries);
        var discountManager = new DiscountManager();
        var expectedTotalPrice = cheese.price().multiply(BigDecimal.valueOf(2));

        // When
        var receiptAfterDiscounts = discountManager.applyTenPercentDiscount(receipt);

        // Then
        assertEquals(expectedTotalPrice, receiptAfterDiscounts.totalPrice());
        assertEquals(0, receiptAfterDiscounts.discounts().size());
    }
}

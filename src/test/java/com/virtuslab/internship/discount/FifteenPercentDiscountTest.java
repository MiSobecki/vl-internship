package com.virtuslab.internship.discount;

import com.virtuslab.internship.product.ProductDb;
import com.virtuslab.internship.receipt.Receipt;
import com.virtuslab.internship.receipt.ReceiptEntry;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FifteenPercentDiscountTest {

    @Test
    @DisplayName("Should apply all discounts when there are at least 3 grain products and " +
            "total price after 15% discount is over 50")
    void shouldApply15PercentDiscountTest() {
        // Given
        var productDb = new ProductDb();
        var bread = productDb.getProduct("Bread");

        List<ReceiptEntry> receiptEntries = new ArrayList<>();
        receiptEntries.add(new ReceiptEntry(bread, 12));

        var receipt = new Receipt(receiptEntries);
        var discountManager = new DiscountManager();
        var expectedTotalPrice = bread.price()
                .multiply(BigDecimal.valueOf(12))
                .multiply(BigDecimal.valueOf(0.85))
                .multiply(BigDecimal.valueOf(0.90));

        // When
        var receiptAfterDiscounts = discountManager.applyAllDiscounts(receipt);

        // Then
        assertEquals(expectedTotalPrice, receiptAfterDiscounts.totalPrice());
        assertEquals(2, receiptAfterDiscounts.discounts().size());
    }

    @Test
    @DisplayName("Should not apply all discounts when there aren't at least 3 grain products or " +
            "total price after 15% discount is less than 50")
    void shouldNotApply15PercentDiscountTest() {
        // Given
        var productDb = new ProductDb();
        var bread = productDb.getProduct("Bread");

        List<ReceiptEntry> receiptEntries = new ArrayList<>();
        receiptEntries.add(new ReceiptEntry(bread, 2));

        var receipt = new Receipt(receiptEntries);
        var discountManager = new DiscountManager();
        var expectedTotalPrice = bread.price().multiply(BigDecimal.valueOf(2));

        // When
        var receiptAfterDiscounts = discountManager.applyAllDiscounts(receipt);

        // Then
        assertEquals(expectedTotalPrice, receiptAfterDiscounts.totalPrice());
        assertEquals(0, receiptAfterDiscounts.discounts().size());
    }
}

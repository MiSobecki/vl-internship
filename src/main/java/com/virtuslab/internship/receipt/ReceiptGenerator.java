package com.virtuslab.internship.receipt;

import com.virtuslab.internship.basket.Basket;
import com.virtuslab.internship.product.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ReceiptGenerator {

    public Receipt generate(Basket basket) {
        List<ReceiptEntry> receiptEntries;

        List<Product> products = basket.getProducts();

        Map<Product, Integer> productsWithQty = new HashMap<>();

        products.forEach(product -> productsWithQty.merge(product, 1, Integer::sum));

        receiptEntries = productsWithQty.entrySet()
                .stream()
                .map(product -> new ReceiptEntry(product.getKey(), product.getValue()))
                .collect(Collectors.toList());

        return new Receipt(receiptEntries);
    }
}

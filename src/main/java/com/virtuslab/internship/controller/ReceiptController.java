package com.virtuslab.internship.controller;

import com.virtuslab.internship.basket.Basket;
import com.virtuslab.internship.discount.DiscountManager;
import com.virtuslab.internship.receipt.Receipt;
import com.virtuslab.internship.receipt.ReceiptGenerator;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReceiptController {

    public ReceiptController() {}

    @PostMapping("basket")
    public Receipt createReceipt(@RequestBody Basket basket) {
        ReceiptGenerator receiptGenerator = new ReceiptGenerator();

        Receipt receipt = receiptGenerator.generate(basket);

        DiscountManager discountManager = new DiscountManager();

        receipt = discountManager.applyAllDiscounts(receipt);

        return receipt;
    }

    @GetMapping("lol")
    public int getTest() {
        return 1;
    }

}

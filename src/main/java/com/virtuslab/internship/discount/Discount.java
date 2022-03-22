package com.virtuslab.internship.discount;

import com.virtuslab.internship.receipt.Receipt;

public interface Discount {

    // I have done this by interface, because maybe there will be discount applied to only few items in the future
    Receipt apply(Receipt receipt);
    boolean shouldApply(Receipt receipt);
}

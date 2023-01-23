package com.special.receipt.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ReceiptForm {

    private String fromName;
    private String toName;
    private List<ReceiptMenuForm> receiptMenuList;
}

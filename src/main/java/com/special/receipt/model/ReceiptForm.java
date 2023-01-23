package com.special.receipt.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReceiptForm {

    private String fromName;
    private String toName;
    private List<String> menu;
    private List<String> money;
}

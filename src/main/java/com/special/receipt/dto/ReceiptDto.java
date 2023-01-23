package com.special.receipt.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReceiptDto {

    private String menu;
    private String money;
    private String totalMoney;

    public static ReceiptDto from(String menu, String money) {
        return ReceiptDto.builder()
                .menu(menu)
                .money(money)
                .build();
    }
}

package com.special.receipt.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class ReceiptMenu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String menu;

    @Column
    private Long money;

    private String fromName;

    private String toName;


    public static ReceiptMenu of(String menu, Long money, String fromName, String toName) {
        return ReceiptMenu.builder()
                .menu(menu)
                .money(money)
                .fromName(fromName)
                .toName(toName)
                .build();
    }
}

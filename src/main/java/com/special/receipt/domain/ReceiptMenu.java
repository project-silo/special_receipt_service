package com.special.receipt.domain;

import com.special.receipt.model.ReceiptMenuForm;
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

    public static ReceiptMenu of(ReceiptMenuForm form) {
        return ReceiptMenu.builder()
                .menu(form.getMenu())
                .money(form.getMoney())
                .build();
    }
}

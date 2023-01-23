package com.special.receipt.domain;

import com.special.receipt.model.ReceiptForm;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Receipt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String  fromName;

    @Column
    private String toName;

    @OneToMany
    private List<ReceiptMenu> receiptMenu;

    @CreatedDate
    private LocalDateTime createdAt;

    public static Receipt of(ReceiptForm receiptForm) {
        return Receipt.builder()
                .fromName(receiptForm.getFromName())
                .toName(receiptForm.getToName())
                .receiptMenu(receiptForm.getReceiptMenuList()
                        .stream().map(ReceiptMenu::of)
                        .collect(Collectors.toList()))
                .createdAt(LocalDateTime.now())
                .build();
    }
}

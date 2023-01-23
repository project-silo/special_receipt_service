package com.special.receipt.domain;

import com.special.receipt.model.ReceiptForm;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ReceiptMenu> receiptMenu;

    @CreatedDate
    private LocalDateTime createdAt;

    public static Receipt of(ReceiptForm receiptForm) {
        String fromName= receiptForm.getFromName();
        String toName = receiptForm.getToName();

        List<Long> money = new ArrayList<>();
        for (String m : receiptForm.getMoney()) {
            m = m.trim();
            money.add(Long.parseLong(m));
        }

        List<ReceiptMenu> list = new ArrayList<>();
        for (int i = 0; i < receiptForm.getMenu().size(); i++) {
            list.add(ReceiptMenu.of(receiptForm.getMenu().get(i), money.get(i), fromName, toName));
        }

        return Receipt.builder()
                .fromName(fromName)
                .toName(toName)
                .receiptMenu(list)
                .createdAt(LocalDateTime.now())
                .build();
    }
}

package com.special.receipt.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class LifeQuotes {
    @Id
    private Long id;

    @Column
    private String lifeQuote;

    @Column
    private String name;
}

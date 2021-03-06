package com.learning.walletmgmt.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@EqualsAndHashCode
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Wallet {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String walletName;
    private double walletBalanceFund;

}

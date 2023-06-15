package com.ahkar.ewallet.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "wallet")
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public Wallet(Long userId, Double amount) {
        this.userId = userId;
        this.amount = amount;
    }

    public Wallet(Long userId, Double amount, LocalDateTime createdAt) {
        this.userId = userId;
        this.amount = amount;
        this.createdAt = createdAt;
    }

}

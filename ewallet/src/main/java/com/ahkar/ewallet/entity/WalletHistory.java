package com.ahkar.ewallet.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "history")
public class WalletHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "wallet_id")
    private Long walletId;

    @Column(name = "before_amount")
    private Double beforeAmount;

    @Column(name = "after_amount")
    private Double afterAmount;

    @Column(name = "reason")
    private String reason;

    @Column(name = "created_at")

    private LocalDateTime createdAt;

    public WalletHistory(Long userId, Long walletId, Double beforeAmount, Double afterAmount, String reason, LocalDateTime createdAt) {
        this.userId = userId;
        this.walletId = walletId;
        this.beforeAmount = beforeAmount;
        this.afterAmount = afterAmount;
        this.reason = reason;
        this.createdAt = createdAt;
    }
}

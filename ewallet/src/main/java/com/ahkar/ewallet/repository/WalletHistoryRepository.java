package com.ahkar.ewallet.repository;

import com.ahkar.ewallet.entity.WalletHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WalletHistoryRepository extends JpaRepository<WalletHistory,Long> {
    List<WalletHistory> findByUserId(Long userId);
}

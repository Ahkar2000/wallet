package com.ahkar.ewallet.service;

import com.ahkar.ewallet.entity.WalletHistory;

import java.util.List;

public interface WalletHistoryService {
    List<WalletHistory> getAllHistory();
    List<WalletHistory> getByUserId(Long userId);
}

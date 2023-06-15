package com.ahkar.ewallet.service.imp;

import com.ahkar.ewallet.entity.WalletHistory;
import com.ahkar.ewallet.repository.WalletHistoryRepository;
import com.ahkar.ewallet.service.WalletHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WalletHistoryServiceImp implements WalletHistoryService {
    @Autowired
    WalletHistoryRepository walletHistoryRepository;
    @Override
    public List<WalletHistory> getAllHistory() {
        List<WalletHistory> walletHistories = walletHistoryRepository.findAll();
        return walletHistories;
    }

    @Override
    public List<WalletHistory> getByUserId(Long userId) {
        List<WalletHistory> walletHistories = walletHistoryRepository.findByUserId(userId);
        return walletHistories;
    }
}

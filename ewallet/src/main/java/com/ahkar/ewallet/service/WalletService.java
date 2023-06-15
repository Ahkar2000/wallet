package com.ahkar.ewallet.service;

import com.ahkar.ewallet.entity.Wallet;

import java.util.List;

public interface WalletService {
    List<Wallet> getWalletList();
    Wallet create(Wallet wallet);
    Wallet cashIn(Long id,Wallet wallet);
    Wallet getById(Long id);
    Wallet cashOut(Long id,Wallet wallet);
    Wallet getByUserId(Long userId);
    void updateAmount(Wallet walletRequest);
}

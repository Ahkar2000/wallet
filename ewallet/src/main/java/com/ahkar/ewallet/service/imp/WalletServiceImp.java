package com.ahkar.ewallet.service.imp;

import com.ahkar.ewallet.entity.Wallet;
import com.ahkar.ewallet.entity.WalletHistory;
import com.ahkar.ewallet.repository.WalletHistoryRepository;
import com.ahkar.ewallet.repository.WalletRepository;
import com.ahkar.ewallet.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class WalletServiceImp implements WalletService {

    @Autowired
    WalletRepository walletRepository;

    @Autowired
    WalletHistoryRepository walletHistoryRepository;
    @Override
    public List<Wallet> getWalletList() {
        List<Wallet> walletList = walletRepository.findAll();
        return walletList;
    }

    @Override
    public Wallet create(Wallet wallet) {
        Wallet walletCheck = findByUserId(wallet.getUserId());
        if(walletCheck == null){
            wallet.setCreatedAt(LocalDateTime.now());
            walletRepository.save(wallet);
            return wallet;
        }
        return null;
    }

    @Override
    public Wallet cashIn(Long id, Wallet wallet) {
        Wallet oldWallet = walletRepository.findById(id).orElse(null);
        if(oldWallet != null){
            if(oldWallet.getUserId() == wallet.getUserId()){
                insertIntoHistory(wallet.getUserId(),id,oldWallet.getAmount(), oldWallet.getAmount() + wallet.getAmount(),"Cash In");
                oldWallet.setAmount(oldWallet.getAmount() + wallet.getAmount());
                walletRepository.save(oldWallet);
                return oldWallet;
            }
        }
        return null;
    }

    @Override
    public Wallet getById(Long id) {
        return walletRepository.findById(id).orElse(null);
    }

    @Override
    public Wallet cashOut(Long id, Wallet wallet) {
        Wallet oldWallet = walletRepository.findById(id).orElse(null);
        if(oldWallet != null){
            if(oldWallet.getUserId() == wallet.getUserId()){
                if(oldWallet.getAmount() > wallet.getAmount()){
                    insertIntoHistory(wallet.getUserId(),id,oldWallet.getAmount(), oldWallet.getAmount() - wallet.getAmount(),"Cash Out");
                    oldWallet.setAmount(oldWallet.getAmount() - wallet.getAmount());
                    walletRepository.save(oldWallet);
                    return oldWallet;
                }
            }
        }
        return null;
    }

    @Override
    public Wallet getByUserId(Long userId) {
        Wallet wallet = findByUserId(userId);
        if(wallet != null) return wallet;
        return null;
    }

    @Override
    public void updateAmount(Wallet walletRequest) {
        Wallet wallet = walletRepository.findByUserId(walletRequest.getUserId()).orElse(null);
        insertIntoHistory(wallet.getUserId(),wallet.getId(),wallet.getAmount(),walletRequest.getAmount(),"Booking");
        wallet.setAmount(walletRequest.getAmount());
        walletRepository.save(wallet);
    }

    private Wallet findByUserId(Long userId){
        Wallet wallet = walletRepository.findByUserId(userId).orElse(null);
        if(wallet != null){
            return wallet;
        }
        return null;
    }

    private void insertIntoHistory(Long userId,Long walletId,Double beforeAmount,Double afterAmount,String reason){
        WalletHistory walletHistory = new WalletHistory();
        walletHistory.setUserId(userId);
        walletHistory.setWalletId(walletId);
        walletHistory.setBeforeAmount(beforeAmount);
        walletHistory.setAfterAmount(afterAmount);
        walletHistory.setCreatedAt(LocalDateTime.now());
        walletHistory.setReason(reason);
        walletHistoryRepository.save(walletHistory);
    }
}

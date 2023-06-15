package com.ahkar.ewallet.controller;

import com.ahkar.ewallet.entity.Wallet;
import com.ahkar.ewallet.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wallet")
public class WalletController {
    @Autowired
    WalletService walletService;

    @GetMapping("")
    public List<Wallet> getWalletList(){
        return walletService.getWalletList();
    }

    @PostMapping("")
    public Wallet create(@RequestBody Wallet wallet){
        return walletService.create(wallet);
    }

    @PutMapping("/update")
    public void update(@RequestBody Wallet walletRequest){
        walletService.updateAmount(walletRequest);
    }

    @GetMapping("/{id}")
    public Wallet getById(@PathVariable("id") Long id){
        return walletService.getById(id);
    }

    @GetMapping("/get-by-userId/{userId}")
    public Wallet getByUserId(@PathVariable("userId") Long userId){
        return walletService.getByUserId(userId);
    }

    @PutMapping("/cash-in/{id}")
    public Wallet cashIn(@PathVariable("id") Long id,@RequestBody Wallet wallet){
        return walletService.cashIn(id,wallet);
    }

    @PutMapping("/cash-out/{id}")
    public Wallet cashOut(@PathVariable("id") Long id,@RequestBody Wallet wallet){
        return walletService.cashOut(id,wallet);
    }
}

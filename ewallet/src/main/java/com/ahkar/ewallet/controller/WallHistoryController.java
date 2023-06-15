package com.ahkar.ewallet.controller;

import com.ahkar.ewallet.entity.WalletHistory;
import com.ahkar.ewallet.service.WalletHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/history")
public class WallHistoryController {
    @Autowired
    WalletHistoryService walletHistoryService;
    @GetMapping("")
    public List<WalletHistory> findAll(){
        return walletHistoryService.getAllHistory();
    }

    @GetMapping("/get-by-userId/{userId}")
    public List<WalletHistory> getByUserId(@PathVariable("userId") Long userId){
        return walletHistoryService.getByUserId(userId);
    }
}

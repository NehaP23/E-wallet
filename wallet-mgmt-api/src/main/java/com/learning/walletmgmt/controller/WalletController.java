package com.learning.walletmgmt.controller;

import com.learning.walletmgmt.model.Wallet;
import com.learning.walletmgmt.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/wallets")
public class WalletController {

    @Autowired
    WalletService walletService;

    @GetMapping
    public ResponseEntity<List<Wallet>> getWallets(){
         return ResponseEntity.ok(walletService.getWallets());
    }
    @PostMapping
    public ResponseEntity<Wallet> createWallet(@RequestBody Wallet wallet){
        walletService.createWallet(wallet);
        return ResponseEntity.of(Optional.of(wallet));
    }

}

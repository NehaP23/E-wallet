package com.learning.walletmgmt.controller;

import com.learning.walletmgmt.exception.FundTransferException;
import com.learning.walletmgmt.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/fund")
public class FundController {

    @Autowired
    private WalletService walletService;

    @GetMapping("/transfer")
    public void transferFund(@RequestParam("wallet1ID") long wallet1Id,
                             @RequestParam("wallet2ID") long wallet2Id,
                             @RequestParam("fundTransferAmount") double fundTransferAmount) throws Exception {
        if(wallet1Id == wallet2Id){
            throw new FundTransferException("Cannot transfer fund to same wallet");
        }
        walletService.transferFund(wallet1Id,wallet2Id,fundTransferAmount);
    }
    @GetMapping("/withdraw")
    public void withDrawFund(@RequestParam("walletID") long wallet1Id,
                             @RequestParam("fundTransferAmount") double fundTransferAmount) throws Exception {
        walletService.withDrawFund(wallet1Id,fundTransferAmount);
    }
    @GetMapping("/addFund")
    public void addFund(@RequestParam("walletID") long wallet1Id,
                             @RequestParam("fundAmount") double fundAmount) throws Exception {
        walletService.addFund(wallet1Id,fundAmount);
    }
}

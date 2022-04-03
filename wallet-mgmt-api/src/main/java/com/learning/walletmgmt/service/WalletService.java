package com.learning.walletmgmt.service;

import com.learning.walletmgmt.exception.FundTransferException;
import com.learning.walletmgmt.model.Wallet;
import com.learning.walletmgmt.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class WalletService {

    @Autowired
    WalletRepository walletRepository;

    private Wallet getWalletByID(Long walletId) throws Exception {
        Optional<Wallet> walletByID = walletRepository.findById(walletId);
        if(walletByID.isPresent()){
            return walletByID.get();
        }
        throw new Exception("Invalid wallet id passed");
    }
    private void withDrawFundAmount(Wallet wallet,
                                    double fundTransferAmount) {
        wallet.setWalletBalanceFund(wallet.getWalletBalanceFund()-fundTransferAmount);
        updateWallet(wallet);
    }
    @Transactional
    private void transferFundAmount(Wallet wallet1,
                                    Wallet wallet2,
                                    double fundTransferAmount) {
        wallet1.setWalletBalanceFund(wallet1.getWalletBalanceFund()-fundTransferAmount);
        wallet2.setWalletBalanceFund(wallet2.getWalletBalanceFund()+fundTransferAmount);
        updateWallet(wallet1);
        updateWallet(wallet2);
    }
    public void transferFund(long wallet1Id,
                             long wallet2Id,
                             double fundTransferAmount) throws Exception {
        Wallet wallet1= getWalletByID(wallet1Id);
        Wallet wallet2= getWalletByID(wallet2Id);
        if(wallet1.getWalletBalanceFund()-fundTransferAmount < 0){
            throw new FundTransferException("Transfer amount making wallet1 as zero, Please use another amount ");
        }else{
            transferFundAmount(wallet1,wallet2,fundTransferAmount);
        }
    }
    public void withDrawFund(long wallet1Id,
                             double fundTransferAmount) throws Exception {
        Wallet wallet= getWalletByID(wallet1Id);
        if(wallet.getWalletBalanceFund()-fundTransferAmount < 0){
            throw new FundTransferException("Withdraw amount making wallet as zero, Please use another amount ");
        }else{
            withDrawFundAmount(wallet,fundTransferAmount);
        }
    }
    public void addFund(long wallet1Id,
                        double fundAmount) throws Exception {
        Wallet wallet= getWalletByID(wallet1Id);
        wallet.setWalletBalanceFund(wallet.getWalletBalanceFund()+fundAmount);
        updateWallet(wallet);
    }
    private void updateWallet(Wallet wallet){
        walletRepository.save(wallet);
    }
    public void createWallet(Wallet wallet){
        walletRepository.save(wallet);
    }
    public List<Wallet> getWallets(){
        return walletRepository.findAll();
    }


}

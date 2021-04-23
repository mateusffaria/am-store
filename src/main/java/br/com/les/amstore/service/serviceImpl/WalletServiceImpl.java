package br.com.les.amstore.service.serviceImpl;

import br.com.les.amstore.domain.Wallet;
import br.com.les.amstore.repository.Wallets;
import br.com.les.amstore.service.IGenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WalletServiceImpl implements IGenericService<Wallet> {
    @Autowired
    Wallets wallets;

    @Override
    public List<Wallet> findAll() {
        return wallets.findAll();
    }

    @Override
    public Wallet findById(Long id) {
        return wallets.findById(id).get();
    }

    @Override
    public Wallet saveAndFlush(Wallet object) {
        return wallets.saveAndFlush(object);
    }
}

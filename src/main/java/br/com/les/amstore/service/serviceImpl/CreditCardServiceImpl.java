package br.com.les.amstore.service.serviceImpl;

import br.com.les.amstore.domain.CreditCard;
import br.com.les.amstore.repository.CreditCards;
import br.com.les.amstore.service.IGenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditCardServiceImpl implements IGenericService<CreditCard> {
    @Autowired
    CreditCards creditCards;

    @Override
    public List<CreditCard> findAll() {
        return creditCards.findAll();
    }

    @Override
    public CreditCard findById(Long id) {
        return creditCards.findById(id).get();
    }

    @Override
    public CreditCard saveAndFlush(CreditCard object) {
        return creditCards.saveAndFlush(object);
    }
}

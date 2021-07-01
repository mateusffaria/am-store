package br.com.les.amstore.service.serviceImpl;

import br.com.les.amstore.domain.Stock;
import br.com.les.amstore.repository.Stocks;
import br.com.les.amstore.service.IStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockServiceImpl implements IStockService {

    @Autowired
    private Stocks stocks;

    @Override
    public List<Stock> findAll() {
        return stocks.findAll();
    }

    @Override
    public Stock findById(Long id) {
        return stocks.findById(id).get();
    }

    @Override
    public Stock saveAndFlush(Stock object) {
        Integer actualAmount = object.getGame().getAmount();
        Integer actualAmountAvailable = object.getGame().getAmountAvailable();

        object.getGame().setAmount(object.getAmoInteger() + actualAmount);
        object.getGame().setAmountAvailable(object.getAmoInteger() + actualAmountAvailable);

        object.getGame().setActive(true);

        return stocks.saveAndFlush(object);
    }
}

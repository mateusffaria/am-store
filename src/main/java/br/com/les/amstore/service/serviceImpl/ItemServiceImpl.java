package br.com.les.amstore.service.serviceImpl;

import br.com.les.amstore.domain.Item;
import br.com.les.amstore.repository.Items;
import br.com.les.amstore.service.IGenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements IGenericService<Item> {
    @Autowired
    Items items;


    @Override
    public List<Item> findAll() {
        return items.findAll();
    }

    @Override
    public Item findById(Long id) {
        return items.findById(id).get();
    }

    @Override
    public Item saveAndFlush(Item object) {
        return items.saveAndFlush(object);
    }
}

package br.com.les.amstore.service.serviceImpl;

import br.com.les.amstore.domain.Order;
import br.com.les.amstore.domain.Status;
import br.com.les.amstore.repository.Orders;
import br.com.les.amstore.repository.Statuses;
import br.com.les.amstore.service.IGenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements IGenericService<Order> {
    @Autowired
    Orders orders;

    @Autowired
    Statuses statuses;

    @Override
    public List<Order> findAll() {
        return orders.findAll();
    }

    @Override
    public Order findById(Long id) {
        return orders.findById(id).get();
    }

    @Override
    public Order saveAndFlush(Order object) {
        Status status = statuses.findByStatus("EM PROCESSAMENTO");
        object.setStatus(status);

        return orders.saveAndFlush(object);
    }
}

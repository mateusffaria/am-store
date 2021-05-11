package br.com.les.amstore.service;

import br.com.les.amstore.domain.Order;
import org.springframework.validation.BindingResult;

public interface IOrderService extends IGenericService<Order> {
    public Order updateOrder(Order object);
    Order saveAndFlush(Order order, BindingResult result);
}

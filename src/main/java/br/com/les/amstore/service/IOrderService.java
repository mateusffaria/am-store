package br.com.les.amstore.service;

import br.com.les.amstore.domain.Order;

public interface IOrderService extends IGenericService<Order> {
    public Order updateOrder(Order object);
}

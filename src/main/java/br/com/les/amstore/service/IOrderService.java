package br.com.les.amstore.service;

import br.com.les.amstore.domain.Order;
import org.springframework.validation.BindingResult;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public interface IOrderService extends IGenericService<Order> {
    public Order updateOrder(Order object);
    Order saveAndFlush(Order order, BindingResult result);
    List<HashMap<String, Double>> findAllByCreatedAtBetween(Date dateInitial, Date dateFinal, Integer searchType);
    List<HashMap<String, Double>> fillCardsIndex();
}

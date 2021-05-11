package br.com.les.amstore.service.serviceImpl;

import br.com.les.amstore.domain.Order;
import br.com.les.amstore.domain.Status;
import br.com.les.amstore.repository.CreditCards;
import br.com.les.amstore.repository.Customers;
import br.com.les.amstore.repository.Orders;
import br.com.les.amstore.repository.Statuses;
import br.com.les.amstore.service.IGenericService;
import br.com.les.amstore.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class OrderServiceImpl implements IOrderService {
    @Autowired
    Orders orders;

    @Autowired
    Statuses statuses;

    @Autowired
    CreditCards creditCards;

    @Autowired
    Customers customers;

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
        orders.saveAndFlush(object);
        return object;
    }

    @Override
    public Order saveAndFlush(Order order, BindingResult result) {
        fillOrderObject(order, result);

        if(result.hasErrors())
            return order;

        orders.saveAndFlush(order);

        order.getCustomer().getCart().getItemList().clear();

        customers.saveAndFlush(order.getCustomer());
        return order;
    }
    public Order fillOrderObject(Order order, BindingResult result){
        Status status = statuses.findByStatus("EM PROCESSAMENTO");
        order.setItemList(order.getCustomer().getCart().getItemList());
        Double costs = order.getShippingTax() + order.getItemList().stream().mapToDouble(i -> i.getGame().getPrice() * i.getAmount().doubleValue()).sum();

        order.getPaymentMethodList().forEach(p -> p.setCreditCard(creditCards.findById(p.getCreditCard().getId()).get()));
        order.setStatus(status);
        order.setTotal(BigDecimal.valueOf(costs - order.getCustomer().getWallet().getValue())
                .setScale(2, RoundingMode.FLOOR)
                .doubleValue());


        if(null != order.getCoupon()){
            order.setTotal(BigDecimal.valueOf(costs - order.getCustomer().getWallet().getValue() - order.getCoupon().getValue())
                    .setScale(2, RoundingMode.FLOOR)
                    .doubleValue());
        }

        Double total = order.getPaymentMethodList().stream().mapToDouble(paymentMethod -> paymentMethod.getPaymentValue()).sum();

        if(!total.equals(order.getTotal())){
            result.addError(new ObjectError("order", "Valor total e do pagamento são diferentes"));
            return order;
        }

        order.getCustomer().getWallet().setValue(0);
        order.getCoupon().setAmount(order.getCoupon().getAmount() - 1);
        order.getItemList().forEach(g -> g.getGame().setAmount(g.getGame().getAmount() - g.getAmount()));

        return order;
    }

    @Override
    public Order updateOrder(Order order){
        return orders.saveAndFlush(order);
    }
}

package br.com.les.amstore.service.serviceImpl;

import br.com.les.amstore.domain.*;
import br.com.les.amstore.repository.*;
import br.com.les.amstore.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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

    @Autowired
    Games games;

    @Autowired
    Genders genders;

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

    @Override
    public List<HashMap<String, Double>> findAllByCreatedAtBetween(Date dateInitial, Date dateFinal, Integer searchType) {
        if(null == searchType)
            searchType = 0;

        List<Order> ordersFiltered = orders.findAllByCreatedAtBetween(dateInitial, dateFinal);

        List<HashMap<String, Double>> orderValue = new ArrayList<>();

        if(searchType.equals(0)){
            List<Game> allGames = games.findAll();

            for (Game game : allGames) {
                Double value = 0d;

                for (Order order : ordersFiltered) {
                    for (Item item : order.getItemList()) {
                        if(game.getId().equals(item.getGame().getId()))
                            value += BigDecimal.valueOf(order.getTotal()).setScale(2, RoundingMode.FLOOR)
                                    .doubleValue();
                    }
                }

                HashMap<String, Double> gameOrder = new HashMap<>();
                gameOrder.put(game.getTitle(), value);

                orderValue.add(gameOrder);
            }
        } else {
            List<Gender> allGenders = genders.findAll();

            for (Gender gender : allGenders) {
                Double value = 0d;

                for (Order order : ordersFiltered) {
                    for (Item item : order.getItemList()) {
                        if(item.getGame().getGenders().contains(gender))
                            value += BigDecimal.valueOf(order.getTotal()).setScale(2, RoundingMode.FLOOR)
                                    .doubleValue();
                    }
                }

                HashMap<String, Double> genderOrder = new HashMap<>();
                genderOrder.put(gender.getName(), value);

                orderValue.add(genderOrder);
            }
        }

        return orderValue;
    }

    @Override
    public List<HashMap<String, Double>> fillCardsIndex() {
        List<HashMap<String, Double>> cards = new ArrayList<>();

        List<Order> allOrders = orders.findAll();

        HashMap<String, Double> totalOrder = new HashMap<>();
        HashMap<String, Double> cardSingle = new HashMap<>();
        HashMap<String, Double> cardMultiple = new HashMap<>();
        Double totalSingleCreditCard = 0d;
        Double totalMultipleCreditCard = 0d;

        totalOrder.put("total", allOrders.stream().mapToDouble(o -> o.getTotal()).sum());

        for (Order order : allOrders) {
            if(order.getPaymentMethodList().size() > 1){
                totalMultipleCreditCard += 1;
            } else {
                totalSingleCreditCard += 1;
            }
        }

        cardSingle.put("Vendas (1 cartão de crédito)", totalSingleCreditCard);
        cardMultiple.put("Vendas (vários cartões de crédito)", totalMultipleCreditCard);

        cards.add(totalOrder);
        cards.add(cardSingle);
        cards.add(cardMultiple);

        return cards;
    }

    public Order fillOrderObject(Order order, BindingResult result){
        Status status = statuses.findByStatus("EM PROCESSAMENTO");
        order.setItemList(order.getCustomer().getCart().getItemList());
        Double costs = order.getShippingTax() + order.getItemList().stream()
                .mapToDouble(i -> i.getGame().getPrice() * i.getAmount().doubleValue()).sum();

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

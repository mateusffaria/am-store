package br.com.les.amstore.service;

import br.com.les.amstore.domain.Customer;

public interface ICartService {
    public void addCartItem(Customer customer, Long idGame, Integer amount);
}

package br.com.les.amstore.service;

import br.com.les.amstore.domain.Customer;
import br.com.les.amstore.domain.Game;

public interface ICartService {
    public void addCartItem(Customer customer, Long idGame, Integer amount);
}

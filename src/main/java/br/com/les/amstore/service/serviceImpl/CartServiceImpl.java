package br.com.les.amstore.service.serviceImpl;

import br.com.les.amstore.domain.Cart;
import br.com.les.amstore.domain.Customer;
import br.com.les.amstore.domain.Game;
import br.com.les.amstore.domain.Item;
import br.com.les.amstore.repository.Carts;
import br.com.les.amstore.repository.Customers;
import br.com.les.amstore.repository.Games;
import br.com.les.amstore.service.ICartService;
import br.com.les.amstore.service.IGenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements IGenericService<Cart>, ICartService {
    @Autowired
    Carts carts;

    @Autowired
    Games games;

    @Autowired
    private Customers customers;

    @Override
    public List<Cart> findAll() {
        return carts.findAll();
    }

    @Override
    public Cart findById(Long id) {
        return carts.findById(id).get();
    }

    @Override
    public Cart saveAndFlush(Cart object) {
        return carts.saveAndFlush(object);
    }

    @Override
    public void addCartItem(Customer customer, Long idGame, Integer amount) {
        Game game = games.findById(idGame).get();

        customer.getCart().getItemList().add(new Item(game, amount));
        customers.saveAndFlush(customer);

        game.setAmountAvailable(game.getAmountAvailable() - amount);

        if(game.getAmountAvailable() <= 0)
            game.setActive(false);

        games.saveAndFlush(game);
    }

    @Override
    public void removeCartItem(Customer customer, Long idItem) {

        for (int i = 0; i< customer.getCart().getItemList().size(); i++) {
            Item item = customer.getCart().getItemList().get(i);

            if(item.getId().equals(idItem)){
                customer.getCart().getItemList().remove(i);
                item.getGame().setAmountAvailable(item.getGame().getAmountAvailable() + item.getAmount());
            }
        }

        customers.saveAndFlush(customer);
    }
}

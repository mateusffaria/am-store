package br.com.les.amstore.repository;

import br.com.les.amstore.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Carts extends JpaRepository<Cart, Long> {
}

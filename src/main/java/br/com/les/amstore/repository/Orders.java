package br.com.les.amstore.repository;

import br.com.les.amstore.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Orders extends JpaRepository<Order, Long> {
}

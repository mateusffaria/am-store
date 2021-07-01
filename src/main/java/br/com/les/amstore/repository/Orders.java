package br.com.les.amstore.repository;

import br.com.les.amstore.domain.Order;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface Orders extends JpaRepository<Order, Long> {
    List<Order> findAllByCreatedAtBetweenOrderByCreatedAt(Date dateInitial, Date dateFinal);
}

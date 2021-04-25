package br.com.les.amstore.repository;

import br.com.les.amstore.domain.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditCards extends JpaRepository<CreditCard, Long> {
}

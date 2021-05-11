package br.com.les.amstore.repository;

import br.com.les.amstore.domain.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Stocks extends JpaRepository<Stock, Long> {
}

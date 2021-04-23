package br.com.les.amstore.repository;

import br.com.les.amstore.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Items extends JpaRepository<Item, Long> {
}

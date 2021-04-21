package br.com.les.amstore.repository;

import br.com.les.amstore.domain.Platform;
import br.com.les.amstore.domain.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Publishers extends JpaRepository<Publisher, Long> {
}

package br.com.les.amstore.repository;

import br.com.les.amstore.domain.Game;
import br.com.les.amstore.domain.Language;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Languages extends JpaRepository<Language, Long> {
}


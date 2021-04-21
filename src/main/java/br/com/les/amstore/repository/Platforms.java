package br.com.les.amstore.repository;

import br.com.les.amstore.domain.Game;
import br.com.les.amstore.domain.Platform;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Platforms extends JpaRepository<Platform, Long> {
}


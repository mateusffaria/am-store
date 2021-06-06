package br.com.les.amstore.repository;

import br.com.les.amstore.domain.Game;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Games extends JpaRepository<Game, Long> {
    List<Game> findAllByActiveTrue(Sort sort);
}

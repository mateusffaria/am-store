package br.com.les.amstore.repository;

import br.com.les.amstore.domain.Devolution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Devolutions extends JpaRepository<Devolution, Long> {
}

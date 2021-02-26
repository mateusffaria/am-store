package br.com.les.amstore.repository;

import br.com.les.amstore.domain.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Cities extends JpaRepository<City, Long> {
}

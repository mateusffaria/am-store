package br.com.les.amstore.repository;

import br.com.les.amstore.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface People extends JpaRepository<Person, Long> {
}

package br.com.les.amstore.repository;

import br.com.les.amstore.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface People extends JpaRepository<Person, Long> {
    Optional<Person> findByEmail(String email);
}

package br.com.les.amstore.repository;

import br.com.les.amstore.domain.Gender;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Genders extends JpaRepository<Gender, Long> {
}


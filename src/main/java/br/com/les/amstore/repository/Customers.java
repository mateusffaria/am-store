package br.com.les.amstore.repository;

import br.com.les.amstore.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Customers extends JpaRepository<Customer, Long> {
}

package br.com.les.amstore.repository;

import br.com.les.amstore.domain.CustomerType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomersType extends JpaRepository<CustomerType, Long> {
}

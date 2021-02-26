package br.com.les.amstore.repository;


import br.com.les.amstore.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Addresses extends JpaRepository<Address, Long> {
}

package br.com.les.amstore.repository;

import br.com.les.amstore.domain.AddressType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressesType extends JpaRepository<AddressType, Long> {
}

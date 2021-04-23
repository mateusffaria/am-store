package br.com.les.amstore.repository;

import br.com.les.amstore.domain.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Wallets extends JpaRepository<Wallet, Long> {
}

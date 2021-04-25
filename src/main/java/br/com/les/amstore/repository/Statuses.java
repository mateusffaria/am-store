package br.com.les.amstore.repository;

import br.com.les.amstore.domain.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Statuses extends JpaRepository<Status, Long> {
    Status findByStatus(String name);
}

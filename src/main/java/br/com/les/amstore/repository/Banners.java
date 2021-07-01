package br.com.les.amstore.repository;

import br.com.les.amstore.domain.Banner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Banners extends JpaRepository<Banner, Long> {
}

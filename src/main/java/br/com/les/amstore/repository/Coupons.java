package br.com.les.amstore.repository;

import br.com.les.amstore.domain.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Coupons extends JpaRepository<Coupon, Long> {
    List<Coupon> findByCodeAndAmountGreaterThan(String code, Integer amount);
}

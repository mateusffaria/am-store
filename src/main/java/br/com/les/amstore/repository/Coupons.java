package br.com.les.amstore.repository;

import br.com.les.amstore.domain.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Coupons extends JpaRepository<Coupon, Long> {
    Coupon findByCodeAndAmountGreaterThan(String code, Integer amount);
}

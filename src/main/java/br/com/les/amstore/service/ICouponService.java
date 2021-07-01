package br.com.les.amstore.service;

import br.com.les.amstore.domain.Coupon;

import java.util.List;

public interface ICouponService {
    List<Coupon> findByCodeAndAmountGreaterThan(String code, Integer amount);
}

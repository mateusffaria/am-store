package br.com.les.amstore.service;

import br.com.les.amstore.domain.Coupon;

public interface ICouponService {
    Coupon findByCodeAndAmountGreaterThan(String code, Integer amount);
}

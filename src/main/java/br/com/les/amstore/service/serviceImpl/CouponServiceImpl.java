package br.com.les.amstore.service.serviceImpl;

import br.com.les.amstore.domain.Coupon;
import br.com.les.amstore.repository.Coupons;
import br.com.les.amstore.service.ICouponService;
import br.com.les.amstore.service.IGenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CouponServiceImpl implements IGenericService<Coupon>, ICouponService {
    @Autowired
    Coupons coupons;

    @Override
    public List<Coupon> findAll() {
        return coupons.findAll();
    }

    @Override
    public Coupon findById(Long id) {
        return coupons.findById(id).get();
    }

    @Override
    public Coupon saveAndFlush(Coupon object) {
        return coupons.saveAndFlush(object);
    }

    @Override
    public List<Coupon> findByCodeAndAmountGreaterThan(String code, Integer amount) {
        return coupons.findByCodeAndAmountGreaterThan(code, amount = 0);
    }
}

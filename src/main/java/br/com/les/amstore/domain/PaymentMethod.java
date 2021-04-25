package br.com.les.amstore.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class PaymentMethod extends DomainEntity {

    private Double paymentValue;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "coupon_id")
    private Coupon coupon;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "credit_card_id")
    private CreditCard creditCard;

}

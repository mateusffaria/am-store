package br.com.les.amstore.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class CreditCard extends DomainEntity {

    @Column(unique = true)
    private String number;

    private String securityCode;

    private String validadeYear;

    private String validadeMonth;

    private String owner;

    private String ownerDoc;

    @ManyToOne
    @JoinColumn(name = "banner_id")
    private Banner banner;

    @OneToMany(mappedBy = "creditCard", targetEntity = PaymentMethod.class)
    private List<PaymentMethod> paymentMethodList;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}

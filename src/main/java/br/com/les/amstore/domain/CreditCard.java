package br.com.les.amstore.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@Entity
public class CreditCard extends DomainEntity {

    @Column(unique = true)
    @Size(min = 16)
    private String number;

    @Size(min = 3)
    private String securityCode;

    @Size(min = 2)
    private String validadeYear;

    @Size(min = 2)
    private String validadeMonth;

    @Size(min = 3)
    private String owner;

    @Size(min = 11)
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

package br.com.les.amstore.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "_order")
public class Order extends DomainEntity {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date releaseDate;

    private Double total;

    private String code;

    private Double shippingTax;

    @OneToOne
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @ManyToOne
    @JoinColumn(name = "address_billing_id")
    private Address addressBilling;

    @OneToOne
    private Tracking tracking;

    @ManyToMany
    private List<PaymentMethod> paymentMethodList;

    @ManyToMany
    private List<Item> itemList;
}

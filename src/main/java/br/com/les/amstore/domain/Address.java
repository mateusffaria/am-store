package br.com.les.amstore.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@Setter
public class Address extends DomainEntity{
    private String street;
    private String number;
    private String postalCode;
    private String complement;

    @ManyToOne
    @JoinColumn(name="customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name="id", insertable = false, updatable = false)
    private AddressType addressType;

    @ManyToOne
    @JoinColumn(name="id", insertable = false, updatable = false)
    private City city;
}

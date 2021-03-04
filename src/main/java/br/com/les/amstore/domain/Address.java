package br.com.les.amstore.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
public class Address extends DomainEntity{

    @NotBlank
    @NotNull
    private String street;

    @NotBlank
    @NotNull
    private String number;

    @NotBlank
    @NotNull
    private String postalCode;

    private String complement;

    @ManyToOne
    @JoinColumn(name="person_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name="address_type_id")
    private AddressType addressType;

    @ManyToOne
    @JoinColumn(name="city_id")
    private City city;
}

package br.com.les.amstore.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@Setter
public class CustomerType extends DomainEntity{
    private String title;
    private String description;

    @OneToMany(mappedBy = "customerType", targetEntity = Customer.class)
    private List<Customer> customers;
}

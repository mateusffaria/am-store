package br.com.les.amstore.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "_customer_type")
public class CustomerType extends DomainEntity{
    private String title;
    private String description;

    @OneToMany(mappedBy = "customerType", targetEntity = Customer.class)
    private List<Customer> customers;
}

package br.com.les.amstore.domain;

import lombok.*;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Collection;
import java.util.List;


@Entity
@Getter
@Setter
@Where(clause = "deleted_at is null")
@ToString
public class Customer extends Person {
    @ManyToOne
    @JoinColumn(name="customer_type_id")
    private CustomerType customerType;

    @OneToMany(mappedBy = "customer", targetEntity = Address.class)
    private List<Address> address;

    public Customer() {
        this.setRoles("ROLE_CUSTOMER");
        this.setActive(true);
    }
}

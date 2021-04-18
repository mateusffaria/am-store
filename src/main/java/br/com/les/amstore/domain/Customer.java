package br.com.les.amstore.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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

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
@Table(name = "_address_type")
public class AddressType extends DomainEntity{
    private String description;
    private String name;

    @OneToMany(mappedBy = "addressType", targetEntity = Address.class)
    private List<Address> addresses;
}

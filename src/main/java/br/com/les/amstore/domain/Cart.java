package br.com.les.amstore.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "_cart")
public class Cart extends DomainEntity{

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Item> itemList;

}

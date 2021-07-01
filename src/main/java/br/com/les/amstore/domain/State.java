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
@Table(name = "_state")
public class State extends DomainEntity{
    private String description;
    private String uf;

    @OneToMany(mappedBy = "state", targetEntity = City.class)
    private List<City> cities;
}

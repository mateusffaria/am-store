package br.com.les.amstore.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@Setter
public class State extends DomainEntity{
    private String description;

    @OneToMany(mappedBy = "state", targetEntity = City.class)
    private List<City> cities;
}

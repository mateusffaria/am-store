package br.com.les.amstore.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private String uf;

    @OneToMany(mappedBy = "state", targetEntity = City.class)
    @JsonIgnore
    private List<City> cities;
}

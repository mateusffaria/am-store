package br.com.les.amstore.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@Setter
public class City extends DomainEntity{
    private String description;


    @OneToMany(mappedBy = "city", targetEntity = Address.class)
    private List<Address> addresses;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="state_id")
    private State state;
}

package br.com.les.amstore.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class City extends DomainEntity{
    private String description;

    @ManyToOne
    @JoinColumn(name="id", insertable = false, updatable = false)
    private State state;
}

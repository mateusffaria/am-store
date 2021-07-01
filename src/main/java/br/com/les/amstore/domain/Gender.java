package br.com.les.amstore.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "_gender")
public class Gender extends DomainEntity{

    private String name;

    @ManyToMany
    private List<Game> game;
}

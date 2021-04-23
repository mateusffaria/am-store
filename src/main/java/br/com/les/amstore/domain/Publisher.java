package br.com.les.amstore.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Setter
@Getter
public class Publisher extends DomainEntity{
    private String name;

    @OneToMany(mappedBy = "publisher", targetEntity = Game.class)
    private List<Game> game;
}

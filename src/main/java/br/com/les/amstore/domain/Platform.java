package br.com.les.amstore.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@Setter
public class Platform extends DomainEntity{

    private String name;

    @OneToMany(mappedBy = "platform", targetEntity = Game.class)
    private List<Game> game;
}

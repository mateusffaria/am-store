package br.com.les.amstore.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "_banner")
public class Banner extends DomainEntity {
    private String name;
}

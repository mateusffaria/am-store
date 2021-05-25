package br.com.les.amstore.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "_status")
public class Status extends DomainEntity {
    private String status;
}

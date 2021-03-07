package br.com.les.amstore.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@MappedSuperclass
@Getter
@Setter
public abstract class DomainEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private Date createdAt;

    private String hash;


    DomainEntity() {
        this.createdAt = new Date();
        this.hash = UUID.randomUUID().toString();
    }
}

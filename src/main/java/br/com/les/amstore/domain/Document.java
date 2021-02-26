package br.com.les.amstore.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
@Getter
@Setter
public class Document extends DomainEntity{
    private String code;
    private Date validate;

    @ManyToOne
    @JoinColumn(name="id", insertable = false, updatable = false)
    private Person person;

    @ManyToOne
    @JoinColumn(name="id", insertable = false, updatable = false)
    private DocumentType documentType;
}

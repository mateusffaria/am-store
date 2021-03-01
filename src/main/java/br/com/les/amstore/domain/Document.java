package br.com.les.amstore.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class Document extends DomainEntity{
    private String code;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    @Temporal(TemporalType.DATE)
    private Date validate;

    @ManyToOne
    @JoinColumn(name="person_id")
    private Person person;

    @ManyToOne
    @JoinColumn(name="document_type_id")
    private DocumentType documentType;
}

package br.com.les.amstore.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class DocumentType extends DomainEntity{

    private String description;

    private String name;

    @OneToMany(mappedBy = "documentType", targetEntity = Document.class)
    private List<Document> documents;
}

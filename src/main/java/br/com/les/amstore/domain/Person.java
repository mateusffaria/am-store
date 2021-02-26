package br.com.les.amstore.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
public class Person extends DomainEntity {

    @OneToMany(mappedBy = "person", targetEntity = Document.class)
    private List<Document> documents;

    private String name;

    private String gender;

    private String telephone;
}

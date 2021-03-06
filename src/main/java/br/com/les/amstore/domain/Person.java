package br.com.les.amstore.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Where(clause = "deleted_at is null")
public class Person extends DomainEntity {

    @OneToMany(mappedBy = "person", targetEntity = Document.class)
    private List<Document> documents;

    @NotNull(message = "Nome é obrigatório")
    @NotBlank(message = "Nome não pode estar em branco")
    private String name;

    @NotNull(message = "Telefone é obrigatório")
    @NotBlank(message = "Telefone não pode estar em branco")
    private String telephone;

    private Date deletedAt;

    public void delete() {
        this.deletedAt = new Date();
    }
}

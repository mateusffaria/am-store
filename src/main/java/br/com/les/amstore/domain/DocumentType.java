package br.com.les.amstore.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Getter
@Setter
public class DocumentType extends DomainEntity{

    @NotNull(message = "Descrição do documento não pode ser nulo")
    @NotBlank(message = "Descrição do documento não pode ser branco")
    private String description;

    @NotNull(message = "Tipo do documento não pode ser branco")
    @NotBlank(message = "Tipo do documento não pode ser branco")
    private String name;

    @OneToMany(mappedBy = "documentType", targetEntity = Document.class)
    private List<Document> documents;
}

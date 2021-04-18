package br.com.les.amstore.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Getter
@Setter
@Where(clause = "deleted_at is null")
public class Document extends DomainEntity{
    @NotBlank(message = "Número do documento não pode ser estar em branco")
    @NotNull(message = "Número do documento não pode ser nulo")
    @Length(min = 12, message = "O Documento é inválido")
    private String code;

    @NotNull(message = "Data de expedição não pode ser nulo")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date validate;

    @ManyToOne
    @JoinColumn(name="person_id")
    private Person person;

    @NotNull(message = "Selecione um tipo de documento")
    @ManyToOne
    @JoinColumn(name="document_type_id")
    private DocumentType documentType;

    private Date deletedAt;

    public void delete() {
        this.deletedAt = new Date();
    }
}

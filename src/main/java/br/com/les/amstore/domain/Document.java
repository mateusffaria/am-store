package br.com.les.amstore.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Getter
@Setter
public class Document extends DomainEntity{
    @NotBlank(message = "Número do documento não pode ser estar em branco")
    @NotNull(message = "Número do documento não pode ser nulo")
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
}

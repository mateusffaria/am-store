package br.com.les.amstore.domain;

import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Getter
@Setter
@Where(clause = "deleted_at is null")
public class Address extends DomainEntity{

    @NotBlank(message = "Logradouro não pode estar em branco")
    @NotNull(message = "Logradouro não pode estar nulo")
    private String street;

    @NotBlank(message = "Número do endereço não pode estar em branco")
    @NotNull(message = "Número do endereço não pode estar em branco")
    private String number;

    @NotBlank(message = "CEP não pode estar em branco")
    @NotNull(message = "CEP não pode estar nulo")
    private String postalCode;

    private String complement;

    @ManyToOne
    @JoinColumn(name="person_id")
    private Customer customer;

    @NotNull(message = "Tipo do endereço não pode estar nulo")
    @ManyToOne
    @JoinColumn(name="address_type_id")
    private AddressType addressType;

    @NotNull(message = "Cidade não pode estar nulo")
    @ManyToOne
    @JoinColumn(name="city_id")
    private City city;

    private Date deletedAt;

    public void delete() {
        this.deletedAt = new Date();
    }
}

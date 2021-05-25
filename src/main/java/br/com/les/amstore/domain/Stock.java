package br.com.les.amstore.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@Table(name = "_stock")
public class Stock extends DomainEntity {
    @ManyToOne
    private Game game;

    @NotNull(message = "Quantidade não pode ser nulo")
    private Integer amoInteger;

    @NotBlank(message = "Código do produto não pode ser vazio")
    @NotNull(message = "Código do produto não pode ser nulo")
    private String productCode;
}

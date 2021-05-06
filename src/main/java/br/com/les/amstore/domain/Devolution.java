package br.com.les.amstore.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@Getter
enum StatusDevolution {
    WAITING_ANSWER("Aguardando resposta"),
    ACCEPTED("Aceito"),
    REFUSED("Recusado");

    private final String description;
}

@Entity
@Getter
@Setter
public class Devolution extends DomainEntity {

    @NotNull(message = "O campo Motivo é obrigatório")
    @NotBlank(message = "O campo Motivo é obrigatório")
    @Size(max = 254)
    private String reason;

    private String answer;

    @OneToOne(cascade = CascadeType.ALL)
    private Order order;
    private StatusDevolution statusDevolution;

    public Devolution() {
        this.statusDevolution = StatusDevolution.WAITING_ANSWER;
    }
}
package br.com.les.amstore.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@Table(name = "_devolution")
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

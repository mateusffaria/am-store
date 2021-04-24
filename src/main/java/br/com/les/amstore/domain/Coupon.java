package br.com.les.amstore.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Getter
@Setter
public class Coupon extends DomainEntity{

    @NotBlank(message = "O código não pode estar em branco")
    @NotNull(message = "O código não pode estar em branco")
    private String code;

    @Min(value = 1, message = "A quantidade deve ser maior que 0")
    private Integer amount;

    @Min(value = 1, message = "O Valor deve ser maior que 0")
    private Double value;
}

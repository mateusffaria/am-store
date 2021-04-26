package br.com.les.amstore.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Getter
@Setter
public class Coupon extends DomainEntity{

    @NotBlank(message = "O código não pode estar em branco")
    @NotNull(message = "O código não pode estar em branco")
    @Size(min = 6)
    private String code;

    @Min(value = 1, message = "A quantidade deve ser maior que 0")
    private Integer amount;

    @Min(value = 1, message = "O Valor deve ser maior que 0")
    private Double value;

    @OneToMany(mappedBy = "coupon", targetEntity = Order.class)
    private List<Order> orderList;
}

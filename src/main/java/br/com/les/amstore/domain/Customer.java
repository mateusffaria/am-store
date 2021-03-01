package br.com.les.amstore.domain;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;


@Entity
@Getter
@Setter
@ToString
public class Customer extends Person{

    @NotNull
    @NotBlank
    @Email
    private String email;

    @NotBlank
    @NotNull
    @Length(min = 6)
    private String encryptedPassword;

    @Transient
    private String password;

    @ManyToOne
    @JoinColumn(name="customer_type_id")
    private CustomerType customerType;

    @OneToMany
    @JoinColumn(name="address_id")
    private List<Address> address;
}

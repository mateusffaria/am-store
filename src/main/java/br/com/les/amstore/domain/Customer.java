package br.com.les.amstore.domain;

import lombok.*;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;


@Entity
@Getter
@Setter
@Where(clause = "deleted_at is null")
public class Customer extends Person{

    @NotNull
    @NotBlank
    @Email
    private String email;

    @Length(min = 6)
    private String encryptedPassword;
    
    private String hash;

    @Transient
    private String password;

    @ManyToOne
    @JoinColumn(name="customer_type_id")
    private CustomerType customerType;

    @OneToMany(mappedBy = "customer", targetEntity = Address.class)
    private List<Address> address;

//    @AssertTrue(message = "Senha é obrigatória")
    public boolean hasPassword() {
        if(null == this.encryptedPassword)
            if(null == this.password || this.password.length() <= 0)
                return false;
        return true;
    }
}

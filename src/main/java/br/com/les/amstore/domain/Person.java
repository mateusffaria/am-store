package br.com.les.amstore.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Where(clause = "deleted_at is null")
public class Person extends DomainEntity {

    @NotNull
    @NotBlank
    @Email
    @Column(unique = true)
    private String email;

    @Length(min = 6)
    private String encryptedPassword;

    @Transient
    private String password;

    private boolean active;

    public boolean hasPasswordSet() {
        if(null == this.encryptedPassword)
            if(null == this.password || this.password.length() <= 0)
                return false;
        return true;
    }

    @OneToMany(mappedBy = "person", targetEntity = Document.class)
    private List<Document> documents;

    @NotNull(message = "Nome é obrigatório")
    @NotBlank(message = "Nome não pode estar em branco")
    private String name;

    @NotNull(message = "Telefone é obrigatório")
    @NotBlank(message = "Telefone não pode estar em branco")
    private String telephone;

    private Date deletedAt;

    private String roles;

    public void delete() {
        this.deletedAt = new Date();
    }
}

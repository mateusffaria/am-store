package br.com.les.amstore.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Game extends DomainEntity {

    @NotNull
    @NotBlank(message = "O Jogo deve conter um título")
    private String title;

    @NotNull(message = "O valor não pode ser nulo")
    private Double price;

    @NotNull(message = "O Jogo deve especificar se possui multiplayer")
    private boolean multiplayer;

    @NotNull(message = "O jogo deve conter faixa etária minima")
    private Integer age;

    @NotNull
    @NotBlank(message = "O Jogo deve conter uma sinopse")
    private String sinopsis;

    @NotNull
    @NotBlank(message = "O Jogo deve conter ao menos uma imagem")
    private String image;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date releaseDate;

    @ManyToOne
    @JoinColumn(name = "platform_id")
    private Platform platform;

    @ManyToMany
    private List<Language> languages;

    @ManyToMany
    private List<Gender> genders;

    @ManyToOne
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

    private boolean active;

    private Integer amount;

    private Integer amountAvailable;

    @Transient
    @Override
    public String validate(){
        StringBuilder stringBuilder = new StringBuilder();

        if(title.trim().length() < 2)
            stringBuilder.append("Título do jogo inválido, ");

        if(image.trim().length() <= 0)
            stringBuilder.append("Insira uma url válida, ");

        if(price.doubleValue() <= 10)
            stringBuilder.append("Preço do jogo deve ser maior que 10, ");

        if(sinopsis.trim().length() < 5)
            stringBuilder.append("Sinopse deve conter mais de 5 caractéres, ");

        if(age < 0)
            stringBuilder.append("Faixa etária inválida, ");

        return stringBuilder.toString();
    }

    public Game() {
        this.active = false;
    }
}

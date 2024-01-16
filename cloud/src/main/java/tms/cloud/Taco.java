package tms.cloud;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true) //przez testowe dodanie konstruktora
public class Taco {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(min = 5, message = "Nazwa musi zawierać co najmniej 5 znaków.")
    private String name;

    @ManyToMany(targetEntity = Ingredient.class)
    //@Size(min = 2, message = "Wybierz przynajmniej jeden składnik.")
    //@NotNull
    private List<Ingredient> ingredients = new ArrayList<>();

    private Date createdAt;

    @PrePersist
    void createdAt() {
        this.createdAt = new Date();
    }

    public Taco(String name, List<Ingredient> ingredients) {

        this.name = name;
        this.ingredients = ingredients;
    } //dla testów API <- CommandRunner
}
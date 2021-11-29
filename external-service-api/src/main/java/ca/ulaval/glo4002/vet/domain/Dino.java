package ca.ulaval.glo4002.vet.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "dino")
public class Dino {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "species")
    private String species;

    @Column(name = "food_type")
    private String foodType;

    public Integer getId() {
        return id;
    }

    public String getSpecies() {
        return species;
    }

    public String getFoodType() {
        return foodType;
    }
}

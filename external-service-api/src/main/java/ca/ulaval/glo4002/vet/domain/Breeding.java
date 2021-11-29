package ca.ulaval.glo4002.vet.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "breeding")
public class Breeding {
    @Id
    @GeneratedValue
    private Integer id;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "mother_id", referencedColumnName = "id")
    private Dino mother;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "father_id", referencedColumnName = "id")
    private Dino father;

    @Column(name = "offspring")
    private String offspring;

    @Column(name = "gender")
    private String gender;

    public Integer getId() {
        return id;
    }

    public Dino getMother() {
        return mother;
    }

    public Dino getFather() {
        return father;
    }

    public String getOffspring() {
        return offspring;
    }

    public String getGender() {
        return gender;
    }
}

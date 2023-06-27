package com.example.tdspring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity(name = "TypePersonneMoral")
@Table(

        name = "type_personne_morale"

)
public class TypePersonneMorale
{

    @Id
    @SequenceGenerator(
            name = "type-personne-morale-sequence",
            sequenceName = "type-personne-morale-sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "typepersonnemorale_sequence"
    )

    @Column(
            name = "id"
    )
    protected Long id;

    @Column(
            name = "intitule",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String intitule;

    public TypePersonneMorale() {

        super();
    }

    public TypePersonneMorale(String intitule) {

        this.intitule = intitule;

    }

    @OneToMany(mappedBy = "typePersonneMorale",
            fetch = FetchType.EAGER,
            cascade = {CascadeType.ALL},
            orphanRemoval = true)
    @JsonIgnore
    private Set<PersonneMorale> personneMorales = new HashSet<>();

    public Set<PersonneMorale> getPersonneMorales() {
        return personneMorales;
    }

    public void setPersonneMorales(Set<PersonneMorale> personneMorales) {

        this.personneMorales = personneMorales;

    }

    public void addPersonneMorale(PersonneMorale personneMorale) {

        this.personneMorales.add(personneMorale);

    }

    public void removePersonneMorale(PersonneMorale personneMorale) {

        this.personneMorales.remove(personneMorale);

        personneMorale.setTypePersonneMorale(null);

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

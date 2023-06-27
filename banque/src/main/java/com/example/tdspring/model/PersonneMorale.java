package com.example.tdspring.model;

import jakarta.persistence.*;

@Entity(name = "PersonneMorale")
@Table(
        name = "personne_morale"
)
public class PersonneMorale extends Personne {

    @Column(
            name = "siret",
            nullable = false,
            columnDefinition = "TEXT"
    )

    private String siret;
    @Column(
            name = "raisonSociale",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String raisonSociale;

    public PersonneMorale() {

        super();
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "typepersonnemorale")
    private TypePersonneMorale typePersonneMorale;

    public PersonneMorale(String adresse, String siret, String raisonSociale, TypePersonneMorale typePersonneMorale) {

        super(adresse);

        this.siret = siret;

        this.raisonSociale = raisonSociale;

        this.typePersonneMorale = typePersonneMorale;

        typePersonneMorale.getPersonneMorales().add(this);

    }


    public String getSiret() {

        return siret;

    }

    public void setSiret(String siret) {

        this.siret = siret;

    }

    public String getRaisonSociale() {

        return raisonSociale;

    }

    public void setRaisonSociale(String raisonSociale) {

        this.raisonSociale = raisonSociale;

    }

    public TypePersonneMorale getTypePersonneMorale() {

        return typePersonneMorale;

    }

    public void setTypePersonneMorale(TypePersonneMorale typePersonneMorale) {

        this.typePersonneMorale = typePersonneMorale;

    }

    @Override

    public String toString() {

        return "PersonneMorale{" +

                "id=" + id +

                ", siret='" + siret + '\'' +

                ", raisonSociale='" + raisonSociale + '\'' +

                ", adresse='" + adresse + '\'' +

                '}';

    }

    @Override
    public String nomComplet() {
        return this.raisonSociale;
    }

    @PreRemove

    private void gererLiensPM() {
        if (typePersonneMorale != null) typePersonneMorale.getPersonneMorales().remove(this);

        typePersonneMorale = null;

    }

}

package com.example.tdspring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity(name = "ConditionsGenerales")
@Table(

        name = "conditions_generales"

)
public class ConditionsGenerales {

    @Id
    @SequenceGenerator(
            name = "conditions_generales_sequence",
            sequenceName = "conditions_generales_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "conditions_generales_sequence"
    )
    @Column(
            name = "id"
    )
    private Long id;

    @Column(
            name = "tx_interet_agios",
            nullable = true,
            columnDefinition = "FLOAT"
    )
    private float tauxInteretAgios;

    @Column(
            name = "type_produit",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String typeProduit;

    @Column(
            name = "cotisation_carte",
            nullable = true,
            columnDefinition = "FLOAT"
    )
    private float cotisationCarte;

// mappedBy contient le nom de l'attribut ManyToOne dans ProduitBancaire

    @OneToMany(mappedBy = "conditionsGenerales",
            fetch = FetchType.EAGER,
            cascade = {CascadeType.ALL},
            orphanRemoval = true)
    @JsonIgnore
    private Set<ProduitBancaire> produitsBancaires = new HashSet<>();

    public ConditionsGenerales() {
    }

    public ConditionsGenerales(float tauxInteretAgios, String typeProduit, float cotisationCarte) {

        this.tauxInteretAgios = tauxInteretAgios;

        this.typeProduit = typeProduit;
        this.cotisationCarte = cotisationCarte;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getTauxInteretAgios() {
        return tauxInteretAgios;
    }

    public void setTauxInteretAgios(float tauxInteretAgios) {
        this.tauxInteretAgios = tauxInteretAgios;
    }

    public String getTypeProduit() {
        return typeProduit;
    }

    public void setTypeProduit(String typeProduit) {
        this.typeProduit = typeProduit;
    }

    public float getCotisationCarte() {
        return cotisationCarte;
    }

    public void setCotisationCarte(float cotisationCarte) {
        this.cotisationCarte = cotisationCarte;
    }

    public Set<ProduitBancaire> getProduitsBancaires() {
        return produitsBancaires;
    }

    public void setProduitsBancaires(Set<ProduitBancaire> produitsBancaires) {

        this.produitsBancaires = produitsBancaires;

    }

    public void addProduitBancaire(ProduitBancaire produitBancaire) {

        this.produitsBancaires.add(produitBancaire);

    }

    public void removeProduitBancaire(ProduitBancaire produitBancaire) {

        this.produitsBancaires.remove(produitBancaire);

        produitBancaire.setConditionsGenerales(null);

    }

    @Override

    public String toString() {

        return "\nConditionsGenerales{" +

                "id=" + id +

                ", \n\ttypeProduit='" + typeProduit +

                ", \n\ttauxInteretAgios=" + tauxInteretAgios +

                ", \n\tcotisationCarte=" + cotisationCarte +

                "}\n";

    }

    @Override

    public boolean equals(Object o) {

        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        ConditionsGenerales that = (ConditionsGenerales) o;

        return Objects.equals(id, that.id);

    }

    @Override

    public int hashCode() {

        return Objects.hash(id);

    }

    @PreRemove

    private void gererLiens() {

// Pour casser le lien avec les conditions générales
// A utiliser dans le cas d'une cardinalité minimale 0

        for (ProduitBancaire pb : produitsBancaires) {

            pb.setConditionsGenerales(null);

        }

        produitsBancaires.clear();

    }

}
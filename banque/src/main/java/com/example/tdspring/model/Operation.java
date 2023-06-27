package com.example.tdspring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.sql.Date;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity(name = "Operation")
@Table(

        name = "operation"

)
public class Operation {

    @Id
    @SequenceGenerator(
            name = "operation_sequence",
            sequenceName = "operation_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "operation_sequence"
    )
    @Column(
            name = "id"
    )
    private Long id;

    @Column(
            name = "date_operation",
            nullable = true,
            columnDefinition = "DATE"
    )
    private Date dateOperation;

    @Column(
            name = "montant",
            nullable = true,
            columnDefinition = "FLOAT"
    )
    private float montant;

    @Column(
            name = "type",
            nullable = true,
            columnDefinition = "TEXT"
    )
    private String type;

    @Column(
            name = "libelle",
            nullable = true,
            columnDefinition = "TEXT"
    )
    private String libelle;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "produit_bancaire_id")
    @JsonIgnore
    private ProduitBancaire produitBancaire;

    public Long getId() {
        return id;
    }

    public Date getDateOperation() {
        return dateOperation;
    }

    public float getMontant() {
        return montant;
    }

    public String getType() {
        return type;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDateOperation(Date dateOperation) {
        this.dateOperation = dateOperation;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Operation(Date dateOperation, float montant, String type, String libelle, ProduitBancaire produitBancaire) {

        this.dateOperation = dateOperation;
        this.libelle = libelle;
        this.montant = montant;
        this.type = type;
        this.produitBancaire = produitBancaire;
        produitBancaire.getOperations().add(this);

    }

    public Operation() {
    }

    public ProduitBancaire getProduitBancaire() {
        return produitBancaire;
    }

    public void setProduitBancaire(ProduitBancaire produitBancaire) {
        this.produitBancaire = produitBancaire;
    }

    @PreRemove
    private void removeParent() {
        this.produitBancaire = null;
    }
}

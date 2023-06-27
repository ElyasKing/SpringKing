package com.example.tdspring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity
public class ProduitBancaire {

    @Id
    @SequenceGenerator(
            name = "produit_bancaire_sequence",
            sequenceName = "produit_bancaire_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "produit_bancaire_sequence"
    )
    @Column(
            name = "id"
    )
    protected Long id;

    @Column(
            name = "solde_courant",
            nullable = false,
            columnDefinition = "FLOAT"
    )
    private float solde_courant;

    public String getNomCompte() {
        return nomCompte;
    }

    public void setNomCompte(String nomCompte) {
        this.nomCompte = nomCompte;
    }

    @Column(
            name = "nomCompte",
            nullable = true,
            columnDefinition = "TEXT"

    )

    private String nomCompte;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "conditions_generales_id")
    private ConditionsGenerales conditionsGenerales;

    @JsonIgnore
    @OneToMany(mappedBy = "produitBancaire",
            fetch = FetchType.EAGER,
            cascade = {CascadeType.ALL},
            orphanRemoval = true)
    private Set<Operation> operations = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_bancaire_id")
    @JsonIgnore
    private ClientBancaire clientBancaire;

    public ProduitBancaire(float solde_courant, ConditionsGenerales conditionsGenerales, ClientBancaire clientBancaire, String nomCompte) {

        this.solde_courant = solde_courant;
        this.nomCompte = nomCompte;
        this.conditionsGenerales = conditionsGenerales;
        this.clientBancaire = clientBancaire;

        conditionsGenerales.getProduitsBancaires().add(this);
        clientBancaire.getProduitsBancaires().add(this);

    }

    public ProduitBancaire() {
    }

    public ConditionsGenerales getConditionsGenerales() {

        return conditionsGenerales;

    }

    public void setConditionsGenerales(ConditionsGenerales conditionsGenerales) {

        this.conditionsGenerales = conditionsGenerales;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getSolde_courant() {
        return solde_courant;
    }

    public void setSolde_courant(float solde_courant) {
        this.solde_courant = solde_courant;
    }

    @Override

    public String toString() {

        return "\nProduitBancaire{" +

                "\n\tid=" + id +

                ", \n\tsolde_courant=" + solde_courant +

                ", \n\tconditionsGenerales=" + conditionsGenerales +

                '}';

    }

    @Override

    public boolean equals(Object o) {

        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        ProduitBancaire that = (ProduitBancaire) o;

        return this.id.equals(that.id);

    }

    @Override

    public int hashCode() {
        return Objects.hash(id);
    }

    @PreRemove
    private void gererLiens() {
        // On enlève le produit bancaire de la liste des produits qui est dans les conditions générales liées
        // Cela casse le lien de conditionsgenerales vers ce produit bancaire

        if (conditionsGenerales != null) {
            conditionsGenerales.getProduitsBancaires().remove(this);
        }

        // On casse le lien du produit bancaire vers ses conditions générales
        conditionsGenerales = null;

    }

    public Set<Operation> getOperations() {
        return operations;
    }

    public void setOperations(Set<Operation> operations) {

        this.operations = operations;

    }

    public void addOperations(Operation operation) {

        this.operations.add(operation);

    }


    public void removeOperation(Operation operation) {

        this.operations.remove(operation);

        operation.setProduitBancaire(null);

    }

    public ClientBancaire getClientBancaire() {

        return clientBancaire;

    }

    public void setClientBancaire(ClientBancaire clientBancaire) {

        this.clientBancaire = clientBancaire;

    }


}

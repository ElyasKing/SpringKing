package com.example.tdspring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Personne {

    @Id
    @SequenceGenerator(name = "personne_sequence", sequenceName = "personne_sequence", allocationSize = 1)
    @GeneratedValue(strategy = SEQUENCE, generator = "personne_sequence")
    @Column(name = "id")
    protected Long id;

    @Column(name = "adresse", nullable = true, columnDefinition = "TEXT")
    protected String adresse;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL}, mappedBy = "personnes")
    protected Set<ClientBancaire> clientsBancaires = new HashSet<ClientBancaire>();

    public Personne() {
    }

    public Personne(String adresse) {
        this.adresse = adresse;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<ClientBancaire> getClientsBancaires() {
        return clientsBancaires;
    }

    public void setClientsBancaires(Set<ClientBancaire> clientsBancaires) {
        this.clientsBancaires = clientsBancaires;
    }

    public void addClientBancaire(ClientBancaire clientBancaire) {
        clientsBancaires.add(clientBancaire);

// Je suis du côté non propriétaire de la relation
        // Si l'ajout d'une instance de la relation est initié par la personne, je dois rajouter

// à la main la personne dans le client bancaire

// Si l'ajout est initié par le client bancaire, la personne sera déjà dans le client bancaire

// => test if

        if (!clientBancaire.getPersonnes().contains(this)) clientBancaire.addPersonne(this);

    }

    public void removeClientBancaireFromClientBancaire(ClientBancaire clientBancaire) {

        if (clientsBancaires.contains(clientBancaire)) {

            clientsBancaires.remove(clientBancaire);

        }

// Je suis du côté non propriétaire de la relation

// Si la suppression d'une instance de la relation est initiée par la personne, je dois enlever

// à la main la personne dans le client bancaire

// Si la suppression est initiée par le client bancaire, la personne sera déjà supprimée du client bancaire

// => test if

//if (clientBancaire.getPersonnes().contains(this)) clientBancaire.removePersonne(this);

    }

    public void removeClientBancaire(ClientBancaire clientBancaire) {

        clientsBancaires.remove(clientBancaire);

// Je suis du côté non propriétaire de la relation

// Si la suppression d'une instance de la relation est initiée par la personne, je dois enlever

// à la main la personne dans le client bancaire

// Si la suppression est initiée par le client bancaire, la personne sera déjà supprimée du client bancaire

// => test if

        if (clientBancaire.getPersonnes().contains(this)) clientBancaire.getPersonnes().remove(this);

    }

    @Override

    public String toString() {

        return "\nPersonne{" +

                "\n\tAdresse='" + adresse +

                '}';

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Personne personne = (Personne) o;

        return this.id.equals(personne.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @PreRemove
    protected void gererLiens() {
        for (ClientBancaire cb : clientsBancaires) {
            if (cb != null)
                cb.removePersonne(this);
        }

        clientsBancaires.clear();
    }

    public abstract String nomComplet();
}
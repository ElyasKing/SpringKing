package com.example.tdspring.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity
public class ClientBancaire {

    @Id
    @SequenceGenerator(name = "client_bancaire_sequence", sequenceName = "client_bancaire_sequence", allocationSize = 1)
    @GeneratedValue(strategy = SEQUENCE, generator = "client_bancaire_sequence")
    @Column(name = "id")
    protected Long id;

    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {CascadeType.ALL})
    @JoinTable(
// nom de la table d'association
            name = "client_personne",

// nom de la colonne clé étrangère côté ClientBancaire
            joinColumns = {@JoinColumn(name = "client_bancaire_id")},

// nom de la colonne clé étrangère côté Personne
            inverseJoinColumns = {@JoinColumn(name = "personne_id")})
    private Set<Personne> personnes = new HashSet<>();

    @OneToMany(mappedBy = "clientBancaire",
            fetch = FetchType.EAGER,
            cascade = {CascadeType.ALL},
            orphanRemoval = true)
    private Set<ProduitBancaire> produitsBancaires = new HashSet<>();

    public ClientBancaire() {
    }

    public Long getId() {

        return id;

    }

    public void setId(Long id) {

        this.id = id;

    }

    public Set<Personne> getPersonnes() {

        return personnes;

    }

    public void setPersonnes(Set<Personne> personnes) {

        this.personnes = personnes;

    }

    public void addPersonne(Personne personne) {
        personnes.add(personne);

        personne.addClientBancaire(this);
    }

    public void removePersonne(Personne personne) {

        personnes.remove(personne);

        personne.getClientsBancaires().remove(this);

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

        produitBancaire.setClientBancaire(null);

    }

    @Override

    public String toString() {

        return "\nClientBancaire{" +

                "\n\tid=" + id +

                '}';

    }

    @Override

    public boolean equals(Object o) {

        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        ClientBancaire that = (ClientBancaire) o;

        return Objects.equals(this.id, that.id);

    }

    @Override

    public int hashCode() {

        return Objects.hash(id);

    }

    @PreRemove

    private void gererLiens() {

        for (Personne personne : this.personnes) {

            personne.getClientsBancaires().remove(this);

        }

        this.personnes.clear();

    }
}

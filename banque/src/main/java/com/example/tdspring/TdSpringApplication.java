package com.example.tdspring;

import ch.qos.logback.core.net.server.Client;
import com.example.tdspring.model.*;
import com.example.tdspring.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.time.Instant;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class TdSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(TdSpringApplication.class, args);
    }

     /*

    @Bean
    CommandLineRunner ajouterConditionsGenerales(ConditionsGeneralesRepository conditionsGeneralesRepository)
    {
        return args -> {
            // On vide la table pour ne pas créer de doublons
            conditionsGeneralesRepository.deleteAll();
            // On crée une liste de conditions générales que l'on insèrera d'un coup en base
            ArrayList<ConditionsGenerales> listeCG = new ArrayList<ConditionsGenerales>();
            // On crée quelques conditions générales
            listeCG.add(new ConditionsGenerales(
                    0,
                    "Mastercard",
                    15));
            listeCG.add(new ConditionsGenerales(
                    3.0f,
                    "Livret épargne",
                    0));
            listeCG.add(new ConditionsGenerales(
                    1.0f,
                    "Compte rémunéré",
                    0));
            listeCG.add(new ConditionsGenerales(
                    3.0f,
                    "Prêt consommation",
                    0));

            // On insère la liste dans la table
            conditionsGeneralesRepository.saveAll(listeCG);

            // Testons maintenant les requetes par defaut 
           // ArrayList<ConditionsGenerales> listeCG = new ArrayList<ConditionsGenerales>();
            long nbConditionsGenerales;
            // Nombre de conditions générales
            nbConditionsGenerales=conditionsGeneralesRepository.count();
            System.out.println("\n***************** test count");
            System.out.println("\nil y a "+nbConditionsGenerales+" conditions generales dans la base");

            // Rechercher Condition générale par id
            long id=100;
            Optional<ConditionsGenerales> cg=conditionsGeneralesRepository.findById(id);
            System.out.println("\n***************** test findById");
            if (cg.isPresent()) System.out.println("\nconditions générales avec l'id : "+id+cg);
            else System.out.println("Pas de condition generale avec la clé : "+id);

            // On récupère et on affiche les conditions générales en base
            System.out.println("\n***************** Toutes les conditions générales non triees");
            System.out.println(conditionsGeneralesRepository.findAll());

            // On récupère les conditions générales en base triées par ordre alphabétique du typeProduit
            System.out.println("\n***************** Toutes les conditions générales dans l'ordre alphabetique du typeProduit");
            System.out.println(conditionsGeneralesRepository.findAll(Sort.by(Sort.Direction.ASC, "typeProduit")));

            // Pagination
            int numeroPage=3; // numéro de la page chargée. Attention, le numéro commence à 0. Ici, on récupère la quatrième page
            int taillePage=1; // Nombre de conditions générales par page.
            // L'instruction suivante récupère la 4ième page de données, chaque page contenant une seule donnée. Les données sont ici aussi triées par ordre alphabétique du type
            Page<ConditionsGenerales> page= conditionsGeneralesRepository.findAll(PageRequest.of(3, 1,Sort.by(Sort.Direction.ASC, "typeProduit")));
            System.out.println("\n***************** Test pagination");
            System.out.println(page+"\n Contenu de la page"+page.getContent());
        };
    }

    //@Bean
    CommandLineRunner testerRequetesAutomatiques(ConditionsGeneralesRepository conditionsGeneralesRepository) {
        return args -> {
            ArrayList<ConditionsGenerales> listeCG = new ArrayList<ConditionsGenerales>();
            System.out.println("*******************************\nRechercher les conditions générales dont le type est Compte chèque");
            listeCG=conditionsGeneralesRepository.findByTypeProduit("Compte chèque");
            System.out.println(listeCG);
            System.out.println("*******************************\nPremiere solution : Rechercher les conditions générales dont le type contient compte");
            listeCG=conditionsGeneralesRepository.findByTypeProduitContains("compte");
            System.out.println(listeCG);
            System.out.println("*******************************\nDeuxieme solution : Rechercher les conditions générales dont le type contient compte");
            listeCG=conditionsGeneralesRepository.findByTypeProduitLike("%compte%");
            System.out.println(listeCG);
            System.out.println("*******************************\nRechercher les 3 dernieres conditions générales saisies en base");
            listeCG=conditionsGeneralesRepository.findFirst3ByOrderByIdDesc();
            System.out.println(listeCG);
            System.out.println("*******************************\nListe des produits dont la rentabilité est >=3 pour la banque");
            listeCG=conditionsGeneralesRepository.findByTauxInteretAgiosGreaterThanEqualOrderByTauxInteretAgiosAsc(3);
            System.out.println(listeCG);
        };
    }

    @Bean
CommandLineRunner ajouterPersonnes(

            PersonneRepository<PersonneMorale> personneMoraleRepository,

            PersonneRepository<PersonnePhysique> personnePhysiqueRepository)

    {

        return args -> {

// Suppression des données créées par ce test lors d'une exécution précédente pour éviter les doublons

            personneMoraleRepository.deleteAll();

            personnePhysiqueRepository.deleteAll();

// Création de trois personnes morales

            PersonneMorale pm1=new PersonneMorale("102bis rue du Vesuve","SIRET1", "Pizza Tonio");

            PersonneMorale pm2=new PersonneMorale("45 Boulevard du fleuve","SIRET2", "Meubles cosy");

            PersonneMorale pm3=new PersonneMorale("14 allee des platanes","SIRET3", "Espaces tres verts");

// Enregistrement en base

            personneMoraleRepository.save(pm1);

            personneMoraleRepository.save(pm2);

            personneMoraleRepository.save(pm3);
            // Affichage du résultat

            System.out.println(personneMoraleRepository.findAll());

// Ajout de personnes physiques

            PersonnePhysique pp1=new PersonnePhysique("19 rue des fleurs, 80000 Amiens", "Dupont","Jean");

            personnePhysiqueRepository.save(pp1);

            PersonnePhysique pp2=new PersonnePhysique("143 boulevard des landes, 64200 Anglet", "Eche","Piou");

            personnePhysiqueRepository.save(pp2);

            PersonnePhysique pp3=new PersonnePhysique("56 avenue de Paris, 60000 Beauvais", "Tristan","Jacques");

            personnePhysiqueRepository.save(pp3);

            System.out.println(personnePhysiqueRepository.findAll());

        };

    }



    @Bean
    CommandLineRunner seedData(

            ConditionsGeneralesRepository conditionsGeneralesRepository,

            PersonneRepository<PersonneMorale> personneMoraleRepository,

            PersonneRepository<PersonnePhysique> personnePhysiqueRepository,

            ProduitBancaireRepository<ProduitBancaire> produitBancaireRepository) {

        return args -> {

            List<ConditionsGenerales> conditionsGeneraless;

            List<ProduitBancaire> produitBancaires;

            ConditionsGenerales cg1 = new ConditionsGenerales((float) 0.2, "cg1", 0);

            conditionsGeneralesRepository.save(cg1);

            ConditionsGenerales cg2 = new ConditionsGenerales(3, "cg2", 0);

            conditionsGeneralesRepository.save(cg2);

            ConditionsGenerales cg3 = new ConditionsGenerales(0, "cg3", 15);

            conditionsGeneralesRepository.save(cg3);

            cg3 = conditionsGeneralesRepository.findById(cg3.getId()).orElseThrow();

            ProduitBancaire pb1 = new ProduitBancaire(1, cg3);

            produitBancaireRepository.save(pb1);

            cg2 = conditionsGeneralesRepository.findById(cg2.getId()).orElseThrow();

            ProduitBancaire pb2 = new ProduitBancaire(2, cg2);

            produitBancaireRepository.save(pb2);

            cg3 = conditionsGeneralesRepository.findById(cg3.getId()).orElseThrow();

            ProduitBancaire pb3 = new ProduitBancaire(3, cg3);

            produitBancaireRepository.save(pb3);

            conditionsGeneraless = conditionsGeneralesRepository.findAll();

            produitBancaires = produitBancaireRepository.findAll();

            pb1 = produitBancaireRepository.findById(pb1.getId()).orElseThrow();

            produitBancaireRepository.deleteById(pb1.getId());

            if (conditionsGeneralesRepository.existsById(cg3.getId())) {

                System.out.println("cg3 est toujours dans la base");

            } else {

                System.out.println("cg3 n''existe plus");

            }

            if (produitBancaireRepository.existsById(pb1.getId())) {

                System.out.println("pb1 est toujours ans la base");

            } else {

                System.out.println("pb1 n''existe plus");

            }

            if (produitBancaireRepository.existsById(pb3.getId())) {

                System.out.println("pb3 est toujours ans la base");

            } else {

                System.out.println("pb3 n''existe plus");

            }

        };

    }

    @Bean
    CommandLineRunner seedData(

            TypePersonneMoraleRepository typePersonneMoraleRepository,

            PersonneRepository<PersonneMorale> personneMoraleRepository,

            PersonneRepository<PersonnePhysique> personnePhysiqueRepository) {

        return args -> {

            List<TypePersonneMorale> typePersonneMorales;

            List<PersonneMorale> personneMorales;

            TypePersonneMorale tpm1 = new TypePersonneMorale("tpm1");

            typePersonneMoraleRepository.save(tpm1);

            TypePersonneMorale tpm2 = new TypePersonneMorale("tpm2");

            typePersonneMoraleRepository.save(tpm2);

            tpm1 = typePersonneMoraleRepository.findById(tpm1.getId()).orElseThrow();

            PersonneMorale pm1 = new PersonneMorale("adresse", "siret", "raison", tpm1);

            personneMoraleRepository.save(pm1);

            typePersonneMorales = typePersonneMoraleRepository.findAll();
            personneMorales = personneMoraleRepository.findAll();
        };
    }

      */

    // @Bean
    CommandLineRunner seedData(

            TypePersonneMoraleRepository typePersonneMoraleRepository,

            PersonneRepository<PersonneMorale> personneMoraleRepository,

            PersonneRepository<PersonnePhysique> personnePhysiqueRepository,
            ClientBancaireRepository clientBancaireRepository) {

        return args -> {

// Création de personnes physiques

            PersonnePhysique pp1 = new PersonnePhysique("adresse_pp1", "nom_pp1", "prenom_pp1");

            personnePhysiqueRepository.save(pp1);
            PersonnePhysique pp2 = new PersonnePhysique("adresse_pp2", "nom_pp2", "prenom_pp2");

            personnePhysiqueRepository.save(pp2);

            PersonnePhysique pp3 = new PersonnePhysique("adresse_pp3", "nom_pp3", "prenom_pp3");

            personnePhysiqueRepository.save(pp3);

// Création de types de personnes morales

            TypePersonneMorale tpm1 = new TypePersonneMorale("SA");

            typePersonneMoraleRepository.save(tpm1);

            TypePersonneMorale tpm2 = new TypePersonneMorale("SARL");

            typePersonneMoraleRepository.save(tpm2);

            TypePersonneMorale tpm3 = new TypePersonneMorale("Auto Entrepreneur");

            typePersonneMoraleRepository.save(tpm3);

// Création de types de personnes morales

            PersonneMorale pm1 = new PersonneMorale("pm1", "SiRET pm1", "raisonsoc pm1", tpm1);

            personneMoraleRepository.save(pm1);

            PersonneMorale pm2 = new PersonneMorale("pm2", "SiRET pm2", "raisonsoc pm2", tpm2);

            personneMoraleRepository.save(pm2);

            tpm1 = typePersonneMoraleRepository.findById(tpm1.getId()).orElseThrow();

            PersonneMorale pm3 = new PersonneMorale("pm3", "SiRET pm3", "raisonsoc pm3", tpm1);

            personneMoraleRepository.save(pm3);

// Création de client bancaires

            ClientBancaire cb1 = new ClientBancaire();

            clientBancaireRepository.save(cb1);

            ClientBancaire cb2 = new ClientBancaire();

            clientBancaireRepository.save(cb2);

            ClientBancaire cb3 = new ClientBancaire();

            clientBancaireRepository.save(cb3);

// Ajout des participants aux clients

            pm1 = personneMoraleRepository.findById(pm1.getId()).orElseThrow();

            cb1.addPersonne(pm1);

            clientBancaireRepository.save(cb1);

            cb1 = clientBancaireRepository.findById(cb1.getId()).orElseThrow();

            pp2 = personnePhysiqueRepository.findById(pp2.getId()).orElseThrow();

            cb1.addPersonne(pp2);

            clientBancaireRepository.save(cb1);

            pp1 = personnePhysiqueRepository.findById(pp1.getId()).orElseThrow();

            cb2 = clientBancaireRepository.findById(cb2.getId()).orElseThrow();

            pp1.addClientBancaire(cb2);

            personnePhysiqueRepository.save(pp1);

            cb3 = clientBancaireRepository.findById(cb3.getId()).orElseThrow();

            pp3 = personnePhysiqueRepository.findById(pp3.getId()).orElseThrow();

            cb3.addPersonne(pp3);

            clientBancaireRepository.save(cb3);

            System.out.println("***************************************************" +

                    "\nLes personnes physiques" +

                    "***********************************************************");

            System.out.println(personnePhysiqueRepository.findAll());

            System.out.println("***************************************************" +

                    "\nLes personnes morales" +

                    "***********************************************************");

            System.out.println(personneMoraleRepository.findAll());

        };
    }




}

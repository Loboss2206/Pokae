package joueur;

/**
 * Classe DresseurHumain du package joueur, elle implémente l'interface IDresseur, cette classe permet a un joueur d'initialiser son ranch et de l'utiliser en combat
 * @author Lucas Schiavetti, Sacha Hassan, Logan Brunet, Logan Laporte, Arthur Bailleul
 *
 */


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import capacités.Capacite;
import capacités.Categorie;
import capacités.Echange;
import interfaces.IAttaque;
import interfaces.IPokemon;
import pokemon.EType;
import pokemon.Pokemon;

public class DresseurHumain extends Dresseur{
    @SuppressWarnings("resource")
	public DresseurHumain() {
    	super();
        Scanner scName = new Scanner(System.in);
        System.out.println("Quel est votre prénom ? ");
        String nom = scName.next();
        this.setNom(nom);
    }

	/**
	 * La fonction permet au joueur de choisir un premier pokemon a envoyer au combat 
	 * @return le numero du pokemon dans le ranch du dresseur
	 */
    @SuppressWarnings("resource")
    @Override
    public IPokemon choisitCombattant() {
        System.out.println("\r\n|-----------------------------------------------|"
                + "\r\n\r\nVoici votre équipe " + this.getNom() + " :\r\n"
                + "---------------------------");
        for (int i = 0; i<getRanch().length;i++) {
            System.out.println("Pokemon " + (i+1) + " : " + getRanch()[i].getNom());
            System.out.println("---------------------------");
        }
        Scanner pokemon = new Scanner(System.in);
        System.out.print("\r\nQuel est le numéro du pokemon que vous souhaitez "
                + "envoyer au combat en premier ?");
        int numeroPokemon = pokemon.nextInt() - 1;
        System.out.println();
        return getRanch()[numeroPokemon];
    }



	/**
	 * La fonction permet au joueur de choisir un pokemon a envoyer au combat
	 * @return le numero du pokemon dans le ranch du dresseur
	 */  
    @SuppressWarnings("resource")
    @Override
    public IPokemon choisitCombattantContre(IPokemon pok) {
        boolean choixValide = false;
        int numeroPokemon = 6;

        // Affichage de l'équipe
        System.out.println("---------------------------");
        for (int i = 0; i < getRanch().length; i++) {

            if (getRanch()[i].estEvanoui() == true) {
                System.out.println("Pokemon " + (i + 1) + " : " + getRanch()[i].getNom() + " | Etat : Evanoui");
            } else {
                System.out.println("Pokemon " + (i + 1) + " : " + getRanch()[i].getNom() + " | Etat : Prêt pour combattre");
            }
            System.out.println("---------------------------");
        }

        // On vérifie que le choix est valide
        while (choixValide == false) {
            try {
                Scanner choixPokemon = new Scanner(System.in);
                System.out.print("\r\nLe pokemon adverse est " + pok.getNom() + ".\r\nQuel est le numéro du pokemon que vous souhaitez "
                        + "envoyer au combat contre lui ?");
                numeroPokemon = choixPokemon.nextInt() - 1;

                if (getRanch()[numeroPokemon].horsCombat() == false) {
                    choixValide = true;
                }
            } catch (ArrayIndexOutOfBoundsException e) {}
        }
        return getRanch()[numeroPokemon];
    }

	/**
	 * La fonction permet au joueur de choisir une attaque
	 * @param attaquant, pokemon attaquant du tour
	 * @param defenseur, pokemon defenseur du tour
	 * @return une attaque
	 */  
    @SuppressWarnings("resource")
    @Override
    public IAttaque choisitAttaque(IPokemon attaquant, IPokemon defenseur) {

        boolean choixDefinitif = false;
        IAttaque attaqueFinale = null;

        while (choixDefinitif == false) {
            Scanner attaque = new Scanner(System.in);
            System.out.print("\r\nQuel type d'attaque souhaitez-vous utiliser ?\r\n");

            System.out.println("Un échange ou une capacité de " + attaquant.getNom() + " ?");
            System.out.println("Entrez \"Echange\" ou \"Capacité\"");
            String typeAttaque = attaque.next();


            // L'attaque est un échange
            if (typeAttaque.equals("Échange") || typeAttaque.equals("Echange")
                    || typeAttaque.equals("échange") || typeAttaque.equals("echange")) {

                boolean echangeValide = false;
                int numeroPokemonEchange = 6;

                // On affiche les pokemons disponibles pour l'échange
                System.out.println("---------------------------");
                for (int i = 0; i < getRanch().length; i++) {

                    if (getRanch()[i].estEvanoui() == true) {
                        System.out.println("Pokemon " + (i + 1) + " : " + getRanch()[i].getNom() + " | Etat : Evanoui");
                    } else {
                        System.out.println("Pokemon " + (i + 1) + " : " + getRanch()[i].getNom() + " | Etat : Prêt pour combattre");
                    }
                    System.out.println("---------------------------");
                }

                // On teste si l'échange est valide
                while (echangeValide == false) {
                    try {
                        Scanner pokemonEchange = new Scanner(System.in);
                        System.out.println("Quel est le numéro du pokemon dans votre équipe avec lequel vous souhaitez "
                                + "échanger " + attaquant.getNom() + " ?");
                        numeroPokemonEchange = pokemonEchange.nextInt() - 1;

                        if (getRanch()[numeroPokemonEchange].horsCombat() == false
                                && getCombattant() != getRanch()[numeroPokemonEchange]) {
                            echangeValide = true;
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {}
                }

                Echange echangePokemon = new Echange(this);
                echangePokemon.setPokemon(getRanch()[numeroPokemonEchange]);
                attaqueFinale = echangePokemon;
                choixDefinitif = true;
            }

            // L'attaque est une capacité
            else if (typeAttaque.equals("Capacité") || typeAttaque.equals("Capacite")
                    || typeAttaque.equals("capacité") || typeAttaque.equals("capacite") ) {

                boolean capaciteValide = false;
                int capacitePokemon = 4;

                // On affiche les capacités du combattant
                System.out.println("\r\n---------------------------------");
                for (int i = 0;i<getCombattant().getCapacitesApprises().length;i++) {
                    if (getCombattant().getCapacitesApprises()[i] != null) {
                        System.out.println("| Capacité " + (i+1) + " : " + getCombattant().getCapacitesApprises()[i].getNom() + ", PP : " + getCombattant().getCapacitesApprises()[i].getPP() + " |");
                    }
                }
                System.out.println("---------------------------------\r\n");

                while (capaciteValide == false) {
                    try {
                        Scanner numeroCapacite = new Scanner(System.in);
                        System.out.println("Quel est le numéro de la capacité de " + attaquant.getNom() + " que vous "
                                + "voulez utiliser ?");
                        capacitePokemon = numeroCapacite.nextInt() - 1;

                        if (getCombattant().getCapacitesApprises()[capacitePokemon] != null &&
                                getCombattant().getCapacitesApprises()[capacitePokemon].getPP() != 0) {
                            capaciteValide = true;
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {}
                }

                attaqueFinale = getCombattant().capacitesApprises[capacitePokemon];
                choixDefinitif = true;
            }
        }
        return attaqueFinale;

    }

	/**
	 * La fonction permet au joueur d'initialiser les capacites de chaque pokemon de son ranch
	 */  
    @Override
    public void initCapacitesRanch() {
        System.out.println("\r\nChoisissez les capacités de vos 6 pokemons " + getNom());

        // Le dresseur choisit pour chaque pokemon ses capacités
        for (int i=0; i<6; i++){

            Capacite[] capacitesSousLeNiveau1 = getRanch()[i].getEspece().getCapacitesSousNiveau(1);

            if (capacitesSousLeNiveau1.length == 0) {
                System.out.println(getRanch()[i].getNom() + " ne peut apprendre aucune capacité pour le moment.");
            }
            else {
                System.out.println("\r\n--------------------------------------------------------\r\n" +
                        "Capacités disponibles pour " + getRanch()[i].getNom() + " :\r\n");

                if (capacitesSousLeNiveau1.length == 51) {
                    for (int j = 0; j < capacitesSousLeNiveau1.length - 1; j++) {
                        System.out.println("| Capacité " + (j + 1) + " : " + capacitesSousLeNiveau1[j].getNom()
                                + ", PP : " + capacitesSousLeNiveau1[j].getPP()
                                + ", Puissance : " + capacitesSousLeNiveau1[j].getPuissance());
                    }
                } else {
                    for (int j = 0; j < capacitesSousLeNiveau1.length; j++) {
                        System.out.println("| Capacité " + (j + 1) + " : " + capacitesSousLeNiveau1[j].getNom()
                                + ", PP : " + capacitesSousLeNiveau1[j].getPP()
                                + ", Puissance : " + capacitesSousLeNiveau1[j].getPuissance());
                    }
                }

                System.out.println("\r\nEntrez les numéros des capacités que vous souhaitez, " + getRanch()[i].getNom() + " ne peut pas avoir 2 fois la même capacité : ");


                int k = 0;

                while (k < capacitesSousLeNiveau1.length && k < 4) {
                    boolean choixValide = false;
                    int choixCapacites = -1;

                    while (choixValide == false) {
                        Scanner choix = new Scanner(System.in);
                        choixCapacites = choix.nextInt() - 1;

                        if (capacitesSousLeNiveau1[choixCapacites] != null) {
                            boolean capaciteValide = true;
                            for (int j = 0; j < k; j++) {
                                if (getRanch()[i].getCapacitesApprises()[j] == capacitesSousLeNiveau1[choixCapacites]) {
                                    capaciteValide = false;
                                }
                            }
                            if (capaciteValide) choixValide = true;
                        }
                    }

                    Capacite c = capacitesSousLeNiveau1[choixCapacites];
                    Capacite capacitePokemon = new Capacite(c.id,c.getNom(),c.getPrecision(),c.getPuissance(),c.getPP(),(Categorie) c.getCategorie(),(EType) c.getType());
                    getRanch()[i].getCapacitesApprises()[k] = capacitePokemon;
                    k++;
                }
            }
        }
    }

	/**
	 * La fonction permet au joueur de choisir une capacite a apprendre a son pokemon qui vient de monter de niveau
	 * @param pok, le pokemon du dresseur
	 */  
    @SuppressWarnings("resource")
    public void peutEnseignerCapacité(Pokemon pok) {

        Capacite[] tabCapacitePokemon = pok.getEspece().getCapacitesSousNiveau(pok.getNiveau());

        List<Capacite> tabCapacitesPasApprises = new ArrayList<Capacite>();

        for (int i = 0;i<tabCapacitePokemon.length;i++) {
            boolean capValide = true;
            for (int k = 0;k<4;k++) {
                if (pok.getCapacitesApprises()[k] == tabCapacitePokemon[i]) {
                    capValide = false;
                }
            }
            if (capValide == true) {
                tabCapacitesPasApprises.add(tabCapacitePokemon[i]);
            }
        }

        if (tabCapacitesPasApprises.size() != 0) {
            System.out.println("Voici les capacités apprenables par " + pok.getNom() + " à ce niveau :");

            for (int i = 0; i < tabCapacitesPasApprises.size(); i++) {
                System.out.println("| Capacité " + (i + 1) + " : " + tabCapacitesPasApprises.get(i).getNom()
                        + ", PP : " + tabCapacitesPasApprises.get(i).getPP()
                        + ", Puissance : " + tabCapacitesPasApprises.get(i).getPuissance());
            }



            Scanner scCap = new Scanner(System.in);
            System.out.println("Voulez-vous apprendre une nouvelle capacité à " + pok.getNom() + " parmi celles ci-dessus ? Si oui, entrez le numéro de cette capacité sinon entrez 0.");
            int reponse = scCap.nextInt();

            if (reponse != 0) {
                reponse--;

                for (int i = 0; i < 4; i++) {
                    if (pok.capacitesApprises[i] != null) {
                        System.out.println("| Capacité " + (i + 1) + " : " + pok.capacitesApprises[i].getNom()
                                + ", PP : " + pok.capacitesApprises[i].getPP()
                                + ", Puissance : " + pok.capacitesApprises[i].getPuissance());
                    }
                }

                Scanner scNumero = new Scanner(System.in);
                System.out.println("Quel est le numéro de la capacité que vous souhaitez supprimer pour " + pok.getNom());
                int numC = scNumero.nextInt() - 1;

                pok.remplaceCapacite(numC, tabCapacitesPasApprises.get(reponse));
                System.out.println(pok.getNom() + " apprend " + tabCapacitesPasApprises.get(reponse) + " à la place de " + pok.getCapacitesApprises()[numC] + ".\r\n");

            }
        }
    }

    public String toString() {
        return "Dresseur Humain : "+getNom();
    }

}

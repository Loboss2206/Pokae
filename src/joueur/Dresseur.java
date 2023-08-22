package joueur;

import java.util.Arrays;
import java.util.Scanner;

import capacités.Attaque;
import capacités.Capacite;
import capacités.Echange;
import combat.Combat;
import interfaces.IAttaque;
import interfaces.ICapacite;
import interfaces.IDresseur;
import interfaces.IPokemon;
import pokedex.Pokedex;
import pokemon.Pokemon;
import testPokae.Main;

/**
 * Classe Dresseur du package joueur, elle implémente l'interface IDresseur, cette classe permet la structure d'un dresseur
 * @author Logan Brunet, Lucas Schiavetti, Sacha Hassan, Logan Laporte, Arthur Bailleul
 *
 */

public abstract class Dresseur implements IDresseur {
    private String nom;
    private int niveau;
    private Pokemon ranch[];
    private Pokemon combattant;
    private Combat combatActuel;

    // Constructeur
    public Dresseur() {
        ranch = Main.pokedexGeneral.engendreRanch();
        initCapacitesRanch();
        niveau = calculNiveau();
    }

    public void setNom(String nom) {
        this.nom = nom;
    }


    // Getters & Setters
    /**
     * Le getter getNom renvoie le nom du dresseur
     * @return Le nom du dresseur
     */
    @Override
    public String getNom() {
        return nom;
    }

    /**
     * Le getter getNiveau renvoie le niveau cumule des niveaux des pokemons du dresseur
     * @return Le niveau cumule des pokemons
     */
    @Override
    public int getNiveau() {
        return niveau;
    }

    /**
     * Le getter getPokemon renvoie un pokemon du ranch du dresseur
     * @return un pokemon du ranch du dresseur
     */
    @Override
    public IPokemon getPokemon(int i) {
        return ranch[i];
    }

    /**
     * Le getter getCombattant renvoie le pokemon en combat du dresseur
     * @return Le ranch du dresseur
     */
    public Pokemon getCombattant() {
        return combattant;
    }

    /**
     * Le getter getCombattant renvoie le ranch du dresseur
     * @return Le ranch du dresseur
     */
    public Pokemon[] getRanch() {
        return ranch;
    }

    /**
     * Le Setters setRanch fixe un ranch pour le dresseur
     */
    public void setRanch(Pokemon[] ranchPokemon) {
        ranch = ranchPokemon;
    }

    /**
     * Le Setters setCombattant fixe un combattant au dresseur
     */
    public void setCombattant(IPokemon combattant) {
        this.combattant = (Pokemon) combattant;
    }

    /**
     * Le getter getCombatActuel renvoie le combat dans lequel le dresseur est
     * @return le combat du dresseur
     */
    public Combat getCombatActuel() {
        return combatActuel;
    }

    /**
     * Le Setters setCombatActuel fixe un combat pour un dress
     */
    public void setCombatActuel(Combat combatActuel) {
        this.combatActuel = combatActuel;
    }

    public int calculNiveau() {
        int niveauTotal = 0;
        for (int i = 0;i<ranch.length;i++) {
            niveauTotal += ranch[i].getNiveau();
        }
        return niveauTotal;
    }

    @Override
    public void enseigne(IPokemon pok, ICapacite[] caps) {
        pok.apprendCapacites(caps);
    }

    @Override
    public void soigneRanch() {
        for (int i = 0; i<ranch.length;i++) {
            ranch[i].soigne();
        }
    }

    /**
     * @return
     */
    @Override
    public abstract IPokemon choisitCombattant();

    /**
     * @param pok
     * @return
     */
    @Override
    public abstract IPokemon choisitCombattantContre(IPokemon pok);

    /**
     *
     * @param attaquant
     * @param defenseur
     * @return
     */
    @Override
    public abstract IAttaque choisitAttaque(IPokemon attaquant, IPokemon defenseur);

    public abstract void initCapacitesRanch();
    public boolean ranchEvanoui() {
        boolean RanchEntierEvanoui = true;
        int i = 0;
        while (RanchEntierEvanoui == true && i<ranch.length ) {
            if (ranch[i].horsCombat() == false) RanchEntierEvanoui = false;
            i++;
        }

        if (dernierCombattant() == true) {
            Pokemon dernier = null;
            for (int j = 0;j<6;j++) {
                if (getRanch()[j].horsCombat() == false) dernier = getRanch()[j];
            }
            int nombreCapacites = 0;
            for (int j = 0; j < 4; j++) {
                try {
                    if (dernier.getCapacitesApprises()[j] != null) nombreCapacites++;
                } catch (NullPointerException e) {}
            }
            if (nombreCapacites == 0) RanchEntierEvanoui = true;
        }

        return RanchEntierEvanoui;
    }

    /**
     * Enleve un element d'un tableau de capacites
     * @param tabCapacites, tableau des capacites
     * @param i, element que l'on veut enlever du tableau
     *
     */
    public static Capacite[] enleverElementTabCapacites(Capacite[] tabCapacites, int i) {
        for (int j = i;j<tabCapacites.length - 1;j++) {
            tabCapacites[j] = tabCapacites[j+1];
        }
        tabCapacites[tabCapacites.length - 1] = null;
        Capacite[] nouveauTableau = new Capacite[tabCapacites.length - 1];
        for (int k = 0;k<nouveauTableau.length;k++) {
            nouveauTableau[k] = tabCapacites[k];
        }
        return nouveauTableau;
    }

    public boolean dernierCombattant() {
        int nombrePokemonHorsCombat = 0;

        for (int i = 0;i<6;i++) {
            if (getRanch()[i].horsCombat() == true) nombrePokemonHorsCombat++;
        }

        return (nombrePokemonHorsCombat == 5);
    }

    public void peutEnseignerCapacité(Pokemon pok) {}

    @Override
    public IPokemon[] getRanchCopy() {
        IPokemon[] copieRanch = Arrays.copyOf(getRanch(),getRanch().length);
        return copieRanch;
    }

    public Dresseur getDresseurCopy() {
        Dresseur dresseurCopie;
        if (this instanceof DresseurIAElaboree) {
            dresseurCopie = new DresseurIAElaboree();
        }
        else if (this instanceof DresseurIARandom) {
            dresseurCopie = new DresseurIARandom();
        }
        else {
            dresseurCopie = new DresseurHumain();
        }
        dresseurCopie.setNom(this.getNom());
        dresseurCopie.setRanch((Pokemon[]) this.getRanchCopy());
        dresseurCopie.setCombattant(this.getCombattant());
        dresseurCopie.setCombatActuel(this.getCombatActuel());
        int niveauDresseur = 0;
        for (int i = 0;i<dresseurCopie.getRanch().length;i++) {
            niveauDresseur += dresseurCopie.getRanch()[i].getNiveau();
        }
        dresseurCopie.niveau = niveauDresseur;
        return dresseurCopie;
    }

    public int getPVTotaux() {
        int PVTotaux = 0;
        for (int i = 0;i<6;i++) {
            PVTotaux += getRanch()[i].getStat().getPV();
        }
        return PVTotaux;
    }

    @Override
    public String toString() {
        return "Dresseur [nom=" + nom + ", niveau=" + niveau + ", ranch=" + Arrays.toString(ranch) + ", combattant="
                + combattant + "]";
    }


}

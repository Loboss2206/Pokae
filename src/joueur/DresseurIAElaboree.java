package joueur;

/**
 * Classe DresseurIAElaboree du package pokemon, elle implémente l'interface IDresseur, cette classe permet de définir comment une ia vas réagir durant un combat
 * @author Lucas Schiavetti, Sacha Hassan, Logan Brunet, Logan Laporte, Arthur Bailleul
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import capacités.Attaque;
import capacités.Capacite;
import capacités.Categorie;
import capacités.Echange;
import combat.EtatDeJeu;
import interfaces.IAttaque;
import interfaces.IPokemon;
import interfaces.IStrategy;
import pokemon.EType;
import pokemon.Pokemon;
import testPokae.Main;


public class DresseurIAElaboree extends Dresseur implements IStrategy {

    public DresseurIAElaboree() {
        super();
        String[] dresseurNoms = {"Jean","Claude","Alexis","Alexandre",
                "Theo","Leo","Logan","Vincent","Arthur","Clement","Dimitri",
                "Sacha","Paul","Natasha","Ondine","Pierre","Lucas","Philippe",
                "Maxime","Blue","Red","Inconnu","MystÃ¨re","Marina","Marc","Soraya",
                "Antoine","Yugi","Nicolas","Anne","Henry","Zinedine","Tamas","Titouneeee",
                "Raphaël","Clement","Cop","WallE","Eve","Luffy","Zoro","Gabin",
                "Sanji","Guts", "Jotaro","Lana","Flash","SuperMan","Mathieu",
                "Simon","Abella","Christopher","Elsa","Winnie","Dwayne","Remy","Anna",
                "Madison","Kayba","Gaëtan"};
        Random randNom = new Random();
        int indexRandom = randNom.nextInt(dresseurNoms.length);
        String nomDresseurIA = dresseurNoms[indexRandom];
        Random randNumero = new Random();
        int numero = randNumero.nextInt(100);
        this.setNom("Robot-Intelligent-" + nomDresseurIA + numero);
    }

    /**
	 * La fonction permet a L'IA de choisir un premier pokemon a envoyer au combat 
	 * @return le numero du pokemon dans le ranch du dresseur
	 */
    public Pokemon choisitCombattant() {
        int numPokemonFinal = 0;
        int calc = 0, max = 0;

        for(int numPokemon = 0; numPokemon < 6; numPokemon++) {

            for(int numCombattantAdverse = 0; numCombattantAdverse < 6; numCombattantAdverse++) {

                for(int nbCapcite = 0; nbCapcite < this.getPokemon(numPokemon).getCapacitesApprises().length; nbCapcite++) {

                    try {
                        calc += this.getPokemon(numPokemon).getCapacitesApprises()[nbCapcite].calculeDommage(this.getPokemon(numPokemon),
                                this.getCombatActuel().getDresseur2().getPokemon(numCombattantAdverse));
                    }
                    catch (NullPointerException e) {

                    }
                }
                for(int nbCapcite = 0; nbCapcite < this.getCombatActuel().getDresseur2().getPokemon(numCombattantAdverse).getCapacitesApprises().length; nbCapcite++) {
                    try {
                        calc += this.getPokemon(numPokemon).getCapacitesApprises()[nbCapcite].calculeDommage(this.getCombatActuel().getDresseur2().getPokemon(numCombattantAdverse),
                                this.getPokemon(numPokemon));
                    }
                    catch (NullPointerException e) {

                    }
                }
            }
            if(calc > max) {
                max = calc;
                numPokemonFinal = numPokemon;
            }
        }
        return this.getRanch()[numPokemonFinal];
    }

	/**
	 * La fonction permet a L'IA de choisir un pokemon a envoyer au combat selon le type des pokemon de son ranch et du type du pokemon adverse afin d'avoir la meilleur efficacite de combat
	 * @param pok, le pokemon du dresseur
	 * @return le numero du pokemon dans le ranch du dresseur
	 */  
    public Pokemon choisitCombattantContre(IPokemon pok) {
        int numeroCombattant = 0;
        double max = 0;

        for(int numeroCombattantAdverse = 0; numeroCombattantAdverse<getRanch().length; numeroCombattantAdverse++) {
            if (getRanch()[numeroCombattantAdverse].horsCombat() == false) {
                double calc;
                double efficacitePokemonType1 = 0;
                double efficacitePokemonType2 = 0;

                try {
                    efficacitePokemonType1 =
                            Main.pokedexGeneral.getEfficacite(this.getPokemon(numeroCombattantAdverse).getEspece().getTypes()[0], pok.getEspece().getTypes()[0]) *
                                    Main.pokedexGeneral.getEfficacite(this.getPokemon(numeroCombattantAdverse).getEspece().getTypes()[0], pok.getEspece().getTypes()[1]);
                } catch (NullPointerException e) {
                    efficacitePokemonType1 = (Main.pokedexGeneral.getEfficacite(this.getPokemon(numeroCombattantAdverse).getEspece().getTypes()[0], pok.getEspece().getTypes()[0]));
                }

                try {
                    efficacitePokemonType2 =
                            Main.pokedexGeneral.getEfficacite(this.getPokemon(numeroCombattantAdverse).getEspece().getTypes()[1], pok.getEspece().getTypes()[0]) *
                                    Main.pokedexGeneral.getEfficacite(this.getPokemon(numeroCombattantAdverse).getEspece().getTypes()[1], pok.getEspece().getTypes()[1]);
                } catch (NullPointerException ex) {
                    try {
                        efficacitePokemonType2 = Main.pokedexGeneral.getEfficacite(this.getPokemon(numeroCombattantAdverse).getEspece().getTypes()[1], pok.getEspece().getTypes()[0]);
                    } catch (NullPointerException e) {
                        efficacitePokemonType2 = 1;
                    }
                }

                calc = efficacitePokemonType1 * efficacitePokemonType2;

                if (calc > max) {
                    max = calc;
                    numeroCombattant = numeroCombattantAdverse;
                }
            }
        }

        return this.getRanch()[numeroCombattant];
    }

	/**
	 * La fonction permet a l'IA de choisir la meilleur attaque possible
	 * @param attaquant, pokemon attaquant du tour
	 * @param defenseur, pokemon defenseur du tour
	 * @return null
	 */     
    public IAttaque choisitAttaque(IPokemon attaquant, IPokemon defenseur) {

        boolean choixDefinitif = false;
        IAttaque attaque = null;
        while (choixDefinitif == false) {
            if (attaquant.getStat().getVitesse() > defenseur.getStat().getVitesse()) {
                for (int i = 0; i < 4; i++) {
                    try {
                        if (attaquant.getCapacitesApprises()[i].getPuissance() == -1) {
                            choixDefinitif = true;
                            attaque = attaquant.getCapacitesApprises()[i];
                        }
                        else {
                            if (attaquant.getCapacitesApprises()[i].calculeDommage(attaquant,defenseur) > (double) defenseur.getStat().getPV()) {
                                choixDefinitif = true;
                                attaque = attaquant.getCapacitesApprises()[i];
                            }
                            else {
                                double calc;
                                double efficacitePokemonType1 = 0;
                                double efficacitePokemonType2 = 0;
                                try {
                                    efficacitePokemonType1 =
                                            Main.pokedexGeneral.getEfficacite(attaquant.getEspece().getTypes()[0], defenseur.getEspece().getTypes()[0]) *
                                                    Main.pokedexGeneral.getEfficacite(attaquant.getEspece().getTypes()[0], defenseur.getEspece().getTypes()[1]);
                                } catch (NullPointerException e) {
                                    efficacitePokemonType1 = (Main.pokedexGeneral.getEfficacite(attaquant.getEspece().getTypes()[0], defenseur.getEspece().getTypes()[0]));
                                }

                                try {
                                    efficacitePokemonType2 =
                                            Main.pokedexGeneral.getEfficacite(attaquant.getEspece().getTypes()[1], defenseur.getEspece().getTypes()[0]) *
                                                    Main.pokedexGeneral.getEfficacite(attaquant.getEspece().getTypes()[1], defenseur.getEspece().getTypes()[1]);
                                } catch (NullPointerException ex) {
                                    try {
                                        efficacitePokemonType2 = Main.pokedexGeneral.getEfficacite(attaquant.getEspece().getTypes()[1], defenseur.getEspece().getTypes()[0]);
                                    } catch (NullPointerException e) {
                                        efficacitePokemonType2 = 1;
                                    }
                                }

                                calc = efficacitePokemonType1 * efficacitePokemonType2;
                                if (calc >= 2) {
                                    for (int j = 0;j<4;j++) {
                                        try {
                                            if (Main.pokedexGeneral.getEfficacite(attaquant.getCapacitesApprises()[j].getType(),defenseur.getEspece().getTypes()[0]) >= 1) {
                                                choixDefinitif = true;
                                                attaque = attaquant.getCapacitesApprises()[j];
                                            }
                                        } catch (NullPointerException e) {}
                                    }
                                }
                                else {
                                    if (((Pokemon)attaquant).nombre_echanges >= 5) {
                                        try {
                                            attaque = attaquant.getCapacitesApprises()[0];
                                            choixDefinitif = true;
                                        } catch (NullPointerException e) {
                                            choixDefinitif = true;
                                            Echange echange = new Echange(this);
                                            echange.setPokemon(this.choisitCombattantContre(defenseur));
                                            attaque = echange;
                                        }
                                    }
                                    else {
                                        System.out.println("1");
                                        choixDefinitif = true;
                                        Echange echange = new Echange(this);
                                        echange.setPokemon(this.choisitCombattantContre(defenseur));
                                        attaque = echange;
                                    }
                                }

                            }
                        }
                    } catch (NullPointerException e) {
                    }
                }
            }
            else {

                if (((Pokemon)attaquant).nombre_echanges >= 5) {
                    try {
                        attaque = attaquant.getCapacitesApprises()[0];
                        choixDefinitif = true;
                    } catch (NullPointerException e) {
                        choixDefinitif = true;
                        Echange echange = new Echange(this);
                        echange.setPokemon(this.choisitCombattantContre(defenseur));
                        attaque = echange;
                    }
                }
                else {
                    choixDefinitif = true;
                    Echange echange = new Echange(this);
                    echange.setPokemon(this.choisitCombattantContre(defenseur));
                    attaque = echange;
                }
            }
        }
        return attaque;


        /* Code MinMax qui a des bugs
        EtatDeJeu etat = new EtatDeJeu(this,getCombatActuel().getDresseur2());
        return (IAttaque) fonction_evaluation(etat,1)[1];
        */
    }
    /*
    
    public Object[] fonction_evaluation(EtatDeJeu X, int n) {

        int pvTotaux1 = 0;
        int pvTotaux2 = 0;
        for (int j = 0;j<6;j++) {
            pvTotaux1 += X.getDresseur1().getRanch()[j].getStat().getPV();
            pvTotaux2 += X.getDresseur2().getRanch()[j].getStat().getPV();
        }

        // Calcul de la probabilité
        if (X.isEtatTerminal() || n == 0) {
            double val = pvTotaux1 / (pvTotaux1 + pvTotaux2);
            return new Object[]{val, null};
        }
        else {
            // Création des attaques du dresseur1
            IAttaque[] C1 = new IAttaque[5];

            for (int j = 0;j<4;j++) {
                try {
                    C1[j] = X.getDresseur1().getCombattant().getCapacitesApprises()[j];
                } catch (NullPointerException e) {}
            }
            Echange coupEchange = new Echange(X.getDresseur1());
            coupEchange.setPokemon(choisitCombattantContre(X.getDresseur2().getCombattant()));
            C1[4] = coupEchange;

            // Création des attaques du dresseur2
            IAttaque[] C2 = new IAttaque[5];

            for (int j = 0;j<4;j++) {
                try {
                    C2[j] = X.getDresseur2().getCombattant().getCapacitesApprises()[j];
                } catch (NullPointerException e) {}
            }

            Echange coupEchange2 = new Echange(X.getDresseur2());
            coupEchange2.setPokemon(choisitCombattantContre(X.getDresseur1().getCombattant()));
            C2[4] = coupEchange2;


            ////////////////////////////////////////////////////////////////////////////////////////////////////


            double max = 0;
            IAttaque cmax = C1[0];
            System.out.println(X.getDresseur1().getCombattant().getStat().getPV());
            for (IAttaque coups1 : C1) {
                if (coups1 != null) {
                    double min = 1;
                    for (IAttaque coups2 : C2) {
                        double val = 0;
                        if (coups2 != null) {
                            if (coups1 instanceof Capacite) {
                                if (coups2 instanceof Capacite) {
                                    // 1er etat possible
                                    Dresseur dresseurCopie1 = X.getDresseur1().getDresseurCopy();
                                    Dresseur dresseurCopie2 = X.getDresseur2().getDresseurCopy();

                                    if (dresseurCopie1.getCombattant().getStat().getVitesse() > dresseurCopie2.getCombattant().getStat().getVitesse()) {
                                        dresseurCopie2.getCombattant().subitAttaqueDe(dresseurCopie1.getCombattant(), coups1);
                                        dresseurCopie1.getCombattant().subitAttaqueDe(dresseurCopie2.getCombattant(), coups2);
                                    } else {
                                        dresseurCopie1.getCombattant().subitAttaqueDe(dresseurCopie2.getCombattant(), coups2);
                                        dresseurCopie2.getCombattant().subitAttaqueDe(dresseurCopie1.getCombattant(), coups1);
                                    }

                                    EtatDeJeu X_1 = new EtatDeJeu(dresseurCopie1, dresseurCopie2);
                                    double P_1 = ((Capacite) coups1).getPrecision() * ((Capacite) coups2).getPrecision();
                                    val += P_1 * (double) fonction_evaluation(X_1, n - 1)[0];


                                    // 2eme etat possible
                                    Dresseur dresseurCopie3 = X.getDresseur1().getDresseurCopy();
                                    Dresseur dresseurCopie4 = X.getDresseur2().getDresseurCopy();

                                    dresseurCopie4.getCombattant().subitAttaqueDe(dresseurCopie3.getCombattant(), coups1);

                                    EtatDeJeu X_2 = new EtatDeJeu(dresseurCopie3, dresseurCopie4);
                                    double P_2 = ((Capacite) coups1).getPrecision() * (1 - ((Capacite) coups2).getPrecision());
                                    val += P_2 * (double) fonction_evaluation(X_2, n - 1)[0];


                                    // 3eme etat possible
                                    Dresseur dresseurCopie5 = X.getDresseur1().getDresseurCopy();
                                    Dresseur dresseurCopie6 = X.getDresseur2().getDresseurCopy();

                                    dresseurCopie5.getCombattant().subitAttaqueDe(dresseurCopie6.getCombattant(), coups2);

                                    EtatDeJeu X_3 = new EtatDeJeu(dresseurCopie5, dresseurCopie6);
                                    double P_3 = (1 - ((Capacite) coups1).getPrecision()) * ((Capacite) coups2).getPrecision();
                                    val += P_3 * (double) fonction_evaluation(X_3, n - 1)[0];


                                    // 4eme etat possible
                                    EtatDeJeu X_4 = new EtatDeJeu(X.getDresseur1(), X.getDresseur2());
                                    double P_4 = (1 - ((Capacite) coups1).getPrecision()) * (1 - ((Capacite) coups2).getPrecision());
                                    val += P_4 * (double) fonction_evaluation(X_4, n - 1)[0];
                                } else {
                                    // 1er etat possible
                                    Dresseur dresseurCopie1 = X.getDresseur1().getDresseurCopy();
                                    Dresseur dresseurCopie2 = X.getDresseur2().getDresseurCopy();

                                    if (dresseurCopie1.getCombattant().getStat().getVitesse() > dresseurCopie2.getCombattant().getStat().getVitesse()) {
                                        dresseurCopie2.getCombattant().subitAttaqueDe(dresseurCopie1.getCombattant(), coups1);
                                        dresseurCopie1.getCombattant().subitAttaqueDe(dresseurCopie2.getCombattant(), coups2);
                                    } else {
                                        dresseurCopie1.getCombattant().subitAttaqueDe(dresseurCopie2.getCombattant(), coups2);
                                        dresseurCopie2.getCombattant().subitAttaqueDe(dresseurCopie1.getCombattant(), coups1);
                                    }

                                    EtatDeJeu X_1 = new EtatDeJeu(dresseurCopie1, dresseurCopie2);
                                    double P_1 = ((Capacite) coups1).getPrecision();
                                    val += P_1 * (double) fonction_evaluation(X_1, n - 1)[0];

                                    // 2eme etat possible
                                    Dresseur dresseurCopie3 = X.getDresseur1().getDresseurCopy();
                                    Dresseur dresseurCopie4 = X.getDresseur2().getDresseurCopy();

                                    dresseurCopie3.getCombattant().subitAttaqueDe(dresseurCopie4.getCombattant(), coups2);


                                    EtatDeJeu X_2 = new EtatDeJeu(dresseurCopie3, dresseurCopie4);
                                    double P_2 = 1 - ((Capacite) coups1).getPrecision();
                                    val += P_2 * (double) fonction_evaluation(X_2, n - 1)[0];

                                }
                            } else {
                                if (coups2 instanceof Capacite) {
                                    // 1er etat possible
                                    Dresseur dresseurCopie1 = X.getDresseur1().getDresseurCopy();
                                    Dresseur dresseurCopie2 = X.getDresseur2().getDresseurCopy();

                                    if (dresseurCopie1.getCombattant().getStat().getVitesse() > dresseurCopie2.getCombattant().getStat().getVitesse()) {
                                        dresseurCopie2.getCombattant().subitAttaqueDe(dresseurCopie1.getCombattant(), coups1);
                                        dresseurCopie1.getCombattant().subitAttaqueDe(dresseurCopie2.getCombattant(), coups2);
                                    } else {
                                        dresseurCopie1.getCombattant().subitAttaqueDe(dresseurCopie2.getCombattant(), coups2);
                                        dresseurCopie2.getCombattant().subitAttaqueDe(dresseurCopie1.getCombattant(), coups1);
                                    }

                                    EtatDeJeu X_1 = new EtatDeJeu(dresseurCopie1, dresseurCopie2);
                                    double P_1 = ((Capacite) coups2).getPrecision();
                                    val += P_1 * (double) fonction_evaluation(X_1, n - 1)[0];


                                    // 2eme etat possible
                                    Dresseur dresseurCopie3 = X.getDresseur1().getDresseurCopy();
                                    Dresseur dresseurCopie4 = X.getDresseur2().getDresseurCopy();

                                    dresseurCopie4.getCombattant().subitAttaqueDe(dresseurCopie3.getCombattant(), coups2);

                                    EtatDeJeu X_2 = new EtatDeJeu(dresseurCopie3, dresseurCopie4);
                                    double P_2 = 1 - ((Capacite) coups2).getPrecision();
                                    val += P_2 * (double) fonction_evaluation(X_2, n - 1)[0];
                                } else {
                                    // Seul etat possible
                                    Dresseur dresseurCopie1 = X.getDresseur1().getDresseurCopy();
                                    Dresseur dresseurCopie2 = X.getDresseur2().getDresseurCopy();

                                    dresseurCopie1.getCombattant().subitAttaqueDe(dresseurCopie2.getCombattant(), coups2);
                                    dresseurCopie2.getCombattant().subitAttaqueDe(dresseurCopie1.getCombattant(), coups1);

                                    EtatDeJeu X_1 = new EtatDeJeu(dresseurCopie1, dresseurCopie2);
                                    double P_1 = 1;
                                    val += P_1 * (double) fonction_evaluation(X_1, n - 1)[0];
                                }
                            }
                        }

                        min = Math.min(min, val);
                        if (min > max) {
                            max = min;
                            cmax = (Attaque) coups1;
                        }
                        max = Math.max(min, max);
                    }
                }

            }
            System.out.println(X.getDresseur1().getCombattant().getStat().getPV());
            return new Object[]{max,cmax};

        }
    }
    */

	/**
	 * La fonction permet a L'IA d'initialiser les capacites de chaque pokemon de son ranch
	 */  
    @Override
    public void initCapacitesRanch() {
        for (int i=0; i<6; i++){

            Capacite[] capacitesSousLeNiveau1 = getRanch()[i].getEspece().getCapacitesSousNiveau(1);

            int k = 0;
            while (k < capacitesSousLeNiveau1.length && k < 4) {
                Random random = new Random();
                int choixCapacites;
                try {
                    choixCapacites = random.nextInt(capacitesSousLeNiveau1.length - 2);
                } catch (IllegalArgumentException e) {
                    choixCapacites = 0;
                }

                Capacite c = capacitesSousLeNiveau1[choixCapacites];

                try {
                    Capacite capacitePokemon = new Capacite(c.id,c.getNom(),c.getPrecision(),c.getPuissance(),c.getPP(),(Categorie) c.getCategorie(),(EType) c.getType());
                    getRanch()[i].getCapacitesApprises()[k] = capacitePokemon;
                    enleverElementTabCapacites(capacitesSousLeNiveau1,choixCapacites);
                } catch (NullPointerException e) {}

                k++;
            }
        }
    }

     /**
	 * La fonction permet a L'IA de choisir une capacite a apprendre a son pokemon qui vient de monter de niveau
	 * @param pok, le pokemon du dresseur
	 */  
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

            Random rand = new Random();
            int reponse = rand.nextInt(tabCapacitesPasApprises.size() + 1);

            if (reponse != 0) {
                reponse--;

                Random randCap = new Random();
                int numC = randCap.nextInt(4);

                pok.remplaceCapacite(numC, tabCapacitesPasApprises.get(reponse));
                System.out.println(pok.getNom() + " apprend " + tabCapacitesPasApprises.get(reponse) + " à la place de " + pok.getCapacitesApprises()[numC] + ".\r\n");
            }
        }
    }
    public String toString() {
        return "Dresseur IA : "+getNom();
    }

}

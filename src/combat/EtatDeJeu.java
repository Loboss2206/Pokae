package combat;

import joueur.Dresseur;
import joueur.DresseurIAElaboree;
import pokemon.Pokemon;

public class EtatDeJeu {
    private Dresseur dresseur1;
    private Dresseur dresseur2;

    public EtatDeJeu(Dresseur dresseur1, Dresseur dresseur2) {
        this.dresseur1 = dresseur1.getDresseurCopy();
        this.dresseur2 = dresseur2.getDresseurCopy();
    }

    public boolean isEtatTerminal() {
        boolean reponse = true;
        int i = 0;
        while (reponse == true || i < 6) {
            if (dresseur1.getRanch()[i].horsCombat() == false || dresseur2.getRanch()[i].horsCombat() == false) {
                reponse = false;
            }
            i++;
        }

        int pvTotaux1 = 0;
        int pvTotaux2 = 0;
        for (int j = 0;j<6;j++) {
            pvTotaux1 += dresseur1.getRanch()[j].getStat().getPV();
            pvTotaux2 += dresseur1.getRanch()[j].getStat().getPV();
        }
        if (pvTotaux1 == 0 || pvTotaux2 == 0) reponse = true;

        return reponse;
    }


    public Dresseur getDresseur1() {
        return dresseur1;
    }

    public void setDresseur1(Dresseur dresseur1) {
        this.dresseur1 = dresseur1;
    }

    public Dresseur getDresseur2() {
        return dresseur2;
    }

    public void setDresseur2(Dresseur dresseur2) {
        this.dresseur2 = dresseur2;
    }

}

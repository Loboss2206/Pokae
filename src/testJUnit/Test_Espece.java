package testJUnit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pokedex.Pokedex;
import pokemon.Espece;
import java.io.IOException;

class Test_Espece {

    Pokedex p;
    Espece especeTest;
    Espece especeEvolution;
    Espece especeTest2;
    Espece especeEvolution2;
    @BeforeEach
    protected void setUp() {
        p = new Pokedex();
        try {
            p.initialisationEspeces();
            p.initialisationEvolution();
        } catch (IOException exception) {}

        especeTest = p.getPokemonInfoId(61);
        especeEvolution = p.getPokemonInfoId(62);
        especeTest2 = p.getPokemonInfoId(67);
        especeEvolution2 = p.getPokemonInfoId(68);
    }

    @Test
    void testGetNiveauEvolution() {
        Espece SabeletteTest = p.getInfo("Sabelette");

        assertTrue(especeTest.getNiveauEvolution() == 45);
        assertFalse(especeTest.getNiveauEvolution() == 2);
        assertNotSame(especeTest.getNiveauEvolution(), 6);
        assertEquals(especeTest2.getNiveauEvolution(), 48);

        assertEquals(SabeletteTest.getNiveauEvolution(), 22);
        assertNotSame(SabeletteTest.getNiveauEvolution(), 58);
    }

    @Test
    void testGetEspeceEvolution() {
        Espece SabeletteTest = p.getInfo("Sabelette");

        assertEquals(especeTest.getEspeceEvolution().getNom(), "Tartard");
        assertFalse(especeTest.getEspeceEvolution().getNom()== "Ectoplasma");

        assertNotSame(especeTest.getEspeceEvolution().getNom(), "Triopiqueur");
        assertEquals(especeTest2.getEspeceEvolution().getNom(), "Mackogneur");
        assertNotSame(especeTest2.getEspeceEvolution().getNom(), "Pikachu");

        assertEquals(SabeletteTest.getEspeceEvolution().getNom(), "Sablaireau");
        assertNotSame(SabeletteTest.getEspeceEvolution().getNom(), "Dracaufeu");
    }

    @Test
    void testGetBaseStat() {
        Espece SabeletteTest = p.getInfo("Sabelette");

        assertEquals(especeTest.getBaseStat().getPV(), 65);
        assertEquals(especeTest.getBaseStat().getForce(), 65);
        assertEquals(especeTest.getBaseStat().getDefense(), 65);
        assertEquals(especeTest.getBaseStat().getSpecial(),50);
        assertEquals(especeTest.getBaseStat().getVitesse(), 90);

        assertFalse(especeTest.getBaseStat().getPV() == 42);
        assertFalse(especeTest.getBaseStat().getForce() == 42);
        assertFalse(especeTest.getBaseStat().getDefense() == 42);
        assertFalse(especeTest.getBaseStat().getSpecial() == 42);
        assertFalse(especeTest.getBaseStat().getVitesse() == 42);

        assertEquals(especeTest2.getBaseStat().getPV(), 80);
        assertEquals(especeTest2.getBaseStat().getForce(), 100);
        assertEquals(especeTest2.getBaseStat().getDefense(), 70);
        assertEquals(especeTest2.getBaseStat().getSpecial(), 50);
        assertEquals(especeTest2.getBaseStat().getVitesse(), 45);

        assertNotSame(especeTest2.getBaseStat().getPV(), 42);
        assertNotSame(especeTest2.getBaseStat().getForce(), 42);
        assertNotSame(especeTest2.getBaseStat().getDefense(), 42);
        assertNotSame(especeTest2.getBaseStat().getSpecial(),  42);
        assertNotSame(especeTest2.getBaseStat().getVitesse(), 42);

        assertEquals(SabeletteTest.getBaseStat().getPV(), 50);
        assertEquals(SabeletteTest.getBaseStat().getForce(), 75);
        assertEquals(SabeletteTest.getBaseStat().getDefense(), 85);
        assertEquals(SabeletteTest.getBaseStat().getSpecial(), 30);
        assertEquals(SabeletteTest.getBaseStat().getVitesse(), 40);

        assertNotSame(SabeletteTest.getBaseStat().getPV(), 428);
        assertNotSame(SabeletteTest.getBaseStat().getForce(), 15);
        assertNotSame(SabeletteTest.getBaseStat().getDefense(), 82);
        assertNotSame(SabeletteTest.getBaseStat().getSpecial(),  32);
        assertNotSame(SabeletteTest.getBaseStat().getVitesse(), 48);
    }

    @Test
    void testGetNom() {
        Espece SabeletteTest = p.getInfo("Sabelette");

        assertEquals(especeTest.getNom(),"Têtarte");
        assertFalse(especeTest.getNom() == "Ectoplasma");

        assertNotSame(especeTest2.getNom(), "Tétarte");
        assertEquals(especeTest2.getNom(), "Machopeur");

        assertNotSame(SabeletteTest.getNom(), "Raichu");
        assertEquals(SabeletteTest.getNom(), "Sabelette");
    }

    @Test
    void testGetNiveauDepart() {
        Espece SabeletteTest = p.getInfo("Sabelette");

        assertTrue(especeTest.getNiveauDepart() == 25);
        assertFalse(especeTest.getNiveauDepart() == 42);

        assertEquals(especeTest2.getNiveauDepart(), 28);
        assertNotSame(especeTest2.getNiveauDepart(), 69);

        assertEquals(SabeletteTest.getNiveauDepart(), 1);
        assertNotSame(SabeletteTest.getNiveauDepart(), 25);
    }

    @Test
    void testGetBaseExp() {
        Espece SabeletteTest = p.getInfo("Sabelette");

        assertTrue(especeTest.getBaseExp() == 131);
        assertFalse(especeTest.getBaseExp() == 667);

        assertEquals(especeTest2.getBaseExp(), 146);
        assertNotSame(especeTest2.getBaseExp(),124);

        assertEquals(SabeletteTest.getBaseExp(), 93);
        assertNotSame(SabeletteTest.getBaseExp(),124);
    }

    @Test
    void testGetGainsStat() {
        Espece SabeletteTest = p.getInfo("Sabelette");

        assertTrue(especeTest.getGainsStat().getPV() == 0);
        assertTrue(especeTest.getGainsStat().getForce() == 0);
        assertTrue(especeTest.getGainsStat().getDefense() == 0);
        assertTrue(especeTest.getGainsStat().getSpecial() == 0);
        assertTrue(especeTest.getGainsStat().getVitesse() == 2);

        assertFalse(especeTest.getGainsStat().getPV() == 6);
        assertFalse(especeTest.getGainsStat().getForce() == 5);
        assertFalse(especeTest.getGainsStat().getDefense() == 5);
        assertFalse(especeTest.getGainsStat().getSpecial() == 5);
        assertFalse(especeTest.getGainsStat().getVitesse() == 0);

        assertEquals(especeTest2.getGainsStat().getPV(), 0);
        assertEquals(especeTest2.getGainsStat().getForce() ,  2);
        assertEquals(especeTest2.getGainsStat().getDefense() ,  0);
        assertEquals(especeTest2.getGainsStat().getSpecial() , 0);
        assertEquals(especeTest2.getGainsStat().getVitesse(), 0);

        assertNotSame(especeTest2.getGainsStat().getPV(), 2);
        assertNotSame(especeTest2.getGainsStat().getForce() ,  4);
        assertNotSame(especeTest2.getGainsStat().getDefense() ,  6);
        assertNotSame(especeTest2.getGainsStat().getSpecial() , 7);
        assertNotSame(especeTest2.getGainsStat().getVitesse(), 1);

        assertEquals(SabeletteTest.getGainsStat().getPV(), 0);
        assertEquals(SabeletteTest.getGainsStat().getForce() ,  0);
        assertEquals(SabeletteTest.getGainsStat().getDefense() ,  1);
        assertEquals(SabeletteTest.getGainsStat().getSpecial() , 0);
        assertEquals(SabeletteTest.getGainsStat().getVitesse(), 0);

        assertNotSame(SabeletteTest.getGainsStat().getPV(), 2);
        assertNotSame(SabeletteTest.getGainsStat().getForce() ,  4);
        assertNotSame(SabeletteTest.getGainsStat().getDefense() ,  6);
        assertNotSame(SabeletteTest.getGainsStat().getSpecial() , 7);
        assertNotSame(SabeletteTest.getGainsStat().getVitesse(), 1);
    }

    @Test
    void testGetEvolution() {
        Espece SabeletteTest = p.getInfo("Sabelette");
        Espece SablaireauTest = p.getInfo("Sablaireau");

        assertEquals(especeTest.getEvolution(45),especeEvolution);
        assertNotSame(especeTest.getEvolution(5),45);

        assertEquals(especeTest2.getEvolution(48),especeEvolution2);
        assertNotSame(especeTest2.getEvolution(48),especeEvolution);

        assertEquals(SabeletteTest.getEvolution(22),SablaireauTest);
        assertNotSame(SabeletteTest.getEvolution(48),25);
    }


    @Test
    void testGetTypes() {
        Espece SabeletteTest = p.getInfo("Sabelette");

        assertTrue(especeTest.getTypes()[0].getNom() == ("Eau"));
        assertFalse(especeTest.getTypes()[0].getNom() == ("Feu"));

        assertEquals(especeTest2.getTypes()[0].getNom(),("Combat"));
        assertNotSame(especeTest2.getTypes()[0].getNom(), ("Poison"));

        assertEquals(SabeletteTest.getTypes()[0].getNom(),("Sol"));
        assertNotSame(SabeletteTest.getTypes()[0].getNom(), ("Eau"));
    }
}

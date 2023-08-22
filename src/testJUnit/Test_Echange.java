package testJUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import capacités.Echange;
import joueur.DresseurIARandom;
import pokedex.Pokedex;
import pokemon.Pokemon;

public class Test_Echange {

    public Echange echangeTest;
    public Pokemon pokemonDresseur;
    public Pokemon pokemonEchangeTest;
    public DresseurIARandom dresseurTest;

    @BeforeEach
    void setUp() {
    	testPokae.Main.pokedexGeneral = new Pokedex();
        try {
            testPokae.Main.pokedexGeneral.initialisationEspeces();
            testPokae.Main.pokedexGeneral.initialisationEvolution();
            testPokae.Main.pokedexGeneral.initialisationCapacites();
            testPokae.Main.pokedexGeneral.initialisationEfficacites();
        } catch (IOException e) {}

        pokemonDresseur = new Pokemon("DracaufeuTest",testPokae.Main.pokedexGeneral.getPokemonInfoId(9));

        dresseurTest = new DresseurIARandom();
        dresseurTest.setCombattant(pokemonDresseur);

        echangeTest = new Echange(dresseurTest);

        pokemonEchangeTest = new Pokemon("TartardTest",testPokae.Main.pokedexGeneral.getPokemonInfoId(62));
    }

    @Test
    final void testCalculeDommage() {
        int dommage = echangeTest.calculeDommage(echangeTest.getDresseurEchange().getCombattant(),pokemonEchangeTest);
        assertEquals(dommage,0);
    }

    @Test
    final void testUtilise() {
        assertNotEquals(dresseurTest.getCombattant(),pokemonEchangeTest);
        echangeTest.setPokemon(pokemonEchangeTest);
        echangeTest.utilise();
        assertEquals(dresseurTest.getCombattant(),pokemonEchangeTest);
    }

    @Test
    final void testSetPokemon() {
        assertNull(echangeTest.getPokemonRemplacant());
        echangeTest.setPokemon(pokemonEchangeTest);
        assertNotNull(echangeTest.getPokemonRemplacant());
        assertEquals(echangeTest.getPokemonRemplacant(),pokemonEchangeTest);
    }

    @Test
    final void testEchangeCombattant() {
        Pokemon ancienCombattant = dresseurTest.getCombattant();
        echangeTest.setPokemon(pokemonEchangeTest);
        assertEquals(echangeTest.getPokemonRemplacant(),pokemonEchangeTest);

        Pokemon Combattant = (Pokemon) echangeTest.echangeCombattant();

        assertEquals(ancienCombattant,Combattant);
        assertEquals(dresseurTest.getCombattant(),pokemonEchangeTest);
    }

}

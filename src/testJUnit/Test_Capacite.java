package testJUnit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import capacités.Capacite;
import junit.framework.TestCase;
import pokedex.Pokedex;


class Test_Capacite extends TestCase {
	
	Capacite c;
	Pokedex p;
	
	@BeforeEach
	protected void setUp() throws Exception {
		p = new Pokedex();
		p.initialisationEspeces();
		p.initialisationCapacites();
		p.initialisationEvolution();
		
		c = p.getCapacite(1);
	}


	@Test
	final void testUtilise() {
		
		int ppcap = c.getPP();
		c.utilise();
		assertEquals(ppcap-1, c.getPP());
	
	}


	@Test
	final void testGetPrecision() {
		assertTrue((c.getPrecision()<=1)&& (c.getPrecision()>=0));
	}

	@Test
	final void testGetPuissance() {
		assertTrue((c.getPuissance()>=-10)&&(c.getPuissance()<=200));
	}

	@Test
	final void testGetPP() {
		assertTrue((c.getPP()>=5)&&(c.getPP()<=100));
	}

	@Test
	final void testGetCategorie() {
		assertEquals(c.getCategorie().getNom(),"Physique");
	}

	@Test
	final void testGetType() {
		assertTrue((c.getType().getNom()=="Glace")||
				(c.getType().getNom()=="Normal")||
				(c.getType().getNom()=="Vol")||
				(c.getType().getNom()=="Plante")||
				(c.getType().getNom()=="Combat")||
				(c.getType().getNom()=="Feu")||
				(c.getType().getNom()=="Eau")||
				(c.getType().getNom()=="Electrik")||
				(c.getType().getNom()=="Sol")||
				(c.getType().getNom()=="Roche")||
				(c.getType().getNom()=="Psy")||
				(c.getType().getNom()=="Dragon")||
				(c.getType().getNom()=="Spectre")||
				(c.getType().getNom()=="Poison")||
				(c.getType().getNom()=="Insecte"));
	}


}

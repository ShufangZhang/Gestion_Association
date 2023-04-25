package tests;

import association.GestionMembres;
import association.InterMembre;
import association.Membre;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests JUnit de la classe {@link association.GestionMembres GestionMembres}.
 *
 * @author julie.stephant
 * @see association.GestionMembres
 */
class TestGestionMembres {

	/*
	 * membres pour tester (nom, prenom, adresse, age, role)
	 */
	private Membre membre1;
	private Membre membre2;
	private Membre membre3;
	private Membre membre4;

	private GestionMembres gestMem;

	/*
	 * liste des membres
	 */
	private ArrayList<Membre> membres;

	@BeforeEach
	void setUp() throws Exception {
		membre1 = new Membre("A", "W", "B", 19, "president");
		membre2 = new Membre("E", "M", "N", 20, "membre");
		membre3 = new Membre("A", "C", "B", 18, "membre");
		membre4 = new Membre("E", "W", "T", 30, "membre");
		membres = new ArrayList<Membre>();
		gestMem = new GestionMembres(membres);
	}

	/**
	 * Ne fait rien après les tests : � modifier au besoin.
	 *
	 * @throws Exception ne peut pas �tre lev�e ici
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	/**
	 * Vérifie si on peut ajouter le membre à la liste des membres ou il existe
	 * deja.
	 * 
	 */

	@Test
	void testAddMember() {

		gestMem.ajouterMembre(membre1);
		assertTrue(gestMem.membres.contains(membre1));

		gestMem.ajouterMembre(membre2);
		assertTrue(gestMem.membres.contains(membre2));

	}

	/**
	 * Vérifie si on peut supprimer le membre à la liste des membres ou le membre
	 * n'est pas dans la liste.
	 * 
	 */

	@Test
	void testDelMember() {
		gestMem.ajouterMembre(membre3);
		gestMem.ajouterMembre(membre4);

		gestMem.supprimerMembre(membre3);
		assertFalse(gestMem.membres.contains(membre3));
		gestMem.supprimerMembre(membre4);
		assertFalse(gestMem.membres.contains(membre4));

	}

	/**
	 * Vérifie si on peut designer un membre comme president ou le membre n'existe
	 * pas dans la liste. ou il existe un autre president.
	 * 
	 */

	@Test
	void testDesignerPresident() {
		gestMem.ajouterMembre(membre1);
		gestMem.ajouterMembre(membre2);

		assertTrue(gestMem.designerPresident(membre1));
		assertFalse(gestMem.designerPresident(membre2));

	}

	/**
	 * retourne la liste des membres presents dans la liste.
	 * 
	 */

	@Test
	void testRetourListMembres() {

		gestMem.ajouterMembre(membre1);
		gestMem.ajouterMembre(membre2);
		membres = gestMem.ensembleMembres();

		assertEquals(membres.get(0).getInformationPersonnelle().getNom(), "A");
		assertEquals(membres.get(0).getInformationPersonnelle().getPrenom(), "W");
		assertEquals(membres.get(0).getInformationPersonnelle().getAdresse(), "B");
		assertEquals(membres.get(0).getInformationPersonnelle().getAge(), 19);
		assertEquals(membres.get(0).getRole(), "president");

		assertEquals(membres.get(1).getInformationPersonnelle().getNom(), "E");
		assertEquals(membres.get(1).getInformationPersonnelle().getPrenom(), "M");
		assertEquals(membres.get(1).getInformationPersonnelle().getAdresse(), "N");
		assertEquals(membres.get(1).getInformationPersonnelle().getAge(), 20);
		assertEquals(membres.get(1).getRole(), "membre");

	}

	/**
	 * retourne les informations du seul president dans la liste.
	 * 
	 */

	@Test
	void testPresident() {
		// on ajoute des membres qui ne sont pas president
		gestMem.ajouterMembre(membre2);
		gestMem.ajouterMembre(membre3);
		gestMem.ajouterMembre(membre4);
		assertEquals(gestMem.president(), null);

		// on designe le membre 2 comme president
		gestMem.designerPresident(membre2);
		assertEquals(gestMem.president().getInformationPersonnelle().getNom(), "E");
		assertEquals(gestMem.president().getInformationPersonnelle().getPrenom(), "M");
		assertEquals(gestMem.president().getInformationPersonnelle().getAdresse(), "N");
		assertEquals(gestMem.president().getInformationPersonnelle().getAge(), 20);

	}

}
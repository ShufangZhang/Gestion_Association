package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import association.Evenement;
import association.GestionMembres;
import association.InformationPersonnelle;
import association.InterGestionMembres;
import association.InterMembre;
import association.Membre;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.Set;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests JUnit de la classe {@link association.Membre Membre}.
 *
 * @author AOUAD Wiame
 * @see association.Membre
 */
class TestMembre {

	/*
	 * des evenementes
	 */
	private Evenement evt1;
	private Evenement evt2;
	private Membre mb1;
	private Membre mb2;
	private Membre mb3;
	private Membre mb4;
	private InformationPersonnelle ip1;
	private InformationPersonnelle ip2;
	private List<Evenement> evts;

	/**
	 * Instancie des informations sur des membres pour les tests.
	 *
	 * @throws Exception ne peut pas être levée ici
	 */
	@BeforeEach
	void setUp() throws Exception {
		evts = new ArrayList<Evenement>();

		evt1 = new Evenement("Aviron", "Brest", "2023-02-06 14:30", 90, 15);
		evt2 = new Evenement("evenement", "Lille", "2021-11-05 10:00", 120, 30);

		ip1 = new InformationPersonnelle("AOUAD", "Wiame", "Brest", 20);

		mb1 = new Membre("AOUAD", "Wiame", "Brest", 19, "membre");
		mb2 = new Membre("STEPHAN", "Julie", "Paris", 20, "president");
		mb3 = new Membre("MEURLET", "Louane", "FR", 20, "membre", evts);
	}

	/**
	 * Ne fait rien après les tests : à modifier au besoin.
	 *
	 * @throws Exception ne peut pas être levée ici
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	/**
	 * Verifie qu'on peut associer les infos persos à un membre.
	 *
	 */
	@Test
	void testGetInfoPerso1() {
		mb1.definirInformationPersonnnelle(ip1);
		assertEquals(mb1.getInfoperso(), ip1);
	}

	/**
	 * Verifie qu'on peut associer les infos persos à un membre.
	 */
	@Test
	void testGetInfoPerso2() {
		mb1.setInfoperso(ip1);
		assertEquals(mb1.getInfoperso(), ip1);
	}

	/**
	 * Vérifie qu'on ne peut pas avoir un infos persos à null. à corriger !
	 */
	@Test
	void testSetInfoPerso() {
		InformationPersonnelle info = mb1.getInfoperso();
		mb1.setInfoperso(null);
		assertEquals(mb1.getInfoperso(), info);
	}

	/**
	 * Vérifie qu'on peut donner role president à un membre.
	 */
	@Test
	void testRolePresident() {
		mb1.setInfoperso(ip1);
		mb1.setRole("president");
		assertEquals(mb1.getRole(), "president");
	}

	/**
	 * Vérifie qu'on peut donner role membre à un president.
	 */
	@Test
	void testRoleMembre() {
		mb2.setRole("membre");
		assertEquals(mb2.getRole(), "membre");
	}

	/**
	 * V�rifie qu'on ne peut pas avoir un role à null. retourne false !, doit
	 * retourner true.
	 */
	void testRoleNull() {
		String role = mb1.getRole();
		mb1.setRole(null);
		assertEquals(mb1.getRole(), role);

	}
	// @Test
	// void testRoleNull() {
	// mb1.setInfoperso(ip1);
	// mb1.setRole(null);
	// assertTrue(mb1.getRole() != null);
	// }

	/**
	 * Vérifie que le membre est associé à un evenement.
	 *
	 */
	@Test
	void testGetEvenements() {
		mb3.setInfoperso(ip1);
		mb3.setEvenement(evt1);
		assertEquals(mb3.getEvenements(), evt1);
	}

	/**
	 * Vérifie que le membre doit être participé à un evt.
	 *
	 */
	@Test
	void testSetEvenement() {
		mb3.setInfoperso(ip1);
		mb3.setEvenement(null);
		assertTrue(mb3.getEvenements() != null);
	}

	/**
	 * Vérifie que le membre a participé au moins à un evt.
	 *
	 */
	@Test
	void testEvts() {
		mb3.setInfoperso(ip1);
		mb3.setEvenement(evt1);
		mb3.setEvenement(evt2);
		List<Evenement> lst = mb3.ensembleEvenements();
		assertEquals(mb3.ensembleEvenements(), lst);
	}

	/**
	 * Vérifie que le membre a participé au moins à un evt.
	 *
	 */
	@Test
	void testEvtsAvenir() {
		mb3.setInfoperso(ip1);
		mb3.setEvenement(evt1);
		mb3.setEvenement(evt2);
		List<Evenement> lst = mb3.ensembleEvenementsAvenir();
		assertEquals(mb3.ensembleEvenementsAvenir(), lst);
	}

	/**
	 * Vérifie que les paramètres des constructeurs sont correctement gérés.
	 */
	@Test
	void testConstr() {

		evts = new ArrayList<Evenement>();
		Evenement evt = new Evenement("evt1", "Brest", "2022-25-12 20:30", 60, 20);
		;
		Membre m = new Membre("W", "A", "B", 19, "membre", evts);
		InformationPersonnelle ip = new InformationPersonnelle("W", "A", "Brest", 19);
		m.setEvenement(evt);
		m.setInfoperso(ip);
		assertEquals(m.getRole(), "membre");
		assertEquals(m.getEvenements(), evt);
	}

}

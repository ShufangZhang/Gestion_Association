package tests;

import static org.junit.Assert.assertFalse;

import static org.junit.Assert.assertNotNull;

import static org.junit.Assert.assertTrue;

import association.Evenement;

import association.GestionEvenements;

import association.InformationPersonnelle;

import association.InterGestionMembres;

import association.InterMembre;

import association.Membre;

import association.GestionMembres;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.BeforeEach;

import java.time.LocalDateTime;

import java.time.Month;

import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import java.util.HashSet;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import association.Evenement;

import association.Evenement;

/**
 * 
 * Tests JUnit de la classe {@link association.GestionEvenement
 * 
 * }.
 *
 * 
 * 
 * @author louane.meurlet Shufang.Zhang
 * 
 * @see association.GestionEvenement
 * 
 */

class TestGestionEvenements {

	private GestionEvenements GE;

	Set<InterMembre> participants = new HashSet<InterMembre>();

	Membre m1 = new Membre("Bond", "James", "MI6", 50, "membre");

	Membre m2 = new Membre("Sparrow", "Jack", "Bateau", 35, "membre");

	Evenement evt = new Evenement("Runion quipe", "Lorient", "2023-02-06 14:30", 90, 10, participants);

	/**
	 * 
	 * Instancie une information sur un gestion evenement pour les tests.
	 *
	 * 
	 * 
	 * @throws Exception ne peut pas être levée ici
	 * 
	 */

	@BeforeEach

	void setUp() throws Exception {

		GE = new GestionEvenements();

	}

	/**
	 * 
	 * Ne fait rien après les tests : à modifier au besoin.
	 *
	 * 
	 * 
	 * @throws Exception ne peut pas être levée ici
	 * 
	 */

	@AfterEach

	void tearDown() throws Exception {
	}

	@Test

	void testConstructeur_avecliste() {

		ArrayList<Evenement> evenements = new ArrayList<Evenement>();

		String dateEvt = "2023-04-12 17:30";

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

		LocalDateTime date = LocalDateTime.parse(dateEvt, formatter);

		Evenement e1 = new Evenement("Réunion", "Moëlan-sur-mer", date, 90, 15);

		evenements.add(e1);

		GE = new GestionEvenements(evenements);

		assertEquals(GE.ensembleEvenements(), evenements);

	}

	@Test

	void testConstructeur() {

		assertNotNull(GE);

	}

	/**
	 * Verifie qu'un evenement cree est correctement insere dans la listes des
	 * evnements.
	 */

	@Test

	void testcreer_event() {

		Evenement e2 = GE.creerEvenement("Badminton", "Molan-sur-mer", 2, Month.of(3), 2022, 15, 30, 120, 20);

		assertEquals(GE.ensembleEvenements().contains(e2), true);

	}

	/**
	 * Verifie qu'un evnement est correctement supprime de la liste des evnements.
	 */
	@Test

	void testsupprimer_event() {

		Evenement evt1 = GE.creerEvenement("REUNION", "SALLE 1", 12, Month.of(12), 2022, 15, 10, 90, 20);
		Evenement evt2 = GE.creerEvenement("REPAS", "SALLE 2", 16, Month.of(12), 2022, 15, 10, 90, 20);

		GE.supprimerEvenement(evt1);

		assertFalse(GE.ensembleEvenements().contains(evt1));

	}

	/**
	 * Vrifie que le renvoie de la liste des vnements n'est pas null.
	 */
	@Test

	void test_listevt() {

		ArrayList<Evenement> evts;

		evts = (ArrayList<Evenement>) GE.ensembleEvenements();

		assertFalse(evts == null);

	}

	/**
	 * Vrifie que le renvoie de la liste des vnements venir n'est pas null.
	 */

	@Test

	void test_listevtav() {

		ArrayList<Evenement> evtsav;

		evtsav = (ArrayList<Evenement>) GE.ensembleEvenementAvenir();

		assertFalse(evtsav == null);

	}

	/**
	 * Vrifie qu'un membre est bien inscrit un vnement.
	 */

	@Test

	void test_mbr_evt_bon() {

		ArrayList<Evenement> evenements_v;

		evenements_v = new ArrayList<Evenement>();

		Membre m3 = new Membre("Marc", "Patrick", "UBO", 45, "membre", evenements_v);

		evenements_v.add(evt);

		boolean verif_evt;

		verif_evt = GE.inscriptionEvenement(evt, m3);

		assertFalse(!verif_evt);

	}

	/**
	 * Vrifie qu'un membre ne peut pas tre inscrit un vnement dont la date chevauche
	 * la date d'un autre vnement auquel il est inscrit.
	 */

	@Test

	void test_mbr_evt_chvt() {

		ArrayList<Evenement> evenements_v;

		evenements_v = new ArrayList<Evenement>();

		Evenement evt1 = GE.creerEvenement("REUNION", "SALLE 1", 12, Month.of(12), 2022, 15, 10, 90, 20);
		Evenement evt2 = GE.creerEvenement("REPAS", "SALLE 2", 16, Month.of(12), 2022, 15, 10, 90, 20);

		Membre m3 = new Membre("Marc", "Patrick", "UBO", 45, "membre", evenements_v);

		m3.setEvenement(evt1);
		evt1.setParticipant(m3);
		// evt2.setParticipant(m3);
		GE.inscriptionEvenement(evt1, m3);

		// Evenement evt_1 = new Evenement("R union  quipe", "Lorient","2023-02-06
		// 14:30", 90, 10, participants);

		// Membre m3 = new Membre("Marc", "Patrick", "UBO", 45, "membre", evenements_v);

		// Evenement evt_v = new Evenement("Runion", "Brest", "2023-02-06 14:31", 90,
		// 10, participants);

		// m3.setEvenement(evt_v);

		boolean verif_evt;

		verif_evt = GE.inscriptionEvenement(evt2, m3);

		assertFalse(verif_evt);

	}

	/**
	 * Vrifie qu'un membre ne peut pas tre inscrit pour un vnement dont la liste des
	 * participants est dj pleine (par rapport au nombre de participants maximum).
	 */

	@Test

	void test_mbr_evt_listfull() {

		ArrayList<Evenement> evenements_v;

		evenements_v = new ArrayList<Evenement>();

		Membre m4 = new Membre("Damien", "Patrick", "UBO", 45, "membre");

		Evenement evt_1 = new Evenement("R union  quipe", "Lorient", "2023-02-06 14:30", 90, 1, participants);

		participants.add(m4);

		Membre m3 = new Membre("Marc", "Patrick", "UBO", 45, "membre", evenements_v);

		Evenement evt_v = new Evenement("Runion", "Brest", "2023-02-06 14:31", 90, 10, participants);

		m3.setEvenement(evt_v);

		boolean verif_evt;

		verif_evt = GE.inscriptionEvenement(evt_1, m3);

		assertFalse(verif_evt);

	}

	/**
	 * Vrifie que si un membre annule un vnement, l'vnement n'est plus dans sa liste
	 * d'vnement.
	 */

	@Test

	void test_annuler_evt() {

		ArrayList<Evenement> evenements_v;

		evenements_v = new ArrayList<Evenement>();

		Membre m4 = new Membre("Marc", "Patrick", "UBO", 45, "membre");

		Evenement evt_1 = new Evenement("Runion quipe", "Lorient", "2023-02-06 14:30", 90, 4, participants);

		participants.add(m4);

		Membre m3 = new Membre("Damien", "Patrick", "UBO", 45, "membre", evenements_v);

		Evenement evt_v = new Evenement("Runion", "Brest", "2023-02-06 14:31", 90, 10, participants);

		m3.setEvenement(evt_v);

		m3.setEvenement(evt_1);

		boolean verif_evt;

		verif_evt = GE.annulerEvenement(evt_1, m3);

		assertFalse(!verif_evt);

	}

}

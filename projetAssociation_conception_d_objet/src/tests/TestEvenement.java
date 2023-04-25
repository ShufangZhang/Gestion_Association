package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import association.Evenement;
import association.InterMembre;
import association.Membre;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests JUnit de la classe {@link association.Evenement Evenement}.
 *
 * @author Shufang.Zhang
 * @see association.Evenement
 */
class TestEvenement {

	/**
	 * Une information sur un evenement, cad le nom, le lieu, la date, la duree. le
	 * nombre de participants maximum et la "collection" des participants
	 */
	private Evenement info;

	/**
	 * Instancie une information sur un evenement pour les tests.
	 *
	 * @throws Exception ne peut pas être levée ici
	 */
	@BeforeEach
	void setUp() throws Exception {
		info = new Evenement("Aviron", "Brest", "2023-02-06 14:30", 90, 15);
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
	 * Verifie que le constructeur fonctionne bien avec la date formatee et sans la
	 * liste des. participants
	 */
	@Test
	void testConstructeur() {
		String dateEvt = "2023-02-06 14:30";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime dateldt = LocalDateTime.parse(dateEvt, formatter);

		Evenement evt = new Evenement("Aviron", "Brest", dateldt, 90, 15);
		assertEquals(evt.getNom(), "Aviron");
		assertEquals(evt.getLieu(), "Brest");
		assertTrue(evt.getDate() != null);
		assertTrue(evt.getDuree() >= 0);
		assertTrue(evt.getNbParticipantsMax() > 0);

	}

	/**
	 * Verifie que le constructeur fonctionne bien avec la date de type Sring et
	 * sans la liste des. participants
	 */
	@Test
	void testConstructeurDateStr() {
		String dateEvt = "2023-02-06 14:30";

		Evenement evt = new Evenement("Réunion équipe", "Lorient", dateEvt, 90, 10);
		assertEquals(evt.getNom(), "Réunion équipe");
		assertEquals(evt.getLieu(), "Lorient");
		assertTrue(evt.getDate() != null);
		assertTrue(evt.getDuree() >= 0);
		assertTrue(evt.getNbParticipantsMax() > 0);

	}

	/**
	 * Verifie que le constructeur fonctionne bien avec la date de type String et
	 * avec la liste des. participants
	 */
	@Test
	void testConstructeurDateStrAvecParticipants() {

		Membre m1 = new Membre("Bond", "James", "MI6", 50, "membre");
		assertEquals(m1.getInfoperso().getNom(), "Bond");

		Membre m2 = new Membre("Sparrow", "Jack", "Bateau", 35, "membre");
		assertEquals(m2.getInfoperso().getNom(), "Sparrow");

		Set<InterMembre> participants = new HashSet<InterMembre>();

		participants.add(m1);
		participants.add(m2);
		assertTrue(participants.contains(m1));
		assertTrue(participants.contains(m2));

		String dateEvt = "2023-02-06 14:30";

		Evenement evt = new Evenement("Réunion équipe", "Lorient", dateEvt, 90, 10, participants);
		assertEquals(evt.getNom(), "Réunion équipe");
		assertEquals(evt.getLieu(), "Lorient");
		assertTrue(evt.getDate() != null);
		assertTrue(evt.getDuree() >= 0);
		assertTrue(evt.getNbParticipantsMax() > 0);

	}

	// ===================================
	// Tests pour PasDeChevauchementTemps
	// ==================================

	/**
	 * Verifie que 2 evennements ne peuvent pas avoir lieu a la meme date
	 * exactement.
	 */
	@Test
	void testPasDeChevauchementTempsMemeDate() {
		String dateevtmmdate = "2023-02-06 14:30";
		Evenement evtmmdate = new Evenement("Nuit de l'info", "Brest", dateevtmmdate, 120, 100);
		assertFalse(info.pasDeChevauchementTemps(evtmmdate));
	}

	/**
	 * Verifie que 2 evennements peuvent avoir lieu a une date completement
	 * differente.
	 */
	@Test
	void testPasDeChevauchementTempsPasMemeDate() {
		String dateevtpasmmdate = "2022-02-06 14:30";
		Evenement evtpasmmdate = new Evenement("Basket", "Brest", dateevtpasmmdate, 90, 20);
		assertTrue(info.pasDeChevauchementTemps(evtpasmmdate));
	}

	/**
	 * Verifie que 2 evennements ne peuvent pas avoir lieu en meme temps quand une
	 * des date chevauche l'autre.
	 */
	@Test
	void testPasDeChevauchementTempsDateChevauche() {
		String dateevtchedate = "2023-02-06 15:00";
		Evenement evtchedate = new Evenement("Baseball", "Brest", dateevtchedate, 90, 10);
		assertFalse(info.pasDeChevauchementTemps(evtchedate));
	}

	/**
	 * Verifie que 2 evennements peuvent avoir lieu quand le 2nd evenement commence
	 * exactement quand le 1er se termine.
	 */
	@Test
	void testPasDeChevauchementTempsDateCommenceQuandAutreTermine() {
		String dateevtcommenceqfini = "2023-02-06 16:00";
		Evenement evtcommenceqfini = new Evenement("Foot", "Brest", dateevtcommenceqfini, 70, 27);
		assertTrue(info.pasDeChevauchementTemps(evtcommenceqfini));
	}

	// ==================================
	// Tests pour PasDeChevauchementLieu
	// =================================

	/**
	 * Verifie que 2 evennements ne peuvent pas avoir lieu en meme temps dans le
	 * meme lieu.
	 */
	@Test
	void testPasDeChevauchementLieuMemeTempsMemeLieu() {
		String dateevtmmdate = "2023-02-06 14:30";
		String lieummlieu = "Brest";
		Evenement evtmmlieummdate = new Evenement("Nuit de l'info", lieummlieu, dateevtmmdate, 120, 100);
		assertFalse(info.pasDeChevauchementLieu(evtmmlieummdate));
	}

	/**
	 * Verifie que 2 evenements peuvent avoir lieu dans le meme lieu avec une date
	 * differente.
	 */
	@Test
	void testPasDeChevauchementLieuPasMemeTempsMemeLieu() {
		String dateevtpasmmdate = "2022-02-06 14:30";
		String lieummlieu = "Brest";
		Evenement evtmmlieupasmmdate = new Evenement("Nuit de l'info", lieummlieu, dateevtpasmmdate, 120, 100);
		assertTrue(info.pasDeChevauchementLieu(evtmmlieupasmmdate));
	}

	/**
	 * Verifie que 2 evenements peuvent avoir lieu en meme temps dans un lieu
	 * different.
	 */
	@Test
	void testPasDeChevauchementLieuMemeTempsPasMemeLieu() {
		String dateevtmmdate = "2023-02-06 14:30";
		String lieupasmmlieu = "Lorient";
		Evenement evtpasmmlieummdate = new Evenement("Nuit de l'info", lieupasmmlieu, dateevtmmdate, 120, 100);
		assertTrue(info.pasDeChevauchementLieu(evtpasmmlieummdate));
	}

	/**
	 * Verifie que 2 evenements peuvent avoir lieu dans un lieu different et avec
	 * une date differente.
	 */
	@Test
	void testPasDeChevauchementLieuPasMemeTempsPasMemeLieu() {
		String dateevtpasmmdate = "2022-02-06 14:30";
		String lieupasmmlieu = "Lorient";
		Evenement evtpasmmlieupasmmdate = new Evenement("Nuit de l'info", lieupasmmlieu, dateevtpasmmdate, 120, 100);
		assertTrue(info.pasDeChevauchementLieu(evtpasmmlieupasmmdate));
	}

	/**
	 * Verifie que 2 evenements ne peuvent pas avoir lieu dans le meme lieu avec une
	 * date qui chevauche l'autre.
	 */
	@Test
	void testPasDeChevauchementLieuChevauchementTempsMemeLieu() {
		String dateevtchedate = "2023-02-06 15:00";
		String lieummlieu = "Brest";
		Evenement evtmmlieuchedate = new Evenement("Nuit de l'info", lieummlieu, dateevtchedate, 120, 100);
		assertFalse(info.pasDeChevauchementLieu(evtmmlieuchedate));
	}

	/**
	 * Verifie que 2 evenements peuvent avoir lieu dans un lieu different avec une
	 * date qui chevauche l'autre.
	 */
	@Test
	void testPasDeChevauchementLieuChevauchementTempsPasMemeLieu() {
		String dateevtchedate = "2023-02-06 15:00";
		String lieupasmmlieu = "Lorient";
		Evenement evtpasmmlieuchedate = new Evenement("Nuit de l'info", lieupasmmlieu, dateevtchedate, 120, 100);
		assertTrue(info.pasDeChevauchementLieu(evtpasmmlieuchedate));
	}

	/**
	 * Verifie que 2 evenements peuvent avoir lieu dans un meme lieu ou le 2nd evt
	 * commence quand le 1er termine.
	 */
	@Test
	void testPasDeChevauchementLieuTempsCommenceQuandFiniMemeLieu() {
		String dateevtcommenceqfini = "2023-02-06 16:00";
		String lieummlieu = "Brest";
		Evenement evtmmlieudatecqf = new Evenement("Nuit de l'info", lieummlieu, dateevtcommenceqfini, 120, 100);
		assertTrue(info.pasDeChevauchementLieu(evtmmlieudatecqf));
	}

	/**
	 * Verifie que 2 evenements peuvent avoir lieu dans un lieu different ou le 2nd
	 * evt commence quand le 1er termine.
	 */
	@Test
	void testPasDeChevauchementLieuTempsCommenceQuandFiniPasMemeLieu() {
		String dateevtcommenceqfini = "2023-02-06 16:00";
		String lieupasmmlieu = "Lorient";
		Evenement evtpasmmlieudatecqf = new Evenement("Nuit de l'info", lieupasmmlieu, dateevtcommenceqfini, 120, 100);
		assertTrue(info.pasDeChevauchementLieu(evtpasmmlieudatecqf));
	}

	// ====================
	// Tests pour la duree
	// ===================

	/**
	 * Vérifie que l'on peut positionner une duree de 60 min.
	 */
	@Test
	void testDuree60() {
		info.setDuree(60);
		assertEquals(info.getDuree(), 60);
	}

	/**
	 * Vérifie qu'on ne peut pas positionner une duree negative.
	 */
	@Test
	void testDureeNegatif() {
		info.setDuree(-60);
		assertTrue(info.getDuree() != -60);
	}

	// =============================================
	// Tests pour le nombre de participants maximum
	// ============================================

	/**
	 * Vérifie que l'on peut positionner un nombre de participants maximum de 100
	 * personnes.
	 */
	@Test
	void testNbParticipantsMax100() {
		info.setNbParticipantsMax(100);
		assertEquals(info.getNbParticipantsMax(), 100);
	}

	/**
	 * Vérifie qu'on ne peut pas positionner un nombre de participants maximum
	 * negatif.
	 */
	@Test
	void testNbParticipantsMaxNegatif() {
		info.setNbParticipantsMax(-100);
		;
		assertTrue(info.getNbParticipantsMax() != -100);
	}

}

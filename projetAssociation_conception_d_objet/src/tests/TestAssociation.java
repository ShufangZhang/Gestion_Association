package tests;

import association.Association;
import association.Evenement;
import association.GestionEvenements;
import association.GestionMembres;
import association.InterMembre;
import association.Membre;
import java.io.IOException;
import java.time.Month;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests JUnit de la classe {@link association.Evenement Evenement}.
 *
 * @author Shufang.Zhang
 * @see association.Evenement
 */
class TestAssociation {

	/**
	 * Une information sur un �v�nement, cad le nom, le lieu, la date, la duree.
	 * le nombre de participants maximum et la "collection" des participants
	 */
	private Association assoc;

	@BeforeEach
	void setUp() throws Exception {
		assoc = new Association();

	}

	@Test
	void testwrite() {
		GestionEvenements ge;
		ge = (GestionEvenements) assoc.gestionnaireEvenements();
		Evenement e1;
		Evenement e2;
		e2 = ge.creerEvenement("Equitation", "Moelan-sur-mer", 2, Month.of(2), 2022, 19, 30, 120, 20);
		e1 = ge.creerEvenement("Badminton", "Brest", 2, Month.of(3), 2022, 15, 30, 120, 20);

		ArrayList<InterMembre> membres;

		GestionMembres gm;
		gm = (GestionMembres) assoc.gestionnaireMembre();
		Membre m1 = new Membre("Stephane", "Meurlet", "maison", 48, "membre");
		Membre m2 = new Membre("Morgane", "Meurlet", "maison", 48, "membre");
		;
		gm.ajouterMembre(m2);
		gm.ajouterMembre(m1);

		/*
		 * try { assoc.sauvegarderDonnees("evenements_membres.txt"); } catch
		 * (IOException e) { // TODO Auto-generated catch block e.printStackTrace(); }
		 */

	}

	@Test
	void testread() {
		try {
			assoc.chargerDonnees("evenements_membres.txt");
			// System.out.println(assoc.gestionnaireEvenements().ensembleEvenements());
			// System.out.println(assoc.gestionnaireMembre().ensembleMembres());

			for (Evenement evt : assoc.gestionnaireEvenements().ensembleEvenements()) {

				System.out.println(evt.toString());
			}
			for (Membre m : assoc.gestionnaireMembre().ensembleMembres()) {
				System.out.println(m.toString());
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

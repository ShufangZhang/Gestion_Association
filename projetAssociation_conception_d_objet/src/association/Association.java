package association;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;

/**
 * Description des informations d'un �v�nnement de l'association.
 *
 * @author louane.meurlet
 *
 */

@SuppressWarnings("serial")
public class Association implements java.io.Serializable, InterGestionAssociation {

	private GestionEvenements ge;

	private GestionMembres gm;

	public Association() {
		// Constructeur
	}

	/**
	 * Renvoie le gestionnaire d'événements de l'association. L'objet retourné
	 * est unique. Au premier appel de la méthode, il est créé et aux appels
	 * suivants, c'est la référence sur cet objet qui est retournée.
	 *
	 * @return le gestionnaire d'événements de l'association
	 */
	public InterGestionEvenements gestionnaireEvenements() {

		if (this.ge == null) {
			this.ge = new GestionEvenements();
		}
		return this.ge;
	}

	/**
	 * Renvoie le gestionnaire de membres de l'association. L'objet retourné est
	 * unique. Au premier appel de la méthode, il est créé et aux appels
	 * suivants, c'est la référence sur cet objet qui est retournée.
	 *
	 * @return le gestionnaire de membres de l'association
	 */
	public InterGestionMembres gestionnaireMembre() {

		if (this.gm == null) {
			this.gm = new GestionMembres();
		}
		return this.gm;

	}

	/**
	 * Enregistre dans un fichier toutes les données de l'association,
	 * c'est-à-dire l'ensemble des membres et des événéments.
	 *
	 * @param nomFichier le fichier dans lequel enregistrer les données
	 * @throws IOException en cas de problème d'écriture dans le fichier
	 */
	public void sauvegarderDonnees(String nomFichier) throws IOException {

		FileWriter fw = new FileWriter(nomFichier, true);
		for (Evenement e : ge.ensembleEvenements()) {
			fw.write(e.toString());
			fw.write("\n");
		}
		for (InterMembre m : gm.ensembleMembres()) {
			fw.write(m.toString());
			fw.write("\n");
		}
		fw.close();
	}

	/**
	 * Charge à partir d'un fichier toutes les données de l'association,
	 * c'est-à-dire un ensemble de membres et d'événements. Si des membres et des
	 * événéments avaient déjà été définis, ils sont écrasés par le
	 * contenu trouvé dans le fichier.
	 *
	 * @param nomFichier le fichier à partir duquel charger les données
	 * @throws IOException en cas de problème de lecture dans le fichier
	 */
	public void chargerDonnees(String nomFichier) throws IOException {
		InputStream ips = new FileInputStream(nomFichier);
		InputStreamReader ipsr = new InputStreamReader(ips);
		BufferedReader br = new BufferedReader(ipsr);

		String ligne;
		while ((ligne = br.readLine()) != null) {

			String[] tab = ligne.split(";");
			System.out.println(tab[0]);
			if (tab[0] == "evenement") {
				System.out.println(tab[0]);
				// On créer un objet Evenement
				String nom = tab[1];
				String lieu = tab[2];

				String dateEvt = tab[3];
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
				LocalDateTime dateldt = LocalDateTime.parse(dateEvt, formatter);

				int annee = dateldt.getYear();
				int mois = dateldt.getMonthValue();
				int jour = dateldt.getDayOfMonth();
				int heure = dateldt.getHour();
				int minute = dateldt.getMinute();

				int duree = Integer.parseInt(tab[4]);
				int nbr = Integer.parseInt(tab[5]);

				Evenement e;
				e = this.gestionnaireEvenements().creerEvenement(nom, lieu, jour, Month.of(mois), annee, heure, minute,
						120, 20);
			}

			if (tab[0] == "membre") {
				System.out.println(tab[0]);
				// On créer un objet Membre
				String nom = tab[1];
				String prenom = tab[2];
				String adresse = tab[3];
				int age = Integer.parseInt(tab[4]);
				String role = tab[5];

				Membre m = new Membre(nom, prenom, adresse, age, role);
				;
				this.gestionnaireMembre().ajouterMembre(m);

			}

		}

	} // public void chargerDonnees(String nomFichier) throws IOException{

}

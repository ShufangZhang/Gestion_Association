package association;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Description des informations d'un membre de l'association.
 *
 * @author oualid.abrame
 *
 */
public class Membre implements java.io.Serializable, InterMembre {

	private static final long serialVersionUID = 1L;

	private InformationPersonnelle infoperso;

	private String role;

	private List<Evenement> evenements;

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<Evenement> getEvenements() {
		return evenements;
	}

	public String getInfoMembre() {
		return this.infoperso.getNom() + ";" + this.infoperso.getPrenom() + ";" + this.infoperso.getAge() + ";"
				+ this.infoperso.getAdresse();
	}

	public void setEvenement(Evenement evenement) {
		this.evenements.add(evenement);
	}

	public InformationPersonnelle getInfoperso() {
		return infoperso;
	}

	public void setInfoperso(InformationPersonnelle infoperso) {
		this.infoperso = infoperso;
	}

	/**
	 * Creer un membre sans liste d'evenements.
	 *
	 * @param nom     le nom du membre
	 * @param prenom  le prenom du membre
	 * @param adresse l'adresse du membre
	 * @param age     l'age du membre
	 * @param role    le role du membre
	 */
	public Membre(String nom, String prenom, String adresse, int age, String role) {

		this.role = role;
		this.infoperso = new InformationPersonnelle(nom, prenom, adresse, age);

	}

	/**
	 * creer un membre avec sa liste d'evenements.
	 *
	 * @param nom        le nom du membre
	 * @param prenom     le prenom du membre
	 * @param adresse    l'adresse du membre
	 * @param age        l'age du membre
	 * @param role       le role du membre
	 * @param evenements la liste des evenements du membre
	 */
	public Membre(String nom, String prenom, String adresse, int age, String role, List<Evenement> evenements) {

		this.role = role;
		this.infoperso = new InformationPersonnelle(nom, prenom, adresse, age);
		this.evenements = evenements;

	}

	/**
	 * La liste des événements auquel le membre est inscrit ou a participé.
	 *
	 * @return la liste des événements du membre
	 */
	public List<Evenement> ensembleEvenements() {

		return evenements;

	}

	/**
	 * La liste des événements auquel le membre est inscrit et qui n'ont pas encore
	 * eu lieu.
	 *
	 * @return la liste des événements à venir du memmbre
	 */
	public List<Evenement> ensembleEvenementsAvenir() {
		int i = 0;
		int taille;
		List<Evenement> a_venir = new ArrayList();
		taille = this.evenements.size();
		for (i = 0; i != taille; i++) {
			if (this.evenements.get(i).getDate().isAfter(LocalDateTime.now())) {
				// ajouter l'Ã©vÃ¨nement Ã  venir dans la liste a_venir
				a_venir.add(this.evenements.get(i));
			}
		}
		return a_venir;
	}

	public void definirInformationPersonnnelle(InformationPersonnelle info) {
		this.infoperso = info;
	}

	public InformationPersonnelle getInformationPersonnelle() {
		return this.infoperso;
	}

	@Override
	public String toString() {
		return "membre;" + infoperso.getNom() + ";" + infoperso.getPrenom() + ";" + infoperso.getAge() + ";"
				+ infoperso.getAdresse() + ";" + role;
	}

}

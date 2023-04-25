package association;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Set;

/**
 * Description des informations d'un evenement de l'association.
 *
 * @author Shufang.Zhang
 *
 */

@SuppressWarnings("serial")

public class Evenement implements java.io.Serializable {

	private String nom;

	private String lieu;

	private LocalDateTime date;

	private int duree; // en minutes

	private int nbParticipantsMax;

	private Set<InterMembre> participants; // "collection" de membres

	// ===================================================
	// Creation de la methode de creation d'un evennement
	// ==================================================

	/**
	 * creer evenement.
	 *
	 * @param nom               nom de l'événement
	 * @param lieu              lieu de l'evenement
	 * @param date              date de l'evenement
	 * @param duree             duree de l'evenement
	 * @param nbParticipantsMax nombre maxi des participants
	 * @param participants      la "collection" des participants à l'événement
	 */

	public Evenement(String nom, String lieu, LocalDateTime date, int duree, int nbParticipantsMax,
			Set<InterMembre> participants) {

		if (nom != null) {
			this.nom = nom;
		}

		if (lieu != null) {
			this.lieu = lieu;

		}

		if (date != null) {
			this.date = date;

		}

		if (duree > 0) {
			this.duree = duree;

		}

		if (nbParticipantsMax != 0) {
			this.nbParticipantsMax = nbParticipantsMax;

		}

		this.participants = participants;
	}

	/**
	 * creer evenement.
	 *
	 * @param nom               nom de l'événement
	 * @param lieu              lieu de l'evenement
	 * @param date              date de l'evenement // parse :recevoir la variable
	 *                          formatter comme un int
	 * @param duree             duree de l'evenement
	 * @param nbParticipantsMax nombre maxi des participants
	 * @param participants      la "collection" des participants à l'événement
	 */
	public Evenement(String nom, String lieu, String date, int duree, int nbParticipantsMax,
			Set<InterMembre> participants) {

		String dateEvt = date;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime dateldt = LocalDateTime.parse(dateEvt, formatter);

		this.date = dateldt;

		if (nom != null) {
			this.nom = nom;
		}

		if (lieu != null) {
			this.lieu = lieu;
		}

		if (duree >= 0) {
			this.duree = duree;
		} else {
			this.duree = 0;
		}

		if (nbParticipantsMax != 0) {
			this.nbParticipantsMax = nbParticipantsMax;
		}

		this.participants = participants;
	}

	/**
	 * creer evenement avec ses informations obligatoires.
	 *
	 * @param nom               nom de l'événement
	 * @param lieu              lieu de l'evenement
	 * @param date              date de l'evenement // parse :recevoir la variable
	 *                          formatter comme un int
	 * @param duree             duree de l'evenement
	 * @param nbParticipantsMax nombre maxi des participants
	 */
	public Evenement(String nom, String lieu, String date, int duree, int nbParticipantsMax) {

		String dateEvt = date;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime dateldt = LocalDateTime.parse(dateEvt, formatter);

		this.date = dateldt;
		if (nom != null) {
			this.nom = nom;
		}

		if (lieu != null) {
			this.lieu = lieu;
		}

		if (duree > 0) {
			this.duree = duree;
		} else {
			this.duree = 0;
		}

		if (nbParticipantsMax != 0) {
			this.nbParticipantsMax = nbParticipantsMax;
		}

	}

	/**
	 * creer evenement avec ses informations obligatoires.
	 *
	 * @param nom               nom de l'événement
	 * @param lieu              lieu de l'evenement
	 * @param date              date de l'evenement // parse :recevoir la variable
	 *                          formatter comme un int
	 * @param duree             duree de l'evenement
	 * @param nbParticipantsMax nombre maxi des participants
	 */
	public Evenement(String nom, String lieu, LocalDateTime date, int duree, int nbParticipantsMax) {

		this.date = date;
		if (nom != null) {
			this.nom = nom;
		}

		if (lieu != null) {
			this.lieu = lieu;
		}

		if (duree > 0) {
			this.duree = duree;
		} else {
			this.duree = 0;
		}

		if (nbParticipantsMax != 0) {
			this.nbParticipantsMax = nbParticipantsMax;
		}

	}

	/**
	 * retourne toute les information d'un evenement.
	 *
	 * @return toutes les information d'un evenement
	 */
	public String getInfoEvenement() {
		return this.getNom() + ";" + this.getLieu() + ";" + this.getDate().toString().replace("T", " ") + ";"
				+ this.getDuree() + ";" + this.getNbParticipantsMax();
	}

	/**
	 * renvoie vrai si 2 evenements ne se chevauchent pas dans le meme lieu en meme
	 * temps.
	 *
	 * @param evt est un evenement que l'on compare a l'evenement appelant
	 *
	 * @return true si 2 evenements ne se chevauchent pas dans le meme lieu en meme
	 *         temps, false sinon
	 */
	public boolean pasDeChevauchementLieu(Evenement evt) {

		if (this.lieu != evt.lieu) {
			return true;
		}

		return this.pasDeChevauchementTemps(evt);
	}

	/**
	 * renvoie vrai si 2 evenements ne se chevauchent pas dans temps (independamment
	 * du lieu).
	 *
	 * @param evt est un evenement que l'on compare a l'evenement appelant
	 * 
	 * @return true si 2 evenements ne se chevauchent pas dans temps (independamment
	 *         du lieu)
	 */
	public boolean pasDeChevauchementTemps(Evenement evt) {
		LocalDateTime datefin;
		LocalDateTime datefinevt;

		datefin = this.date.plusMinutes(this.duree);
		datefinevt = evt.date.plusMinutes(evt.duree);

		if ((evt.date.equals(this.date) || datefinevt.isAfter(this.date) && datefinevt.isBefore(datefin))
				|| (evt.date.isAfter(this.date) && evt.date.isBefore(datefin))
				|| (evt.date.isBefore(this.date) && datefinevt.isAfter(datefin))) {

			return false;
		}

		return true;

	}

	// ==============================================
	// generation automatique des getters et setters
	// ==============================================

	/**
	 * renvoie le nom de l'evenement.
	 *
	 * @return le nom de l'evenement
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Modififie le nom de l'evenement.
	 *
	 * @param nom le nouveau nom
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * renvoie le lieu de l'evenement.
	 *
	 * @return le lieu de l'evenement
	 */
	public String getLieu() {
		return lieu;
	}

	/**
	 * Modififie le lieu de l'evenement.
	 *
	 * @param lieu le nouveau lieu
	 */
	public void setLieu(String lieu) {
		this.lieu = lieu;
	}

	/**
	 * renvoie la date de l'evenement.
	 *
	 * @return la date de l'evenement
	 */
	public LocalDateTime getDate() {
		return date;
	}

	/**
	 * Modififie la date de l'evenement.
	 *
	 * @param date la nouvelle date
	 */
	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	/**
	 * renvoie la duree de l'evenement.
	 *
	 * @return la duree de l'evenement
	 */
	public int getDuree() {
		return duree;
	}

	/**
	 * Modifie la duree de l'evenement.
	 *
	 * @param duree la duree de l'evenement
	 */
	public void setDuree(int duree) {
		if (duree > 0) {
			this.duree = duree;
		}
	}

	/**
	 * renvoie le nombre de participants max de l'evenement.
	 *
	 * @return le nombre de participants max de l'evenement
	 */
	public int getNbParticipantsMax() {
		return nbParticipantsMax;
	}

	/**
	 * Modififie le nombre de participants maximum de l'evenement.
	 *
	 * @param nbParticipantsMax le nouveau nombre de participants maximum
	 */
	public void setNbParticipantsMax(int nbParticipantsMax) {
		if (nbParticipantsMax > 0) {
			this.nbParticipantsMax = nbParticipantsMax;
		}
	}

	/**
	 * renvoie la "collection" des participants de l'evenement.
	 *
	 * @return la "collection" des participants de l'evenement
	 */
	public Set<InterMembre> getParticipants() {
		return participants;
	}

	/**
	 * Modififie la "collection" de participants de l'evenement.
	 *
	 * @param participants la nouvelle "collection" de participants de l'evenement
	 */
	public void setParticipants(Set<InterMembre> participants) {

		this.participants = participants;

	}

	/**
	 * ajoute un participant a la collection des participants de l'evenement.
	 *
	 * @param participant la collection des participants a l'evenement
	 */
	public void setParticipant(InterMembre participant) {
		this.participants.add(participant);
	}

	/**
	 * enleve un participant a la collection des participants de l'evenement.
	 *
	 * @param participant la collection des participants a l'evenement
	 */
	public void removeParticipant(InterMembre participant) {
		this.participants.remove(participant);
	}

	/**
	 * enleve tous les participants de la collection des participants de
	 * l'evenement.
	 */
	public void removeTouslesParticipants() {
		this.participants.clear();
	}

	// ================================================================
	// generation automatique des methodes hashCode, toString et equals
	// ================================================================

	@Override
	public int hashCode() {
		return Objects.hash(date, duree, lieu, nbParticipantsMax, nom, participants);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Evenement other = (Evenement) obj;
		return Objects.equals(date, other.date) && duree == other.duree && Objects.equals(lieu, other.lieu)
				&& nbParticipantsMax == other.nbParticipantsMax && Objects.equals(nom, other.nom)
				&& Objects.equals(participants, other.participants);
	}

	@Override
	public String toString() {
		return "evenement;" + nom + ";" + lieu + ";" + date + ";" + duree + ";" + nbParticipantsMax + ";="
				+ participants;
	}

	// Modifiez manuellement le code g n r  au besoin. Rajoutez notamment
	// les methodes de gestion des participants a l'evenement.
	//
	// - Rajoutez un/des constructeurs permettant de construire plus facilement
	// un evenement sans avoir besoin de passer un parametre de type LocalDateTime
	//
	// - Ecrivez la JavaDoc complete de la classe
}

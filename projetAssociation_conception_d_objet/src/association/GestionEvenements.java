package association;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

/**
 * Description des informations pour la gestion des evenements de l'association.
 *
 * @author Shufang.zhang
 *
 */

@SuppressWarnings("serial")
public class GestionEvenements implements java.io.Serializable, InterGestionEvenements {

	private ArrayList<Evenement> evenements;

	public GestionEvenements(ArrayList<Evenement> evenements) {
		this.evenements = evenements;

	}

	public GestionEvenements() {
		if (this.evenements == null) {
			this.evenements = new ArrayList<Evenement>();
		}

	}

	/**
	 * Creer un evenement et le retourne.
	 *
	 * @param nom            le nom de l'evenement
	 * @param lieu           le lieu de l'evenement
	 * @param jour           le jour de l'evenement
	 * @param mois           le mois de l'evenement
	 * @param annee          l'annee de l'evenement
	 * @param heure          l'heure de l'evenement
	 * @param minutes        l'heure de l'evenement
	 * @param duree          la duree de l'evenement
	 * @param nbParticipants le nombre de participant à l'evenement
	 * @return event retourne l'evenement cree
	 */
	public Evenement creerEvenement(String nom, String lieu, int jour, Month mois, int annee, int heure, int minutes,
			int duree, int nbParticipants) {

		LocalDateTime evtdatetime = LocalDateTime.of(annee, mois, jour, heure, minutes);

		Evenement event = new Evenement(nom, lieu, evtdatetime, duree, nbParticipants);

		if (this.evenements == null) {
			this.evenements = new ArrayList<Evenement>();
		}
		this.evenements.add(event);

		return event;

	}

	/**
	 * Supprime un evenement.
	 *
	 * @param evt l'evenement a supprimer
	 */
	public void supprimerEvenement(Evenement evt) {

		for (Evenement ev : this.evenements) {
			if (ev.getNom().equals(evt.getNom()) && ev.getLieu().equals(evt.getLieu())
					&& ev.getDate().toString().replace("T", " ").equals(evt.getDate().toString().replace("T", " "))
					&& ev.getDuree() == evt.getDuree()) {
				// e.removeTouslesParticipants();
				this.evenements.remove(ev);
			}
		}
	}

	/**
	 * Renvoie l'ensemble des événements de l'association.
	 *
	 * @return l'ensemble des événements
	 */
	public List<Evenement> ensembleEvenements() {

		return this.evenements;
	}

	/**
	 * Renvoie l'ensemble des événements à venir de l'association.
	 *
	 * @return l'ensemble des événements à venir
	 */
	public List<Evenement> ensembleEvenementAvenir() {
		int i = 0;
		int taille;
		taille = this.evenements.size();
		List<Evenement> a_venir = new ArrayList();
		for (i = 0; i != taille; i++) {
			if (this.evenements.get(i).getDate().isAfter(LocalDateTime.now())) {
				// ajouter l'évènement à venir dans la liste a_venir
				a_venir.add(this.evenements.get(i));
			}
		}
		return a_venir;
	}

	/**
	 * Un membre est incrit à un événement.
	 *
	 * @param evt l'événement auquel s'inscrire
	 * @param mbr le membre qui s'inscrit
	 * @return <code>true</code> s'il n'y a pas eu de problème, <code>false</code>
	 *         si l'événement est en conflit de calendrier avec un événement
	 *         auquel est déjà inscrit le membre ou si le nombre de participants
	 *         maximum est déjà atteint
	 */
	public boolean inscriptionEvenement(Evenement evt, InterMembre mbr) {
		int i = 0;
		int taille;
		// int taille2;
		if (evt.getParticipants().size() >= evt.getNbParticipantsMax()) {
			return false; // Si le nombre de participants n'est pas encore atteint
		}
		List<Evenement> membreevt = mbr.ensembleEvenements();
		taille = this.evenements.size();

		for (i = 0; i != taille; i++) {
			boolean chevauchetemps;
			chevauchetemps = membreevt.get(i).pasDeChevauchementTemps(evt);

			if (!chevauchetemps) {
				return false;
			}
		}
		// Inscription du membre à l'évènement
		evt.setParticipant(mbr);
		((Membre) mbr).setEvenement(evt);
		return true;
	}

	/**
	 * Désincrit un membre d'un événement.
	 *
	 * @param evt l'événement auquel se désinscrire
	 * @param mbr le membre qui se désincrit
	 * @return si le membre était bien inscrit à l'événement, renvoie
	 *         <code>true</code> pour préciser que l'annulation est effective,
	 *         sinon <code>false</code> si le membre n'était pas inscrit à
	 *         l'événement
	 */
	public boolean annulerEvenement(Evenement evt, InterMembre mbr) {

		int i = 0;
		int taille;

		List<Evenement> membreevt = mbr.ensembleEvenements();

		for (Evenement evtmbr : membreevt) {
			if (evtmbr.equals(evt)) {
				evt.removeParticipant(mbr);
				membreevt.remove(evt);
				return true;
			}

		}

		/*
		 * taille = this.evenements.size();
		 * 
		 * for (i = 0; i != taille; i++) { if (evt == membreevt.get(i)) {
		 * evt.removeParticipant(mbr); membreevt.remove(evt); return true; } }
		 */
		return false;

	}

}

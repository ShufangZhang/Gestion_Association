package association;

import java.util.ArrayList;

/**
 * Description de la gestion l'ensemble des membres d'un evenement.
 *
 * @author wiame.aouad
 *
 */

@SuppressWarnings("serial")
public class GestionMembres implements java.io.Serializable, InterGestionMembres {

	public ArrayList<Membre> membres;

	/**
	 * 
	 */
	public GestionMembres() {
		if (this.membres == null) {
			this.membres = new ArrayList<Membre>();
		}

	}

	public GestionMembres(ArrayList<Membre> listmembres) {
		this.membres = listmembres;
	}

	/**
	 * Ajoute un membre dans l'association. Ne fait rien si le membre était déjà
	 * présent dans l'association.
	 *
	 * @param membre le membre à rajouter
	 * @return <code>true</code> si le membre a bien été ajouté,
	 *         <code>false</code> si le membre était déjà présent (dans ce cas
	 *         il n'est pas ajouté à nouveau)
	 */
	public boolean ajouterMembre(Membre membre) {
		// if (membres == null) {this.membres = new ArrayList<Membre>();}

		for (Membre mbr : this.membres) {
			if ((mbr.getInfoperso().getNom().equals(membre.getInfoperso().getNom()))
					&& (mbr.getInfoperso().getPrenom().equals(membre.getInfoperso().getPrenom()))
					&& (mbr.getInfoperso().getAge() == membre.getInfoperso().getAge())
					&& (mbr.getInfoperso().getAdresse().equals(membre.getInfoperso().getAdresse()))) {
				return false;
			}
		}
		this.membres.add(membre);
		return true;
	}

	/**
	 * Supprime un membre de l'association.
	 *
	 * @param membre le membre à supprimer
	 * @return <code>true</code> si le membre était présent et a été supprimé,
	 *         <code>false</code> si le membre n'était pas dans l'association
	 */

	public boolean supprimerMembre(Membre membre) {
		// Si le membre existe dans la liste
		// if(this.membres.contains(membre)) {

		for (Membre mbr : this.membres) {
			if ((mbr.getInfoperso().getNom().equals(membre.getInfoperso().getNom()))
					&& (mbr.getInfoperso().getPrenom().equals(membre.getInfoperso().getPrenom()))
					&& (mbr.getInfoperso().getAge() == membre.getInfoperso().getAge())
					&& (mbr.getInfoperso().getAdresse().equals(membre.getInfoperso().getAdresse()))) {
				this.membres.remove(this.membres.indexOf(mbr));
				return true;

			}
		}
		return false;
	}

	/**
	 * Désigne le président de l'association. Il doit être un des membres de
	 * l'association.
	 *
	 * @param membre le membre à désigner comme président.
	 * @return <code>false</code> si le membre n'était pas dans l'association (le
	 *         président n'est alors pas positionné), <code>true</code> si le
	 *         membre a été nommé président
	 */
	public boolean designerPresident(InterMembre membre) {

		int i = 0;
		int taille;
		taille = this.membres.size();
		// Si le membre existe dans la liste
		if (this.membres.contains(membre)) {
			for (i = 0; i != taille; i++) {
				if (membre == this.membres.get(i)) {
					// on affecte le role president a ce membre
					this.membres.get(i).setRole("president");
					return true;
				}
				// Il y a déjà un président qui n'est pas le membre désigné
				if ((membre != this.membres.get(i)) && (this.membres.get(i).getRole() == "president")) {
					return false;

				}

			}

		}
		return false;
	}

	/**
	 * on a remplacé set par array. Renvoie l'ensemble des membres de
	 * l'association.
	 *
	 * @return l'ensemble des membres de l'association.
	 * 
	 * 
	 */

	public ArrayList<Membre> ensembleMembres() {

		return this.membres;

	}

	/**
	 * Renvoie le président de l'association.
	 *
	 * @return le membre président de l'association s'il avait été désigné
	 *         sinon retourne <code>null</code>
	 */

	public Membre president() {

		int i = 0;
		int taille;
		taille = this.membres.size();
		for (i = 0; i != taille; i++) { // parcours
			if (this.membres.get(i).getRole() == "president") {
				return this.membres.get(i);
			}

		}

		return null;

	}

}

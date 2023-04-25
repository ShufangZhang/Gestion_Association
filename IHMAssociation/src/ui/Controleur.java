package ui;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import association.Association;
import association.Evenement;
import association.Membre;

public class Controleur implements Initializable {

	private Association assoc;

	@FXML
	private TextField entreAdresseMembre;

	@FXML
	private TextField entreAgeMembre;

	@FXML
	private TextField entreeDateEvt;

	@FXML
	private TextField entreeDureeEvt;

	@FXML
	private TextField entreeHeureEvt;

	@FXML
	private TextField entreeLieuEvt;

	@FXML
	private TextField entreeMaxParticipantsEvt;

	@FXML
	private TextField entreeNomEvt;

	@FXML
	private TextField entreeNomMembre;

	@FXML
	private TextField entreePrenomMembre;

	@FXML
	private Label labelListeAfficheeEvt;

	@FXML
	private Label labelListeAfficheeMembre;

	@FXML
	private ListView<String> listeEvenements;

	@FXML
	private ListView<String> listeMembres;

	@FXML
	private TextArea message;

	@FXML
	private Font x1;

	@FXML
	private Color x2;

	public Controleur() {

	}

	@FXML
	void actionBoutonAfficherMembreSelectionneMembre(ActionEvent event) {
		// entreeNomMembre.setText("Luke Skywalker");
		// Ligne type = Nom;Pr�nom;Age;Adresse
		String sel = listeMembres.getSelectionModel().getSelectedItem();

		// R�cup�ration des champs s�par�s par ";"
		String tab[] = sel.split(";");

		String nom = tab[0];
		String prenom = tab[1];
		Integer age = Integer.parseInt(tab[2]);
		String adresse = tab[3];

		entreeNomMembre.setText(nom);
		entreePrenomMembre.setText(prenom);
		entreAdresseMembre.setText(adresse);
		entreAgeMembre.setText(age.toString());

		// Membre mbr = new Membre(nom, prenom, adresse,age,"");

		// if (assoc.gestionnaireMembre().supprimerMembre(mbr) == true) {
		// listeMembres.getItems().remove(sel);
		// }

	}

	@FXML
	void actionBoutonAfficherParticipantsEvt(ActionEvent event) {

	}

	@FXML
	void actionBoutonAfficherTousMembresMembre(ActionEvent event) {
		// Afficher tous les membres
		message.clear();
		message.appendText("Affichage des membres");

		listeMembres.getItems().clear(); // On vide la liste avant de réafficher

		for (Membre mbr : assoc.gestionnaireMembre().ensembleMembres()) {
			listeMembres.getItems().add(mbr.getInfoMembre());
		}
	}

	@FXML
	void actionBoutonEvenementSelectionneEvt(ActionEvent event) {

	}

	@FXML
	void actionBoutonEvenementsFutursAssociation(ActionEvent event) {

		message.clear();
		message.appendText("Liste des évènements A venir");
		listeEvenements.getItems().clear();

		for (Evenement evt : assoc.gestionnaireEvenements().ensembleEvenementAvenir()) {

			listeEvenements.getItems().add(evt.getInfoEvenement());
		}
	}

	@FXML
	void actionBoutonEvenementsFutursMembre(ActionEvent event) {

	}

	@FXML
	void actionBoutonEvenementsMembreMembre(ActionEvent event) {
		message.clear();
		message.appendText("Liste des évènements du membre");
		listeMembres.getItems().clear();

		String nom = entreeNomMembre.getText();
		String prenom = entreePrenomMembre.getText();
		String adresse = entreAdresseMembre.getText();
		Integer age = Integer.parseInt(entreAgeMembre.getText());

		for (Membre mbr : assoc.gestionnaireMembre().ensembleMembres()) {
			// Recherche du membre avec les coordonnées renseignée
			if ((nom.equals(mbr.getInfoperso().getNom())) && (prenom.equals(mbr.getInfoperso().getPrenom()))
					&& (age == mbr.getInfoperso().getAge()) && (adresse.equals(mbr.getInfoperso().getAdresse()))) {
				// Le membre existe dans la liste
				for (Evenement evt : mbr.ensembleEvenements()) {
					listeEvenements.getItems().add(evt.toString());
				}
			}

		}

	}

	@FXML
	void actionBoutonIDesiscrireMembreEvenement(ActionEvent event) {

	}

	@FXML
	void actionBoutonInscrireMembreEvenement(ActionEvent event) {

	}

	@FXML
	void actionBoutonNouveauEvt(ActionEvent event) {

		entreeNomEvt.setText("");
		entreeLieuEvt.setText("");
		entreeDateEvt.setText("");
		entreeHeureEvt.setText("");
		entreeDureeEvt.setText("");
		entreeMaxParticipantsEvt.setText("");

	}

	@FXML
	void actionBoutonNouveauMembre(ActionEvent event) {

		entreeNomMembre.setText("");
		entreePrenomMembre.setText("");
		entreAdresseMembre.setText("");
		entreAgeMembre.setText("");

	}

	@FXML
	void actionBoutonSupprimerEvt(ActionEvent event) {

		String selevt = listeEvenements.getSelectionModel().getSelectedItem();

		// R�cup�ration des champs séparés par ;
		String tab[] = selevt.split(";");

		String nom = tab[0];
		String lieu = tab[1];
		String date_heure = tab[2];
		Integer duree = Integer.parseInt(tab[3]);
		Integer maxp = Integer.parseInt(tab[4]);

		message.appendText(tab[0] + " " + tab[1] + " " + tab[2] + " " + tab[3] + " " + tab[4]);

		Evenement evt = new Evenement(nom, lieu, date_heure, duree, maxp);

		assoc.gestionnaireEvenements().supprimerEvenement(evt);

		listeEvenements.getItems().remove(selevt);

		message.clear();
		message.appendText("Evènement supprimé");
	}

	@FXML
	void actionBoutonSupprimerMembre(ActionEvent event) {
		//
		// Ligne type = Nom;Pr�nom;Age;Adresse
		String sel = listeMembres.getSelectionModel().getSelectedItem();

		// R�cup�ration des champs s�par�s par ";"
		String tab[] = sel.split(";");

		String nom = tab[0];
		String prenom = tab[1];
		Integer age = Integer.parseInt(tab[2]);
		String adresse = tab[3];

		Membre mbr = new Membre(nom, prenom, adresse, age, "");

		if (assoc.gestionnaireMembre().supprimerMembre(mbr) == true) {
			listeMembres.getItems().remove(sel);
		}

		message.clear();
		message.appendText("Membre " + nom + " " + prenom + " supprimé");
	}

	@FXML
	void actionBoutonTousEvenementsAssociationEvt(ActionEvent event) {

		listeEvenements.getItems().clear();

		message.clear();
		message.appendText("Affichage des évènements");

		for (Evenement evt : assoc.gestionnaireEvenements().ensembleEvenements()) {

			listeEvenements.getItems().add(evt.getInfoEvenement());
		}
	}

	@FXML
	void actionBoutonValiderEvt(ActionEvent event) {

		String nom = entreeNomEvt.getText();
		String lieu = entreeLieuEvt.getText();
		String date = entreeDateEvt.getText();
		String heure = entreeHeureEvt.getText();
		Integer duree = Integer.parseInt(entreeDureeEvt.getText());
		Integer maxp = Integer.parseInt(entreeMaxParticipantsEvt.getText());

		String date_heure = date + " " + heure;

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		// DateTimeFormatter formatterh = DateTimeFormatter.ofPattern("HH:mm");

		LocalDateTime dateldt = LocalDateTime.parse(date_heure, formatter);
		// LocalDateTime date_h = LocalDateTime.parse(heure, formatterh);

		Integer year = dateldt.getYear();
		Month month = dateldt.getMonth();
		Integer jour = dateldt.getDayOfMonth();
		Integer heure_f = dateldt.getHour();
		Integer minutes = dateldt.getMinute();

		// Evenement evt = new Evenement(nom, lieu, dateldt, duree,maxp);

		assoc.gestionnaireEvenements().creerEvenement(nom, lieu, jour, month, year, heure_f, minutes, duree, maxp);

		message.clear();
		message.appendText("Evènement créé");
	}

	@FXML
	void actionBoutonValiderMembre(ActionEvent event) {

		String nom = entreeNomMembre.getText();
		String prenom = entreePrenomMembre.getText();
		String adresse = entreAdresseMembre.getText();
		Integer age = Integer.parseInt(entreAgeMembre.getText());

		Membre mbr = new Membre(nom, prenom, adresse, age, "membre");

		assoc.gestionnaireMembre().ajouterMembre(mbr);

		message.clear();
		message.appendText("Membre " + nom + " " + prenom + " créé");
	}

	@FXML
	void actionMenuApropos(ActionEvent event) {

	}

	@FXML
	void actionMenuCharger(ActionEvent event) {

	}

	@FXML
	void actionMenuNouveau(ActionEvent event) {

	}

	@FXML
	void actionMenuQuitter(ActionEvent event) {

	}

	@FXML
	void actionMenuSauvegarder(ActionEvent event) {

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("Initialisation de l'interface");
		this.assoc = new Association();
	}

}

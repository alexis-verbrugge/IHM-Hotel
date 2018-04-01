import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.awt.*;

 // java -cp "/export/documents/mariadb-client.jar:." ...
public class Allocation_Controleur implements ActionListener {
	private JTextField nom;
	private JTextField prenom;
	private JTextField reference;
	private Bouton bouton_ok1;
	private Bouton bouton_ok2;
	private Bouton bouton_ok4;
	private int numero_chambre;
	private JLabel message;
	private Allocation_Modele allocation_modele = new Allocation_Modele();
	private CardLayout gestionnaire;
	private JPanel cards_sud;
	private Reservation reservation = new Reservation();
	private JComboBox<String> choix;
	private JComboBox<String> choix_ref;
	private JLabel infos;

	// constructeur
	public Allocation_Controleur(JTextField r,JTextField n, JTextField p, Bouton b1, Bouton b2,
		Bouton b4 ,int num, JLabel m, JLabel i, CardLayout c, JPanel pan, Reservation res, JComboBox<String> chambre,
		JComboBox<String> c_ref) {
		this.reference=r;
		this.nom=n;
		this.prenom=p;
		this.bouton_ok1=b1;
		this.bouton_ok2=b2;
		this.numero_chambre=num;
		this.message=m;
		this.gestionnaire=c;
		this.cards_sud=pan;
		this.reservation=res;
		this.choix=chambre;
		this.infos=i;
		this.choix_ref=c_ref;
		this.bouton_ok4=b4;
		}

	public void actionPerformed(ActionEvent e) {
		int tab_chambre[]=new int[50];

		if (e.getSource().equals(this.nom)) {
				this.prenom.requestFocusInWindow();
		}

		// si on appui sur le ok apres avoir re[ntrer nom et prenom 
		if (e.getSource().equals(bouton_ok1) || e.getSource().equals(this.prenom)) {
			int count_nom=0;
			System.out.println("prenom : " + this.prenom.getText() + " nom : " + this.nom.getText());
			try {
				count_nom=this.allocation_modele.countNomPrenom(this.nom.getText(), this.prenom.getText());
				// cas ou la personne a une reservation
				if (count_nom==1) {
					this.reservation=this.allocation_modele.getReservation(this.nom.getText(), this.prenom.getText());
					if (this.allocation_modele.countReservation(this.reservation)==0) {
					this.numero_chambre=this.allocation_modele.propositionChambre(this.reservation);
					this.infos.setText(this.reservation.getPrenom()+" "+this.reservation.getNom()+" avec "+this.reservation.getReference()+" en reference");
					this.message.setText("Chambre proposée : " + this.numero_chambre);
					// texte vert 
					this.message.setForeground(new Color(104,177,0));
					this.reservation.setChambre(this.numero_chambre);
					this.gestionnaire.show(this.cards_sud, "boutons");
					} else {
						this.message.setText("Reservation à ce nom deja allouée");
						this.infos.setText("");
						// texte rouge
					this.message.setForeground(new Color(219,51,51));
					}
					
				}
			// cas ou la personne recherchee n'a pas de reservations
				if (count_nom==0) {
					System.out.println("Pas de reservations");
					this.message.setText("Il n'y a pas de réservations au nom de" +
						this.prenom.getText() +" "+ this.nom.getText());
					this.infos.setText("");
					// texte rouge
					this.message.setForeground(new Color(219,51,51));
					this.gestionnaire.show(this.cards_sud, "vide");
				}
			// cas ou la personne recherchee a plusieurs reservation
				if (count_nom>1) {
					String tab_reference[]= new String[count_nom];
					System.out.println("Plus d'une reservation");
					this.infos.setText("");
					this.message.setText("Il y'a plusieurs réservations au nom de " +
						this.prenom.getText() +" "+ this.nom.getText());
					// texte rouge
					this.message.setForeground(new Color(219,51,51));
					this.gestionnaire.show(this.cards_sud, "reference");
					this.choix_ref.removeAllItems();
					this.allocation_modele.getReservation(this.nom.getText(), this.prenom.getText(), tab_reference);
					for (int i=0; i<tab_reference.length; i++) {
						this.choix_ref.addItem(tab_reference[i]);
					}
				}
				this.nom.setText("");
				this.prenom.setText("");
			} catch(SQLException exc) {
				System.err.println("SQL exception : " + exc.getMessage());
			}			
		}
		//si on appui sur le bouton ok apres avoir rentrer la reference 
		if (e.getSource().equals(bouton_ok2) || e.getSource().equals(this.reference)) {
			System.out.println("reference : " + this.reference.getText());
			try {
					// cas ou la reservation a ete retrouvee 
				if (this.allocation_modele.countReference(this.reference.getText())==1) {
					this.reservation=this.allocation_modele.getReservation(this.reference.getText());
					if (this.allocation_modele.countReservation(this.reservation)==0) {
					this.numero_chambre=this.allocation_modele.propositionChambre(this.reservation);
					this.infos.setText(this.reservation.getPrenom()+" "+this.reservation.getNom()+" avec "+this.reservation.getReference()+" en reference");
					this.message.setText("Chambre proposée : " + this.numero_chambre);
					// texte vert 
					this.message.setForeground(new Color(104,177,0));
					this.reservation.setChambre(this.numero_chambre);
					this.gestionnaire.show(this.cards_sud, "boutons");
					} else {
						this.message.setText("Réservation deja allouée");
						this.infos.setText("");
						
						// texte rouge
						this.message.setForeground(new Color(219,51,51));
					}
				}
					// cas ou la reservation n'a pas ete retrouvee 
				if (this.allocation_modele.countReference(this.reference.getText())!=1) {
					this.message.setText("Pas de références trouvées avec cet identifiant");
					this.infos.setText("");
					// texte rouge
					this.message.setForeground(new Color(219,51,51));
					this.gestionnaire.show(this.cards_sud, "vide");
				}
				this.reference.setText("");
			}

			catch(SQLException exc) {
				System.err.println("SQL exception : " + exc.getMessage());
			}
		}

		if (e.getActionCommand().equals("Valider")) {
			Date date = this.reservation.getDate();
			try {
				if (this.allocation_modele.countReservation(this.reservation)==0) {
					
					for (int i=0; i<this.reservation.getDuree(); i++) {
				this.allocation_modele.validerReservation(this.reservation);
				date=(new Date(date.getTime() + 24*60*60*1000));
				this.reservation.setDate(date);
				}
				this.message.setText("Chambre " + reservation.getChambre() + " bien reservée ");
				// texte vert 
				this.message.setForeground(new Color(104,177,0));
				this.gestionnaire.show(this.cards_sud, "vide");
			} else {
				this.message.setText("Reservation deja allouée");
				// texte rouge
				this.message.setForeground(new Color(219,51,51));
				this.gestionnaire.show(this.cards_sud, "vide");
			}
			} catch(SQLException exc) {
				System.err.println("SQL exception : " + exc.getMessage());
			}
		}

		if (e.getActionCommand().equals("Changer")) {
			this.choix.removeAllItems();
			try {
			this.gestionnaire.show(this.cards_sud, "changement");
			tab_chambre=new int[50];
			this.allocation_modele.selectChambres(this.reservation, tab_chambre,50);

			for (int i=0; i<tab_chambre.length; i++) {
				if (tab_chambre[i]!=0) {
			this.choix.addItem(tab_chambre[i]+"");
			}
		}
		} catch(SQLException exc) {
				System.err.println("SQL exception : " + exc.getMessage());
			}
		}

		if (e.getActionCommand().equals("Annuler")) {
			this.choix.removeAllItems();
			this.gestionnaire.show(this.cards_sud, "boutons");
		}
		
		if (e.getActionCommand().equals("Ok")) {

			String tmp = this.choix.getSelectedItem().toString();
			int tmp2 = Integer.parseInt(tmp);
			System.out.println("valeur de la nv chambre:"+tmp);
			this.reservation.setChambre(tmp2);
			this.message.setText("Chambre proposée : " + tmp2);
			// texte vert 
			this.message.setForeground(new Color(104,177,0));
			this.choix.removeAllItems();
			this.gestionnaire.show(this.cards_sud, "boutons");
		}
		if (e.getSource().equals(bouton_ok4)) {
			try {
			String tmp = this.choix_ref.getSelectedItem().toString();
			this.reservation=this.allocation_modele.getReservation(tmp);
					if (this.allocation_modele.countReservation(this.reservation)==0) {
					this.numero_chambre=this.allocation_modele.propositionChambre(this.reservation);
					this.infos.setText(this.reservation.getPrenom()+" "+this.reservation.getNom()+" avec "+this.reservation.getReference()+" en reference");
					this.message.setText("Chambre proposée : " + this.numero_chambre);
					// texte vert 
					this.message.setForeground(new Color(104,177,0));
					this.reservation.setChambre(this.numero_chambre);
					this.gestionnaire.show(this.cards_sud, "boutons");
					} else {
						this.message.setText("Réservation deja allouée");
						this.infos.setText("");
						
						// texte rouge
						this.message.setForeground(new Color(219,51,51));
					}
					} catch(SQLException exc) {
				System.err.println("SQL exception : " + exc.getMessage());
			}
		}
		if (e.getActionCommand().equals("Quitter")) {
			String ObjBoutton[] = {"oui", "non"};
		int resultat=JOptionPane.showOptionDialog(null,
			"Voulez vous vraiment quitter ?", "Aurevoir !",
			 JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, 
			 null, ObjBoutton, ObjBoutton[1]);
		
		if (resultat==JOptionPane.YES_OPTION) {
			System.exit(0);
		}
		}
	}
}
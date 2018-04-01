import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;

 // java -cp "/export/documents/mariadb-client.jar:." ...
public class Statistique_Controleur implements ActionListener{
	private JTextField date_occupation;
	private JTextField date_nonpres;
	private JTextField date_periode_deb;
	private JTextField date_periode_fin;
	private Bouton bouton_ok1;
	private Bouton bouton_ok2;
	private Bouton bouton_ok3;
	private JLabel message;
	private JLabel infos;
	private JLabel titre;
	private int num;
	private int num1;
	private Statistique_Modele statistique_modele = new Statistique_Modele();
	private java.sql.Date deb;
	private java.sql.Date fin;
	private java.sql.Date occup;
	private java.sql.Date nonpres;
	private CardLayout gestionnaire;
	private CardLayout gestionnaire2;
	private JPanel cards;
	private JPanel cards_south;
	private Camembert camembert;
	//constructeur
	public Statistique_Controleur(JTextField doc, JTextField dn,JTextField dpd,JTextField dpf,
	 Bouton b1, Bouton b2, Bouton b3, JLabel m,JLabel m2,JLabel titre, CardLayout g, JPanel p, Camembert cam, CardLayout g2,
	 JPanel p2){
		this.date_occupation=doc;
		this.date_nonpres=dn;
		this.date_periode_deb=dpd;
		this.date_periode_fin=dpf;
		this.bouton_ok1=b1;
		this.bouton_ok2=b2;
		this.bouton_ok3=b3;
		this.message=m;	
		this.cards=p;
		this.camembert=cam;
		this.gestionnaire=g;
		this.gestionnaire2=g2;
		this.cards_south=p2;
		this.infos=m2;
		this.titre=titre;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(bouton_ok1)){
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date parsed = new Date();
			System.out.println("text: "+this.date_occupation.getText());
			try{
				try{
					parsed = format.parse(this.date_occupation.getText());
				 	occup = new java.sql.Date(parsed.getTime());
					
				this.num=this.statistique_modele.tauxOccupation(occup);
				if(this.num>0){
					camembert.setCam(100,this.num);
					this.gestionnaire.show(this.cards, "stats");
					this.gestionnaire2.show(this.cards_south, "retour");
					this.infos.setText(this.num+"% des chambres sont occupées le "+ this.date_occupation.getText());
					this.titre.setText("Taux d'occupation du "+this.date_occupation.getText());
				}
				else {
					this.message.setText("Aucune chambre n'est occupé le "+this.date_occupation.getText());
				}
				}catch(ParseException exc){
					System.err.println("ParseException : "+ exc.getMessage());
					// texte rouge
					this.message.setForeground(new Color(219,51,51));
					this.message.setText("Ceci n'est pas une date !");
				}
			} catch (SQLException exc) {
				System.err.println("SQL exception : " + exc.getMessage());
			}
		}
		if (e.getSource().equals(bouton_ok2)){
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date parsed = new Date();
			try{
				try{
					parsed = format.parse(this.date_nonpres.getText());
					nonpres = new java.sql.Date(parsed.getTime());
				
				this.num=this.statistique_modele.nombreReservation(nonpres);
				int tab_res[]= new int[this.num];
				this.statistique_modele.getIdReservation(tab_res,nonpres);
				this.num1=this.statistique_modele.nonPresence(tab_res,nonpres);			
				this.infos.setText(this.num1+" clients sur "+this.num+" ne se sont pas présentés le "+this.date_nonpres.getText());
				camembert.setCam(this.num, this.num-this.num1);
				this.titre.setText("Taux de non presentation le "+this.date_occupation.getText());
				this.gestionnaire.show(this.cards, "stats");
				this.gestionnaire2.show(this.cards_south, "retour");
				}catch(ParseException exc){
					System.err.println("ParseException : "+ exc.getMessage());
					// texte rouge
					this.message.setForeground(new Color(219,51,51));
					this.message.setText("Ceci n'est pas une date !");
				}		
			} catch (SQLException exc) {
				System.err.println("SQL exception : " + exc.getMessage());
			}
		}
		if(e.getSource().equals(bouton_ok3)){
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date parsed = new Date();
			try{
				try{
					parsed = format.parse(this.date_periode_deb.getText());
					deb = new java.sql.Date(parsed.getTime());
					parsed = format.parse(this.date_periode_fin.getText());
					fin = new java.sql.Date(parsed.getTime());
				if (deb.after(fin) || deb.equals(fin)) {
					// texte rouge
					this.message.setForeground(new Color(219,51,51));
					this.message.setText("Date de debut supérieure ou egale à la date de fin !");
				} else {
				long diff = (fin.getTime() - deb.getTime())/(1000*60*60*24);
				long debzer = deb.getTime();
				float moy = 0.0f;
				this.num=0;
				for (int i=0;i<diff;i++){
					this.num=this.num+this.statistique_modele.tauxOccupation(deb);
					debzer=deb.getTime();
					debzer=debzer+1*24*60*60*1000;
					deb=new java.sql.Date(debzer);
				}
				moy=this.num/diff;
				int moyenne =(int) moy;
				camembert.setCam(100,moyenne);
				this.infos.setText("Moyenne chambre occupé/jour de "+this.date_periode_deb.getText()+" a "+this.date_periode_fin.getText()+": "+moyenne+"%");
				this.titre.setText("Taux d'occupation du "+this.date_periode_deb.getText()+" au "+this.date_periode_fin.getText());
				this.gestionnaire.show(this.cards, "stats");
				this.gestionnaire2.show(this.cards_south, "retour");
				}
				}catch(ParseException exc){
					System.err.println("ParseException : "+ exc.getMessage());
					// texte rouge
					this.message.setForeground(new Color(219,51,51));
					this.message.setText("Il y'a une erreur au niveau des dates !");
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
		if (e.getActionCommand().equals("Retour")) {
			this.gestionnaire.show(this.cards, "menu");
			this.gestionnaire2.show(this.cards_south, "quitter");
			this.titre.setText("Bienvenue Mr Smith");
		}
	}
}
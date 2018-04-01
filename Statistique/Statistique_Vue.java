import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Statistique_Vue extends JPanel {

	public Statistique_Vue(JFrame fenetre) {
		this.setLayout(new BorderLayout());
		Bouton bouton_ok1 = new Bouton("OK");
		Bouton bouton_ok2 = new Bouton("OK");
		Bouton bouton_ok3 = new Bouton("OK");
		Bouton bouton_retour = new Bouton("Retour");
		Bouton bouton_quitter = new Bouton("Quitter");
		bouton_quitter.setBackground(new Color(219,51,51));
		JLabel texte_occupation = new JLabel("Taux d'occupation:");
		JLabel texte_non_presentation = new JLabel("Taux non-presentation");
		JLabel texte_periode = new JLabel("Taux par période");
		JLabel texte_titre = new JLabel("Bienvenue Mr Smith", JLabel.CENTER);
		JLabel texte_message = new JLabel("message", JLabel.CENTER);
		JLabel texte_infos = new JLabel("");
		JLabel texte_titre2 = new JLabel("");
		JLabel texte_date= new JLabel("Toutes les dates doivent être entrée au format YYYY-MM-DD");
    	JTextField jtf_occupation = new JTextField(15);
    	JTextField jtf_non_presentation = new JTextField(15);
    	JTextField jtf_periode_deb = new JTextField(7);
    	JTextField jtf_periode_fin = new JTextField(7);
    	Camembert camembert = new Camembert(fenetre);

		Font f = new Font("Arial", Font.BOLD, 22); 
		texte_titre.setFont(f);
		f = new Font("Arial", Font.PLAIN, 16); 
		texte_occupation.setFont(f);
		texte_non_presentation.setFont(f);
		texte_periode.setFont(f);
		texte_date.setFont(f);
		jtf_occupation.setFont(f);
		jtf_non_presentation.setFont(f);
		jtf_periode_deb.setFont(f);
		jtf_periode_fin.setFont(f);


		JPanel panneau_quitter = new JPanel();
		JPanel panneau_retour = new JPanel();

		JPanel panneau1 = new JPanel();
		JPanel panneau2 = new JPanel();
		CardLayout gestionnaire = new CardLayout();
		JPanel cards = new JPanel(gestionnaire);
		CardLayout gestionnaire2 = new CardLayout();
		JPanel cards_south = new JPanel(gestionnaire2);

		cards.add(panneau1, "menu");
        cards.add(camembert, "stats");
        gestionnaire.show(cards, "menu");

        cards_south.add(panneau_quitter, "quitter");
        cards_south.add(panneau_retour, "retour");
        gestionnaire2.show(cards_south, "quitter");


		this.add(texte_titre, BorderLayout.NORTH);
		this.add(cards, BorderLayout.CENTER);
		this.add(cards_south, BorderLayout.SOUTH);

		this.setBackground(new Color(170,187,219));
		panneau_quitter.setBackground(new Color(170,187,219));
		panneau_retour.setBackground(new Color(170,187,219));
		panneau1.setBackground(new Color(170,187,219));
		panneau2.setBackground(new Color(170,187,219));
		camembert.setBackground(new Color(170,187,219));

		panneau_retour.add(texte_infos);
		panneau_retour.add(bouton_retour);
		panneau_quitter.add(texte_message);
		panneau_quitter.add(bouton_quitter);


		panneau1.setLayout(new GridBagLayout());
		GridBagConstraints contrainte1 = new GridBagConstraints();

		Statistique_Controleur c1 = new Statistique_Controleur(jtf_occupation,jtf_non_presentation,
			jtf_periode_deb, jtf_periode_fin, bouton_ok1,bouton_ok2, bouton_ok3,texte_message, texte_infos,	
			texte_titre, gestionnaire, cards, camembert, gestionnaire2, cards_south);
 
		bouton_ok1.addActionListener(c1);
		bouton_ok2.addActionListener(c1);
		bouton_ok3.addActionListener(c1);
		bouton_quitter.addActionListener(c1);
		bouton_retour.addActionListener(c1);

		contrainte1.gridx=0;
		contrainte1.gridy=1;
		contrainte1.gridwidth=5;
		contrainte1.gridheight=1;
		contrainte1.fill=GridBagConstraints.NONE;
		contrainte1.anchor=GridBagConstraints.CENTER;
		contrainte1.insets=new Insets(20,5,5,5);
		contrainte1.weightx=1.0f;
		contrainte1.weighty=1.0f;

		panneau1.add(texte_date, contrainte1);

		contrainte1.gridx=0;
		contrainte1.gridy=1;
		contrainte1.gridwidth=2;
		contrainte1.gridheight=1;
		contrainte1.fill=GridBagConstraints.NONE;
		contrainte1.anchor=GridBagConstraints.NORTHWEST;
		contrainte1.insets=new Insets(20,5,5,5);
		contrainte1.weightx=1.0f;
		contrainte1.weighty=1.0f;

		panneau1.add(texte_occupation, contrainte1);

		contrainte1.gridx=2;
		contrainte1.gridy=1;
		contrainte1.gridwidth=4;
		contrainte1.gridheight=1;
		contrainte1.fill=GridBagConstraints.NONE;
		contrainte1.anchor=GridBagConstraints.NORTH;
		contrainte1.insets=new Insets(20,5,5,5);
		contrainte1.weightx=1.0f;
		contrainte1.weighty=1.0f;

		panneau1.add(jtf_occupation, contrainte1);

		contrainte1.gridx=6;
		contrainte1.gridy=1;
		contrainte1.gridwidth=1;
		contrainte1.gridheight=1;
		contrainte1.fill=GridBagConstraints.NONE;
		contrainte1.anchor=GridBagConstraints.NORTHEAST;
		contrainte1.insets=new Insets(20,5,5,5);
		contrainte1.weightx=1.0f;
		contrainte1.weighty=1.0f;

		panneau1.add(bouton_ok1, contrainte1);

		contrainte1.gridx=0;
		contrainte1.gridy=2;
		contrainte1.gridwidth=2;
		contrainte1.gridheight=1;
		contrainte1.fill=GridBagConstraints.NONE;
		contrainte1.anchor=GridBagConstraints.WEST;
		contrainte1.insets=new Insets(5,5,5,5);
		contrainte1.weightx=1.0f;
		contrainte1.weighty=1.0f;

		panneau1.add(texte_non_presentation, contrainte1);

		contrainte1.gridx=2;
		contrainte1.gridy=2;
		contrainte1.gridwidth=4;
		contrainte1.gridheight=1;
		contrainte1.fill=GridBagConstraints.NONE;
		contrainte1.anchor=GridBagConstraints.CENTER;
		contrainte1.insets=new Insets(5,5,5,5);
		contrainte1.weightx=1.0f;
		contrainte1.weighty=1.0f;

		panneau1.add(jtf_non_presentation, contrainte1);

		contrainte1.gridx=6;
		contrainte1.gridy=2;
		contrainte1.gridwidth=1;
		contrainte1.gridheight=1;
		contrainte1.fill=GridBagConstraints.NONE;
		contrainte1.anchor=GridBagConstraints.EAST;
		contrainte1.insets=new Insets(5,5,5,5);
		contrainte1.weightx=1.0f;
		contrainte1.weighty=1.0f;

		panneau1.add(bouton_ok2, contrainte1);

		contrainte1.gridx=0;
		contrainte1.gridy=3;
		contrainte1.gridwidth=2;
		contrainte1.gridheight=1;
		contrainte1.fill=GridBagConstraints.NONE;
		contrainte1.anchor=GridBagConstraints.SOUTHWEST;
		contrainte1.insets=new Insets(5,5,5,5);
		contrainte1.weightx=1.0f;
		contrainte1.weighty=1.0f;

		panneau1.add(texte_periode, contrainte1);

		contrainte1.gridx=2;
		contrainte1.gridy=3;
		contrainte1.gridwidth=2;
		contrainte1.gridheight=1;
		contrainte1.fill=GridBagConstraints.NONE;
		contrainte1.anchor=GridBagConstraints.SOUTH;
		contrainte1.insets=new Insets(5,5,5,5);
		contrainte1.weightx=1.0f;
		contrainte1.weighty=1.0f;

		panneau1.add(jtf_periode_deb, contrainte1);

		contrainte1.gridx=4;
		contrainte1.gridy=3;
		contrainte1.gridwidth=2;
		contrainte1.gridheight=1;
		contrainte1.fill=GridBagConstraints.NONE;
		contrainte1.anchor=GridBagConstraints.SOUTH;
		contrainte1.insets=new Insets(5,5,5,5);
		contrainte1.weightx=1.0f;
		contrainte1.weighty=1.0f;

		panneau1.add(jtf_periode_fin, contrainte1);

		contrainte1.gridx=6;
		contrainte1.gridy=3;
		contrainte1.gridwidth=1;
		contrainte1.gridheight=1;
		contrainte1.fill=GridBagConstraints.NONE;
		contrainte1.anchor=GridBagConstraints.SOUTHEAST;
		contrainte1.insets=new Insets(5,5,5,5);
		contrainte1.weightx=1.0f;
		contrainte1.weighty=1.0f;

		panneau1.add(bouton_ok3, contrainte1);

		

		
   	}


}
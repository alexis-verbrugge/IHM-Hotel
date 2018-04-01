import javax.swing.*;
import java.awt.*;

public class Allocation_Vue extends JPanel {

	private Reservation reservation= new Reservation();




	public Allocation_Vue(int taille) {
		this.setLayout(new BorderLayout());
		Bouton bouton_ok1 = new Bouton("OK");
		Bouton bouton_ok2 = new Bouton("OK");
		Bouton bouton_valider = new Bouton("Valider");
		bouton_valider.setBackground(new Color(104,177,0));
		Bouton bouton_changer = new Bouton("Changer");
		Bouton bouton_ok3 = new Bouton("Ok");
		Bouton bouton_annuler = new Bouton("Annuler");
		Bouton bouton_ok4 = new Bouton("OK");
		bouton_annuler.setBackground(new Color(219,51,51));
		Bouton bouton_quitter = new Bouton("Quitter");
		bouton_quitter.setBackground(new Color(219,51,51));
		JLabel texte_titre = new JLabel("Allocation de réservations", JLabel.CENTER);
		JLabel texte_nom = new JLabel("Nom");
		JLabel texte_prenom = new JLabel("Prénom");
		JLabel texte_num_res = new JLabel("Référence");
		JLabel texte_message = new JLabel("",JLabel.CENTER);
		JLabel texte_changement = new JLabel("Changer de chambre:");
		JLabel texte_infos = new JLabel("", JLabel.CENTER);
		JLabel texte_reference = new JLabel("choisissez une référence: ");
		JTextField jtf_nom = new JTextField(25);
		JTextField jtf_prenom = new JTextField(25);
		JTextField jtf_num_res = new JTextField(25);
		Dimension dim= new Dimension(20, 200);
		JPanel panneau1 = new JPanel();
		JPanel panneau2 = new JPanel();
		JPanel panneau_vide = new JPanel();
		JPanel panneau_changement = new JPanel();
		JPanel panneau_reference = new JPanel();
		JComboBox<String> changer_chambre = new JComboBox<>();
		JComboBox<String> changer_reference = new JComboBox<>();
		int numero_chambre=0;
		CardLayout gestionnaire = new CardLayout();
		JPanel cards_sud = new JPanel(gestionnaire);
		cards_sud.add(panneau_vide, "vide");
        cards_sud.add(panneau2, "boutons");
        cards_sud.add(panneau_changement, "changement");
        cards_sud.add(panneau_reference, "reference");
        
        this.setBackground(new Color(170,187,219));
        panneau1.setBackground(new Color(170,187,219));
        panneau_vide.setBackground(new Color(170,187,219));
        panneau2.setBackground(new Color(170,187,219));
        panneau_changement.setBackground(new Color(170,187,219));
        panneau_reference.setBackground(new Color(170,187,219));

		this.add(texte_titre, BorderLayout.NORTH);
		this.add(panneau1, BorderLayout.CENTER);
		this.add(cards_sud, BorderLayout.SOUTH);

		panneau2.add(bouton_valider);
		panneau2.add(bouton_changer);
		gestionnaire.show(cards_sud, "vide");

		Font police_titre = new Font("Arial", Font.BOLD, 22); 
		texte_titre.setFont(police_titre);
		police_titre = police_titre.deriveFont(12.0f);
		Font f = new Font("Arial", Font.PLAIN, 16); 
		texte_nom.setFont(f);
		texte_prenom.setFont(f);
		texte_num_res.setFont(f);
		texte_reference.setFont(f);
		jtf_nom.setFont(f);
		jtf_prenom.setFont(f);
		jtf_num_res.setFont(f);
		f = new Font("Arial", Font.BOLD, 14);
		texte_message.setFont(f);
		texte_infos.setFont(f);

		panneau_changement.setLayout(new FlowLayout());
		panneau_changement.add(texte_changement);
		panneau_changement.add(changer_chambre);
		panneau_changement.add(bouton_ok3);
		panneau_changement.add(bouton_annuler);

		panneau_reference.setLayout(new FlowLayout());
		panneau_reference.add(texte_reference);
		panneau_reference.add(changer_reference);
		panneau_reference.add(bouton_ok4);

		String nom=jtf_nom.getText();
		String prenom=jtf_prenom.getText();
		System.out.println("nom : " + nom + " prenom : "+ prenom);

		Allocation_Controleur c1 = new Allocation_Controleur(jtf_num_res,jtf_nom,jtf_prenom,bouton_ok1,bouton_ok2,
		 bouton_ok4, numero_chambre, texte_message, texte_infos, gestionnaire,
		  cards_sud,this.reservation, changer_chambre, changer_reference);
 
		bouton_ok1.addActionListener(c1);
		bouton_ok2.addActionListener(c1);
		bouton_valider.addActionListener(c1);
		bouton_changer.addActionListener(c1);
		bouton_ok3.addActionListener(c1);
		bouton_ok4.addActionListener(c1);
		bouton_quitter.addActionListener(c1);
		bouton_annuler.addActionListener(c1);
		changer_chambre.addActionListener(c1);
		changer_reference.addActionListener(c1);
		jtf_nom.addActionListener(c1);
		jtf_prenom.addActionListener(c1);
		jtf_num_res.addActionListener(c1);
		panneau1.setLayout(new GridBagLayout());
		GridBagConstraints contrainte1 = new GridBagConstraints();

		contrainte1.gridx=0;
		contrainte1.gridy=0;
		contrainte1.gridwidth=3;
		contrainte1.gridheight=1;
		contrainte1.fill=GridBagConstraints.NONE;
		contrainte1.anchor=GridBagConstraints.WEST;
		contrainte1.insets=new Insets(5,5,5,5);
		contrainte1.weightx=1.0f;
		contrainte1.weighty=1.0f;

		panneau1.add(texte_nom, contrainte1);

		contrainte1.gridx=0;
		contrainte1.gridy=1;
		contrainte1.gridwidth=3;
		contrainte1.gridheight=1;
		contrainte1.fill=GridBagConstraints.HORIZONTAL;
		contrainte1.anchor=GridBagConstraints.WEST;
		contrainte1.insets=new Insets(5,5,5,5);
		contrainte1.weightx=1.0f;
		contrainte1.weighty=1.0f;

		panneau1.add(jtf_nom, contrainte1);

		contrainte1.gridx=0;
		contrainte1.gridy=3;
		contrainte1.gridwidth=3;
		contrainte1.gridheight=1;
		contrainte1.fill=GridBagConstraints.NONE;
		contrainte1.anchor=GridBagConstraints.WEST;
		contrainte1.insets=new Insets(5,5,5,5);
		contrainte1.weightx=1.0f;
		contrainte1.weighty=1.0f;


		panneau1.add(texte_prenom, contrainte1);

		contrainte1.gridx=0;
		contrainte1.gridy=4;
		contrainte1.gridwidth=3;
		contrainte1.gridheight=1;
		contrainte1.fill=GridBagConstraints.HORIZONTAL;
		contrainte1.anchor=GridBagConstraints.WEST;
		contrainte1.insets=new Insets(5,5,5,5);
		contrainte1.weightx=1.0f;
		contrainte1.weighty=1.0f;

		panneau1.add(jtf_prenom, contrainte1);

		contrainte1.gridx=2;
		contrainte1.gridy=5;
		contrainte1.gridwidth=3;
		contrainte1.gridheight=1;
		contrainte1.fill=GridBagConstraints.NONE;
		contrainte1.anchor=GridBagConstraints.EAST;
		contrainte1.insets=new Insets(5,5,5,5);
		contrainte1.weightx=1.0f;
		contrainte1.weighty=1.0f;

		panneau1.add(bouton_ok1, contrainte1);


		contrainte1.gridx=4;
		contrainte1.gridy=0;
		contrainte1.gridwidth=3;
		contrainte1.gridheight=1;
		contrainte1.fill=GridBagConstraints.NONE;
		contrainte1.anchor=GridBagConstraints.WEST;
		contrainte1.insets=new Insets(5,5,5,5);
		contrainte1.weightx=1.0f;
		contrainte1.weighty=1.0f;

		panneau1.add(texte_num_res, contrainte1);

		contrainte1.gridx=4;
		contrainte1.gridy=1;
		contrainte1.gridwidth=3;
		contrainte1.gridheight=1;
		contrainte1.fill=GridBagConstraints.HORIZONTAL;
		contrainte1.anchor=GridBagConstraints.EAST;
		contrainte1.insets=new Insets(5,5,5,5);
		contrainte1.weightx=1.0f;
		contrainte1.weighty=1.0f;

		panneau1.add(jtf_num_res, contrainte1);

		contrainte1.gridx=5;
		contrainte1.gridy=3;
		contrainte1.gridwidth=2;
		contrainte1.gridheight=1;
		contrainte1.fill=GridBagConstraints.NONE;
		contrainte1.anchor=GridBagConstraints.EAST;
		contrainte1.insets=new Insets(5,5,5,5);
		contrainte1.weightx=1.0f;
		contrainte1.weighty=1.0f;

		

		panneau1.add(bouton_ok2, contrainte1);

		contrainte1.gridx=5;
		contrainte1.gridy=4;
		contrainte1.gridwidth=1;
		contrainte1.gridheight=1;
		contrainte1.fill=GridBagConstraints.NONE;
		contrainte1.anchor=GridBagConstraints.EAST;
		contrainte1.insets=new Insets(5,5,5,5);
		contrainte1.weightx=1.0f;
		contrainte1.weighty=1.0f;
		

		panneau1.add(bouton_quitter, contrainte1);

		contrainte1.gridx=0;
		contrainte1.gridy=6;
		contrainte1.gridwidth=7;
		contrainte1.gridheight=1;
		contrainte1.fill=GridBagConstraints.NONE;
		contrainte1.anchor=GridBagConstraints.CENTER;
		contrainte1.insets=new Insets(5,5,5,5);
		contrainte1.weightx=1.0f;
		contrainte1.weighty=1.0f;

		panneau1.add(texte_infos, contrainte1);

		contrainte1.gridx=0;
		contrainte1.gridy=7;
		contrainte1.gridwidth=7;
		contrainte1.gridheight=1;
		contrainte1.fill=GridBagConstraints.NONE;
		contrainte1.anchor=GridBagConstraints.CENTER;
		contrainte1.insets=new Insets(5,5,5,5);
		contrainte1.weightx=1.0f;
		contrainte1.weighty=1.0f;

		panneau1.add(texte_message, contrainte1);

	}

}
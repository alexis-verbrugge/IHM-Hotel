import javax.swing.*;
import java.awt.*;

public class Fenetre_Vue extends JFrame {
	public Fenetre_Vue() {
		this.setSize(700,500);
		this.setLayout(new BorderLayout());
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new Fermeture_Controleur());	
		Statistique_Vue statistique = new Statistique_Vue(this);
		this.add(statistique);

	}
}
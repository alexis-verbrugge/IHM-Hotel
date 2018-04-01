import javax.swing.*;
import java.awt.*;

public class Fenetre_Vue extends JFrame {
	public Fenetre_Vue() {
		this.setSize(750,500);
		this.setLayout(new BorderLayout());
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new Fermeture_Controleur());	
		int taille=this.getWidth()/3;
		Allocation_Vue allocation = new Allocation_Vue(taille);
		this.add(allocation);
		

	}
}
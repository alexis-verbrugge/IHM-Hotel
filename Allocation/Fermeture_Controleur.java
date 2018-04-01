import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class Fermeture_Controleur extends WindowAdapter {
	public void windowClosing(WindowEvent e) {
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
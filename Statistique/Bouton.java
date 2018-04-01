import javax.swing.*;
import java.awt.*;

public class Bouton extends JButton {
	
	public Bouton(String nom) {
		super(nom);
		this.setBackground(new Color(59, 89, 182));
        this.setForeground(Color.WHITE);
        this.setFocusPainted(false);
        this.setFont(new Font("Tahoma", Font.BOLD, 12));
	}
}
import javax.swing.*;
import java.awt.*;

public class Camembert extends JComponent {
	private int fheight;
	private int fwidth;
	private int vert;
	private double calcul;
	private int rouge;
	private int nb;

	public Camembert(JFrame f) {
		this.fheight=f.getWidth();
		this.fwidth=f.getHeight();
	}

	public void setCam(int total, int res) {
		double tmp1,tmp2;
		tmp1=(double)res;
		tmp2=(double)total;

		this.vert=res;
		this.rouge=total-res;
		this.calcul = (double)(tmp1/tmp2)*360;
		System.out.println(this.vert+"/"+total+"="+this.calcul);
		this.nb =(int) calcul;
		this.repaint();

		//System.out.println("oui : "+pourc+"% ("+this.oui+")\nnon : "+pourcnon+"% ("+this.non+")");
	}

	@Override
	public void paintComponent(Graphics pinceau) {
		pinceau.setColor(new Color(170,187,219));
		pinceau.fillRect(0,0,this.getWidth(), this.getHeight());
    	pinceau.setColor(Color.RED);
    	pinceau.fillArc(this.fwidth/2, this.fheight/4-50, 200, 200, 180, 360);

    	pinceau.setColor(Color.GREEN);
    	//fillArc(int x, int y, int width, int height, int startAngle, int arcAngle)
    	pinceau.fillArc(this.fwidth/2, this.fheight/4-50, 200, 200, 0, this.nb);
  	}
}
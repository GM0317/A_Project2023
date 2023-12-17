import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Ending extends JPanel{
	protected Image Ending;
	protected Ruins ruins;
	private boolean showEnding;
	protected static int y;
	
	public Ending(Ruins ruins) {
		this.ruins = ruins;
		Ending = new ImageIcon("Ending/Happy Eending.png").getImage();
	}
	public void draw(Graphics g) {
		g.drawImage(Ending, 0, y, null);
		if(y>-300) {
			System.out.println("엔딩"+y);
			y=y-1;
			System.out.println("엔딩"+y);
		}
		
	}
	public void showEnding() {	 
		 showEnding = true;
	}
}

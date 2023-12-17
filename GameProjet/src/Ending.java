import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Ending extends JPanel implements ActionListener{
	protected Image Ending;
	protected Ruins ruins;
	private boolean showEnding;
	private int x = 0;  // 이미지의 x 좌표
    private int y = 0;  // 이미지의 y 좌표
    private Timer timer;
    private int deltaY = 1; // 이동할 y 좌표의 변화량
	public Ending(Ruins ruins) {
		this.ruins = ruins;
		Ending = new ImageIcon("Ending/Happy Eending.png").getImage();
		
	}
	public void moveImag() {
		 if (showEnding) {
		        y++;
		        repaint();
		  }
	}
	public void draw(Graphics g) {
		g.drawImage(Ending, 0, 0, null);
		moveImag();
	}
	public void showEnding() {	 
		 showEnding = true;
		 moveImag();
	 }
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}


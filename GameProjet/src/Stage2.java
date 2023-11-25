import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Stage2 {
	private Image map;
	private Image floor;
	public Stage2() {
		map = new ImageIcon("stage/1.png").getImage();
		floor = new ImageIcon("stage/stage Floor.png").getImage();
	}
	public void draw(Graphics g) {
		g.drawImage(map, 0, 0, 2000, 600, null);
		g.drawImage(floor, 0, 0, 3500, 570, null);
	}

}

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Stage2 {
	private Image map;
	private Image floor;
	private Image ledder;
	private Image Tile;
	private Image Tile2;
	private Image Tile3;
	private Image Tile4;
	public Stage2() {
		map = new ImageIcon("stage/1.png").getImage();
		floor = new ImageIcon("stage/stage Floor.png").getImage();
		ledder = new ImageIcon("stage/Ladder1.png").getImage();
		Tile = new ImageIcon("stage/Tile_09.png").getImage();
		Tile2 = new ImageIcon("stage/Tile_10.png").getImage();
		Tile3 = new ImageIcon("stage/Tile_11.png").getImage();
		Tile4 = new ImageIcon("stage/Tile_12.png").getImage();
	}
	public void draw(Graphics g) {
		g.drawImage(map, 0, 0, 2000, 600, null);
		g.drawImage(floor, 0, 0, 3500, 570, null);
		g.drawImage(Tile, 600, 400, 35, 40, null);
		g.drawImage(Tile2, 635, 400, 35, 40, null);
		g.drawImage(Tile3, 670, 400, 35, 40, null);
		g.drawImage(Tile4, 705, 400, 35, 40, null);
		g.drawImage(ledder, 635, 430, 35, 40, null);
		g.drawImage(ledder, 635, 460, 35, 40, null);
		g.drawImage(ledder, 635, 490, 35, 40, null);
		g.drawImage(ledder, 635, 400, 35, 40, null);
		
		
	}

}

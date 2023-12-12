import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Tile2 extends Onbject {
	protected Image Tile;
	protected Image Tile2;
	protected Image Tile3;
	protected Image Tile4;
	public Tile2(Stage stage) {
		super(stage);
		// TODO Auto-generated constructor stub
		Tile = new ImageIcon("stage/Tile_01.png").getImage();
		Tile2 = new ImageIcon("stage/Tile_02.png").getImage();
		Tile3 = new ImageIcon("stage/Tile_02.png").getImage();
		Tile4 = new ImageIcon("stage/Tile_03.png").getImage();
	}
	public void draw(Graphics g) {
		g.drawImage(Tile, 800 + stage.getBGX(), 300, 35, 40, null);
	    g.drawImage(Tile2, 835 + stage.getBGX(), 300, 35, 40, null);
	    g.drawImage(Tile3, 870 + stage.getBGX(), 300, 35, 40, null);
	    g.drawImage(Tile4, 905 + stage.getBGX(), 300, 35, 40, null);
	    
	}

}

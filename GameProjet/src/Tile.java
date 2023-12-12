import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Tile extends Onbject{
	private Player player;
	protected Image Tile;
	protected Image Tile2;
	protected Image Tile3;
	protected Image Tile4;
	public Tile(Stage stage) {
		super(stage);
		Tile = new ImageIcon("stage/Tile_09.png").getImage();
		Tile2 = new ImageIcon("stage/Tile_10.png").getImage();
		Tile3 = new ImageIcon("stage/Tile_11.png").getImage();
		Tile4 = new ImageIcon("stage/Tile_12.png").getImage();
	}
	public void draw(Graphics g) {
		g.drawImage(Tile, 800 + stage.getBGX(), 300, 35, 40, null);
	    g.drawImage(Tile2, 835 + stage.getBGX(), 300, 35, 40, null);
	    g.drawImage(Tile3, 870 + stage.getBGX(), 300, 35, 40, null);
	    g.drawImage(Tile4, 905 + stage.getBGX(), 300, 35, 40, null);
		// 사다리와 Tile 이미지를 고정된 좌표에 그리기
	    g.drawImage(Tile, 600 + stage.getBGX(), 400, 35, 40, null);
	    g.drawImage(Tile2, 635 + stage.getBGX(), 400, 35, 40, null);
	    g.drawImage(Tile3, 670 + stage.getBGX(), 400, 35, 40, null);
	    g.drawImage(Tile4, 705 + stage.getBGX(), 400, 35, 40, null);
	    
	    g.drawImage(Tile, 800 + stage.getBGX(), 300, 35, 40, null);
	    g.drawImage(Tile2, 835 + stage.getBGX(), 300, 35, 40, null);
	    g.drawImage(Tile3, 870 + stage.getBGX(), 300, 35, 40, null);
	    g.drawImage(Tile4, 905 + stage.getBGX(), 300, 35, 40, null);
	    
	    g.drawImage(Tile, 1000 + stage.getBGX(), 200, 35, 40, null);
	    g.drawImage(Tile2, 1035 + stage.getBGX(), 200, 35, 40, null);
	    g.drawImage(Tile2, 1070 + stage.getBGX(), 200, 35, 40, null);
	    g.drawImage(Tile2, 1105 + stage.getBGX(), 200, 35, 40, null);
	    g.drawImage(Tile2, 1140 + stage.getBGX(), 200, 35, 40, null);
	    g.drawImage(Tile3, 1175 + stage.getBGX(), 200, 35, 40, null);
	    g.drawImage(Tile3, 1205 + stage.getBGX(), 200, 35, 40, null);
	    g.drawImage(Tile3, 1240 + stage.getBGX(), 200, 35, 40, null);
	    g.drawImage(Tile3, 1275 + stage.getBGX(), 200, 35, 40, null);
	    g.drawImage(Tile3, 1305 + stage.getBGX(), 200, 35, 40, null);
	    g.drawImage(Tile3, 1340 + stage.getBGX(), 200, 35, 40, null);
	    g.drawImage(Tile4, 1375 + stage.getBGX(), 200, 35, 40, null);
	    
	    g.drawImage(Tile, 1500 + stage.getBGX(), 300, 35, 40, null);
	    g.drawImage(Tile2, 1535 + stage.getBGX(), 300, 35, 40, null);
	    g.drawImage(Tile3, 1570 + stage.getBGX(), 300, 35, 40, null);
	    g.drawImage(Tile4, 1605 + stage.getBGX(), 300, 35, 40, null);
	    
	    g.drawImage(Tile, 1700 + stage.getBGX(), 400, 35, 40, null);
	    g.drawImage(Tile2, 1735 + stage.getBGX(), 400, 35, 40, null);
	    g.drawImage(Tile3, 1770 + stage.getBGX(), 400, 35, 40, null);
	    g.drawImage(Tile4, 1805 + stage.getBGX(), 400, 35, 40, null);
		
	}
	

}

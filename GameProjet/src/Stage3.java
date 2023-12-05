import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class Stage3 extends Stage {
	private Image map;
	private Image floor;
	private Image monsterImage;
	protected monster3 m3;
   
	public Stage3() {
      map = new ImageIcon("stage/stage3.png").getImage();
      floor = new ImageIcon("stage/stage Floor.png").getImage();
      monsterImage = new ImageIcon("rsc/3_monster_1.png").getImage();
    
	}
	
   
   public void draw(Graphics g) {
      g.drawImage(map, 0, 0, 3500, 600, null);
      g.drawImage(floor, 0, 0, 3500, 570, null);
      g.drawImage(monsterImage, 10, 10, 50, 50, null);
   }
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
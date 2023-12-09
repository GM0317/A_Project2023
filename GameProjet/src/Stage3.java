import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

import javax.swing.ImageIcon;

public class Stage3 extends Stage {
<<<<<<< HEAD
   private Image map;
   private Image floor;
   private int bgX = 0;
   private LinkedList<Onbject> objectList = new LinkedList<>();
   public Stage3() {
=======
	private Image map;
	private Image floor;
	private Image monsterImage;
	protected monster3 m3;
   
	public Stage3() {
>>>>>>> branch 'main' of https://github.com/GM0317/A_Project2023.git
      map = new ImageIcon("stage/stage3.png").getImage();
      floor = new ImageIcon("stage/stage Floor.png").getImage();
      monsterImage = new ImageIcon("rsc/3_monster_1.png").getImage();
    
	}
	
   
   public void draw(Graphics g) {
<<<<<<< HEAD
	   super.draw(g);
      g.drawImage(map, bgX, 0, 3500, 600, null);
      g.drawImage(floor, bgX, 0, 3500, 570, null);
=======
      g.drawImage(map, 0, 0, 3500, 600, null);
      g.drawImage(floor, 0, 0, 3500, 570, null);
      g.drawImage(monsterImage, 10, 10, 50, 50, null);
>>>>>>> branch 'main' of https://github.com/GM0317/A_Project2023.git
   }
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		switch(e.getKeyCode())
		{
		case KeyEvent.VK_LEFT:
			bgX += 10;
			break;
		case KeyEvent.VK_RIGHT:
			bgX -= 10;
			break;
		}
	}
	@Override
	public void drawBackground(Graphics g) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void drawTile(Graphics g) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void drawMonster(Graphics g) {
		// TODO Auto-generated method stub
		
	}
}
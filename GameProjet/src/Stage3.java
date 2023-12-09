import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

import javax.swing.ImageIcon;

public class Stage3 extends Stage {
   private Image map;
   private Image floor;
   private int bgX = 0;
   private LinkedList<Onbject> objectList = new LinkedList<>();
   private LinkedList<Monster> monsterList = new LinkedList<>();
   public Stage3() {
      map = new ImageIcon("stage/stage3.png").getImage();
      floor = new ImageIcon("stage/stage Floor.png").getImage();
      monsterImage = new ImageIcon("rsc/3_monster_1.png").getImage();
    
	}
   //public Stage3(LinkedList<Monster> monsterList) {
	   /*this.monster = monsterList.get(0);
		loadImage();
	   this.x = 0;
		this.y = y;
		//this.stage2 = stage2;
		this.initialY = y; // 초기 Y 좌표 저장
	}*/
   
   public void draw(Graphics g) {
	   super.draw(g);
      g.drawImage(map, bgX, 0, 3500, 600, null);
      g.drawImage(floor, bgX, 0, 3500, 570, null);
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
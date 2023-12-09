import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

import javax.swing.ImageIcon;

public class Stage3 extends Stage {
   private Image map;
   private Image floor;
   //private Image monsterImage;
   private int bgX = 0;
   private LinkedList<Onbject> objectList = new LinkedList<>();
   private LinkedList<Monster> monsterList = new LinkedList<>();
   public Stage3() {
      map = new ImageIcon("stage/stage3.png").getImage();
      floor = new ImageIcon("stage/stage Floor.png").getImage();
      monsterList.add(new monster3(300, 490, bgX));
      //monsterImage = new ImageIcon("rsc/3_monster_1.png").getImage();
   }
   
   public void draw(Graphics g) {
	  super.draw(g);
      g.drawImage(map, bgX, 0, 3500, 600, null);
      g.drawImage(floor, bgX, 0, 3500, 570, null);
      //g.drawImage(monsterImage, 400, 490, 100, 100, null);
      drawMonster(g);
   }
	@Override
	public void keyPressed(KeyEvent e) {
	    switch (e.getKeyCode()) {
	        case KeyEvent.VK_LEFT:
	            bgX += 10; // 배경을 왼쪽으로 스크롤
	            for (Monster monster : monsterList) {
	                monster.moveMonster(1); // 몬스터를 오른쪽으로 이동
	            }
	            break;
	        case KeyEvent.VK_RIGHT:
	            bgX -= 10; // 배경을 오른쪽으로 스크롤
	            for (Monster monster : monsterList) {
	                monster.moveMonster(-1); // 몬스터를 왼쪽으로 이동
	            }
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
		for (Monster monster : monsterList) {
            monster.draw(g);  // 몬스터 리스트에 있는 몬스터들을 그림
        }
	}
}
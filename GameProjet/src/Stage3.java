import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

import javax.swing.ImageIcon;

public class Stage3 extends Stage {
   private Image map;
   private Image floor;
   private PlayerHp hp;
   private Monster Mhp;
   private int bgX = 0;
   private LinkedList<Onbject> objectList = new LinkedList<>();
   private LinkedList<Monster> monsterList = new LinkedList<>();
   
   public Stage3(Player player) {
      map = new ImageIcon("stage/stage3.png").getImage();
      floor = new ImageIcon("stage/stage Floor.png").getImage();
      monsterList.add(new Monster3(player, 300, 490, bgX));
      monsterList.add(new Monster3(player, 500, 490, bgX));
      this.hp = player.getPlayerHp();
   }
   
   public void draw(Graphics g) {
	  super.draw(g);
      g.drawImage(map, bgX, 0, 3500, 600, null);
      g.drawImage(floor, bgX, 0, 3500, 570, null);
      drawMonster(g);
      for(Monster monster : monsterList) {
    	  if(monster.Checkmonster()) {
    		  hp.draw(g);
    		  monster.DieMT(50);
    	  }
    		  
      }
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
		LinkedList<Monster> removeM = new LinkedList<>();
		for (Monster monster : monsterList) {
            monster.draw(g);  // 몬스터 리스트에 있는 몬스터들을 그림
        if(monster.getHP()==0) {
  		  	removeM.add(monster);
      	  }
		}
		monsterList.removeAll(removeM);
	}
}
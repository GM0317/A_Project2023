import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.ImageIcon;

public class Stage1 extends Stage {
	private Graphics screenGraphic;
	private Image background;
	private Image floor;
	private Tile2 tile2;
	private Ledder ledder;
	private int bgX = 0;
	private Monster Mhp;
	private PlayerHp hp;
	 private LinkedList<Monster> monsterList = new LinkedList<>();
	private LinkedList<Onbject> objectList = new LinkedList<>();
	public Stage1(Player player){
		this.tile2 = new Tile2(this);
		//this.ledder = new Ledder(this);
		objectList.add(tile2);
		 objectList.add(ledder);
		background = new ImageIcon("rsc/스테이지1art.png").getImage();		
		floor = new ImageIcon("stage/stage1 바닥.png").getImage();
		 monsterList.add(new Monster1(player, 50, 505, bgX));
	     // this.hp = player.getPlayerHp();

	}
	public void draw(Graphics g) {
		super.draw(g);
	     g.drawImage(background, bgX, 0, 3000, 600, null);
	     g.drawImage(floor, bgX, 0, 3500, 560, null);
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
	public void drawTile(Graphics g) {
		// TODO Auto-generated method stub	
        //tile2.draw(g); // Tile 이미지 그리기
        //ledder.draw(g);
	}	
	public void paint(Graphics g) {
		 //super.paint(g);
	     
	     //monster1.draw(g); // Monster1 그리기
	}
	@Override
	public void drawBackground(Graphics g) {
		//super.draw(g);
	    // g.drawImage(background, bgX, 0, 3000, 600, null);
	    // g.drawImage(floor, bgX, 0, 3500, 560, null);
	}

	@Override
	public void drawMonster(Graphics g) {
		// TODO Auto-generated method stub
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
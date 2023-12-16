import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class Stage4 extends Stage{
	private Player player;
	protected Image map;
	protected Image floor;
	public Stage4(Player player) {
		this.player = player;
		
		map = new ImageIcon("stage/Background.png").getImage();
		floor = new ImageIcon("stage/stage4 Floor.png").getImage();
	}
	public void draw (Graphics g) {
		super.draw(g);
		g.drawImage(map,bgX,-230,2100,800,null);
		g.drawImage(floor, bgX, 0, 3500, 570, null); // 바닥	
		check();
	}
	@Override
	public void drawBackground(Graphics g) {
		// TODO Auto-generated method stub
		// 배경이 내가 설정한 범위를 넘어가지 않도록 고정
	    if (bgX > 0) {
	        bgX = 0;
	    } else if (bgX < -1100) {  // 3500 (배경의 전체 너비) - 350 (화면의 너비)
	        bgX = -1100;
	    }
	}

	@Override
	public void drawTile(Graphics g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawMonster(Graphics g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		switch (e.getKeyCode()) {
        case KeyEvent.VK_LEFT:
            bgX += 10; // 배경을 왼쪽으로 스크롤  
            break;
        case KeyEvent.VK_RIGHT:
            bgX -= 10; // 배경을 오른쪽으로 스크롤
            break;
        case KeyEvent.VK_UP:
            break;
		}
	}
	public void check() {
		 if (player != null) {
			Rectangle[] tileLine = {		    
			        new Rectangle(0 + bgX, 524, 3500, 40)//땅바닥
			    };
			Rectangle playerBox = player.getRect();
			boolean onGround = false;
           for (Rectangle tileBoundary : tileLine) {
               if (playerBox.intersects(tileBoundary)) {
                   int playerBottom = playerBox.y + playerBox.height;
                  // int tileTop = tileBoundary.y;
                   //int overlap = playerBottom - tileTop;
                   //player.setY(player.getY() - overlap);
                   onGround = true; // 바닥에 닿음을 표시
                   break; // 첫 번째 충돌 발견 시 반복문을 빠져나감
               }
           }
           if (!onGround) {
               player.setY(player.getY() + 1);
               System.out.println("플레이어가 바닥에 있는중");	
           }
       } else {
           System.out.println("플레이어 객체가 null입니다.");	
		 }
	}

}

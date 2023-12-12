import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

import javax.swing.ImageIcon;

public class Stage3 extends Stage {
   private Player player;
   private Image map;
   private Image floor;
   private int bgX = 0;
   private LinkedList<Onbject> objectList = new LinkedList<>(); 
	public Stage3(Player player) {
		this.player = player;
      map = new ImageIcon("stage/stage3.png").getImage();
      floor = new ImageIcon("stage/stage Floor.png").getImage();
      monsterImage = new ImageIcon("rsc/3_monster_1.png").getImage();
      
      Tile = new ImageIcon("stage/Tiles1.png").getImage();
      Tile2 = new ImageIcon("stage/Tiles2.png").getImage();
      Tile3 = new ImageIcon("stage/Tiles3.png").getImage();
      //Tile4 = new ImageIcon("stage/Tile_12.png").getImage();
    
	}
	public void setPlayer(Player p) {
		player = p;
	}
   public void draw(Graphics g) {
	   super.draw(g);
      g.drawImage(map, bgX, 0, 3500, 600, null);
      g.drawImage(floor, bgX, 0, 3500, 570, null);
      g.drawImage(monsterImage, 10, 10, 50, 50, null);
      
   // 사각형 경계선 그리기
	    g.setColor(Color.RED);
	    int rectX = 0 + bgX; //경계선 X좌표, bgX는 배경이 이동할 때 경계선 위치를 고정
	    int rectY = 365; //경계선 Y좌표
	    int rectWidth = 238; // 경계선 너비
	    int rectHeight = 40; // 경계선 높이
	    
	    // 사각형 경계선 그리기
	    g.setColor(Color.RED);
	    int rectX2 = 580 + bgX;
	    int rectY2 = 200; 
	    int rectWidth2 = 140;
	    int rectHeight2 = 40;
	    
	    // 사각형 경계선 그리기
	    g.setColor(Color.RED);
	    int rectX3 = 930 + bgX;
	    int rectY3 = 200;
	    int rectWidth3 = 340;
	    int rectHeight3 = 40;
	    
	    // 사각형 경계선 그리기
	    g.setColor(Color.RED);
	    int rectX4 = 1500 + bgX; //경계선 X좌표, bgX는 배경이 이동할 때 경계선 위치를 고정
	    int rectY4 = 300; //경계선 Y좌표
	    int rectWidth4 = 140; // 경계선 너비
	    int rectHeight4 = 40; // 경계선 높이
	    
	    g.setColor(Color.RED);
	    int rectX5 = 1700 + bgX; //경계선 X좌표, bgX는 배경이 이동할 때 경계선 위치를 고정
	    int rectY5 = 400; //경계선 Y좌표
	    int rectWidth5 = 140; // 경계선 너비
	    int rectHeight5 = 40; // 경계선 높이
	    
	    g.setColor(Color.RED);
	    int rectX6 = 80 + bgX; //경계선 X좌표, bgX는 배경이 이동할 때 경계선 위치를 고정
	    int rectY6 = 200; //경계선 Y좌표
	    int rectWidth6 = 330; // 경계선 너비
	    int rectHeight6 = 40; // 경계선 높이
	    
	    g.setColor(Color.RED);
	    int rectX7 = 0 + bgX; //경계선 X좌표, bgX는 배경이 이동할 때 경계선 위치를 고정
	    int rectY7 = 524; //경계선 Y좌표
	    int rectWidth7 = 3500; // 경계선 너비
	    int rectHeight7 = 40; // 경계선 높이
	    
	    g.drawRect(rectX, rectY, rectWidth, rectHeight); //타일 경계선
	    g.drawRect(rectX2, rectY2, rectWidth2, rectHeight2);
	    g.drawRect(rectX3, rectY3, rectWidth3, rectHeight3);
	    g.drawRect(rectX4, rectY4, rectWidth4, rectHeight4);
	    g.drawRect(rectX5, rectY5, rectWidth5, rectHeight5);
	    g.drawRect(rectX6, rectY6, rectWidth6, rectHeight6);
	    g.drawRect(rectX7, rectY7, rectWidth7, rectHeight7);
	    
	    g.drawImage(Tile, 386 + bgX, 129, 530, 180, null);
	    g.drawImage(Tile, 1305 + bgX, 227, 530, 180, null);
	    g.drawImage(Tile, 1505 + bgX, 327, 530, 180, null);
	    g.drawImage(Tile2, - 150 + bgX, 290, 530, 185, null);
	    g.drawImage(Tile3, - 25 + bgX, 129, 530, 180, null);
	    g.drawImage(Tile3, 835 + bgX, 129, 530, 180, null);

	    
	    check();
   }
   public void check() {
	   if (player != null) {
		   Rectangle playerBox = player.getRect();
			Rectangle[] tileLine = { // 직사각형 타일 경계선 배열
					new Rectangle(0 + bgX, 365, 238, 40),
			        new Rectangle(580 + bgX, 200, 140, 40),
			        new Rectangle(0 + bgX, 200, 410, 40),
			        new Rectangle(1500 + bgX, 300, 140, 40),
			        new Rectangle(1700 + bgX, 400, 140, 40),
			        new Rectangle(0 + bgX, 524, 3500, 40)
			    };
			boolean onGround = false;

            for (Rectangle tileBoundary : tileLine) {
                if (playerBox.intersects(tileBoundary)) {
                    int playerBottom = playerBox.y + playerBox.height;
                    int tileTop = tileBoundary.y;
                    int overlap = playerBottom - tileTop;

                    // 플레이어를 경계선 위로 이동시킴
                    player.setY(player.getY() - overlap);
                    onGround = true; // 바닥에 닿음을 표시
                    break; // 첫 번째 충돌 발견 시 반복문을 빠져나감
                }
            }

            // 바닥에 닿지 않았을 경우, 플레이어를 내려감 (중력 적용)
            if (!onGround) {
                player.setY(player.getY() + 1); // 플레이어를 아래로 내림 (중력)
            }
        } else {
            System.out.println("플레이어 객체가 null입니다.");
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
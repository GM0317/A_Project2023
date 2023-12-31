import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Stage2 extends Stage {
	protected Image sign;
	private Player player;
	private PlayerHp hp;
	private GameCanvas canvas;
	private Monster Mhp;
	private int Px = 400; //포탈 x좌표
	private int Py = 400; //포탈 y좌표
	private LinkedList<Onbject> objectList = new LinkedList<>();
	private LinkedList<Monster> monsterList = new LinkedList<>();
	public Stage2(Player player, GameCanvas canvas) {
		this.canvas = canvas;
		this.player = player; //초기화
		if(player == null) {
			System.out.println("plaer는 null...");
		}
		sign = new ImageIcon("stage/표시판.png").getImage();
		map = new ImageIcon("stage/1.png").getImage();
		floor = new ImageIcon("stage/stage Floor.png").getImage();
		Tile = new ImageIcon("stage/Tile_09.png").getImage();
		Tile2 = new ImageIcon("stage/Tile_10.png").getImage();
		Tile3 = new ImageIcon("stage/Tile_11.png").getImage();
		Tile4 = new ImageIcon("stage/Tile_12.png").getImage();
		Portal = new ImageIcon("stage/유적 입구.png").getImage();
		
		monsterList.add(new Monster2(player, 300, 400, 43, 122, bgX, 1, 100)); //x,y,가로,세로,속도,이동거리
		monsterList.add(new Monster2(player, 800, 375, 55, 150, bgX, 2, 300));
		monsterList.add(new Monster2(player, 1300, 80, 43, 122, bgX, 2, 150));
		monsterList.add(new Monster2(player, 2300, 400, 43, 122, bgX, 2, 400));
		this.hp = player.getPlayerHp();

	}
	public void draw(Graphics g) {
		super.draw(g);
		drawBackground(g);
		drawTile(g);
		drawMonster(g);
	     for (Monster monster : monsterList) {
	    	 monster.moveMonster();
	     }
		
	}
	public void setPlaer(Player p) {
		player = p;
	}
	public void setBGX(int bgX) {
	      this.bgX=bgX;
	}
	@Override
	public void keyPressed(KeyEvent e) {
	    switch (e.getKeyCode()) {
	        case KeyEvent.VK_LEFT:
	            bgX += 10; // 배경을 왼쪽으로 스크롤
	            for (Monster monster : monsterList) {
	                monster.moveMonster(1); // 몬스터를 오른쪽으로 이동
	                //monster.moveMonster();
	            }
	            break;
	        case KeyEvent.VK_RIGHT:
	            bgX -= 10; // 배경을 오른쪽으로 스크롤
	            for (Monster monster : monsterList) {
	                monster.moveMonster(-1); // 몬스터를 왼쪽으로 이동
	            }
	            break;
	        case KeyEvent.VK_UP:
	            //PortalChek();
	            PortalChek2();
	            break;
			}    
	    
	    }
	@Override
	public void drawBackground(Graphics g) {
		g.drawImage(map, bgX, 0, 3000, 600, null); // 백그라운드 배경
		g.drawImage(floor, bgX, 0, 3500, 570, null); // 바닥		
		g.drawImage(sign, 2800+bgX, 453 ,100, 100, null); //포털
		if (bgX > 0) {// 배경이 내가 설정한 범위를 넘어가지 않도록 고정
	        bgX = 0;
	    } else if (bgX < -2010) {  // 3500 (배경의 전체 너비) - 350 (화면의 너비)
	        bgX = -2010;
	    }	
		
	}
	@Override
	public void drawTile(Graphics g) {
		// 사다리와 Tile 이미지를 고정된 좌표에 그리기
	    g.drawImage(Tile, 600 + bgX, 400, 35, 40, null);
	    g.drawImage(Tile2, 635 + bgX, 400, 35, 40, null);
	    g.drawImage(Tile3, 670 + bgX, 400, 35, 40, null);
	    g.drawImage(Tile4, 705 + bgX, 400, 35, 40, null);
	    
	    g.drawImage(Tile, 870 + bgX, 300, 35, 40, null);
	    g.drawImage(Tile2, 905 + bgX, 300, 35, 40, null);
	    g.drawImage(Tile3, 940 + bgX, 300, 35, 40, null);
	    g.drawImage(Tile4, 975 + bgX, 300, 35, 40, null);
	    
	    g.drawImage(Tile, 1205 + bgX, 200, 35, 40, null);
	    g.drawImage(Tile2, 1240 + bgX, 200, 35, 40, null);
	    g.drawImage(Tile2, 1275 + bgX, 200, 35, 40, null);
	    g.drawImage(Tile2, 1305 + bgX, 200, 35, 40, null);
	    g.drawImage(Tile2, 1340 + bgX, 200, 35, 40, null);
	    g.drawImage(Tile3, 1375 + bgX, 200, 35, 40, null);
	    g.drawImage(Tile3, 1405 + bgX, 200, 35, 40, null);
	    g.drawImage(Tile3, 1440 + bgX, 200, 35, 40, null);
	    g.drawImage(Tile3, 1475 + bgX, 200, 35, 40, null);
	    g.drawImage(Tile3, 1505 + bgX, 200, 35, 40, null);
	    g.drawImage(Tile3, 1540 + bgX, 200, 35, 40, null);
	    g.drawImage(Tile4, 1575 + bgX, 200, 35, 40, null);
	    
	    g.drawImage(Tile, 1805 + bgX, 300, 35, 40, null);
	    g.drawImage(Tile2, 1840 + bgX, 300, 35, 40, null);
	    g.drawImage(Tile3, 1875 + bgX, 300, 35, 40, null);
	    g.drawImage(Tile4, 1905 + bgX, 300, 35, 40, null);
	    
	    g.drawImage(Tile, 2075 + bgX, 400, 35, 40, null);
	    g.drawImage(Tile2, 2105 + bgX, 400, 35, 40, null);
	    g.drawImage(Tile3, 2140 + bgX, 400, 35, 40, null);
	    g.drawImage(Tile4, 2175 + bgX, 400, 35, 40, null);
	    
	    g.setColor(Color.RED);
	}
	public void drawPotal(Graphics g) {
		
	}
	@Override
	public void drawMonster(Graphics g) {
		LinkedList<Monster> removeM = new LinkedList<>();
		
		for (Monster monster : monsterList) {
            monster.draw(g, canvas);  // 몬스터 리스트에 있는 몬스터들을 그림
            if(monster.Checkmonster()) {
 		         hp.draw(g);
 		    }
            if(monster.Checkattack()) {
 		         monster.DieMT(50);
 		    }
            if(monster.getHP()==0) {
  		  	removeM.add(monster);
  		  	
      	  }
            
		}
		monsterList.removeAll(removeM);
		check();
		
	}	
	public void check() {
		 if (player != null) {
			Rectangle[] tileLine = { // 직사각형 타일 경계선 배열
			        new Rectangle(600 + bgX, 400, 140, 1),
			        new Rectangle(870 + bgX, 300, 140, 1),
			        new Rectangle(1205 + bgX, 200, 410, 1),
			        new Rectangle(1805 + bgX, 300, 130, 1),
			        new Rectangle(2075 + bgX, 400, 130, 1),
			        new Rectangle(0 + bgX, 524, 3500, 40)//땅바닥
			    };
			Rectangle playerBox = player.getRect();
			boolean onGround = false;

            for (Rectangle tileBoundary : tileLine) {
                if (playerBox.intersects(tileBoundary)) {  
                	 int newX = player.getX();
                     int newY = player.getY();
                     // X 좌표 조정
                     if (player.getX() < tileBoundary.getMinX()) {
                         newX = (int) tileBoundary.getMinX();
                     } else if (player.getX() + player.getWidth() > tileBoundary.getMaxX()) {
                         newX = (int) (tileBoundary.getMaxX() - player.getWidth());
                     }

                     // Y 좌표 조정
                     if (player.getY() < tileBoundary.getMinY()) {
                         newY = (int) tileBoundary.getMinY();
                     } else if (player.getY() + player.getHeight() > tileBoundary.getMaxY()) {
                         newY = (int) (tileBoundary.getMaxY() - player.getHeight());
                     }
                     // 조정된 위치로 설정
                     player.setX(newX);
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
	public void PortalChek2() {
		if (player != null) {
			Rectangle[] tileLine = { // 직사각형 타일 경계선 배열
					new Rectangle(2800 + bgX, 460, 50, 50)
			    };
			Rectangle playerBox = player.getRect();
            for (Rectangle tileBoundary : tileLine) {
                if (playerBox.intersects(tileBoundary)) {  
                	canvas.changeStage(3); //유적으로 이동
                	player.setX(50);
                	player.setY(445);
                	System.out.println("Portal!");	
                }
            }	
		 }
	}
}//
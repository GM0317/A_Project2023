import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

import javax.swing.ImageIcon;

public class Stage3 extends Stage {
   private Player player;
   private GameCanvas canvas;
   private Image map;
   private Image floor;
   protected Image sign;
   private int bgX = 0;
   private PlayerHp hp;
   private Monster Mhp;
   private int Px = 400; //포탈 x좌표
   private int Py = 400; //포탈 y좌표
   private LinkedList<Monster> monsterList = new LinkedList<>();
   private LinkedList<Onbject> objectList = new LinkedList<>(); 
	public Stage3(Player player, GameCanvas canvas) {
		this.canvas = canvas;
		this.player = player;
		map = new ImageIcon("stage/stage3.png").getImage();
		floor = new ImageIcon("stage/stage Floor.png").getImage();
		sign = new ImageIcon("stage/표시판.png").getImage();
	      
		Tile = new ImageIcon("stage/Tiles1.png").getImage();
		Tile2 = new ImageIcon("stage/Tiles2.png").getImage();
	 	Tile3 = new ImageIcon("stage/Tiles3.png").getImage();
	 	//Tile4 = new ImageIcon("stage/Tile_12.png").getImage();
	 	Portal = new ImageIcon("stage/유적 입구.png").getImage();
	 	
	 	monsterList.add(new Monster3(player, 200, 440, 100, 100, bgX, 2, 500));//x,y,가로,세로,속도,이동거리
	 	monsterList.add(new Monster3(player, 500, 460, 80, 80, bgX, 4, 400));
	 	monsterList.add(new Monster3(player, 200, 150, 50, 50, bgX, 1, 200));
	 	monsterList.add(new Monster3(player, 1000, 480, 50, 50, bgX, 2, 300));
	 	monsterList.add(new Monster3(player, 1500, 450, 80, 80, bgX, 2, 200));
	 	monsterList.add(new Monster3(player, 2500, 480, 50, 50, bgX, 2, 500));
	 	monsterList.add(new Monster3(player, 2000, 390, 150, 150, bgX, 1, 500));
	 	monsterList.add(new Monster3(player, 1000, 150, 50, 50, bgX, 2, 200));
	 	
	 	
	 	this.hp = player.getPlayerHp();
	}
	public void setPlayer(Player p) {
		player = p;
	}
	public void setBGX(int bgX) {
	      this.bgX=bgX;
	}
	public void draw(Graphics g) {
	   super.draw(g);
	// 배경이 내가 설정한 범위를 넘어가지 않도록 고정
	    if (bgX > 0) {
	        bgX = 0;
	    } else if (bgX < -2513) {  // 3500 (배경의 전체 너비) - 350 (화면의 너비)
	        bgX = -2513;
	    }
	   g.drawImage(map, bgX, 0, 3500, 600, null);
	   g.drawImage(floor, bgX, 0, 3500, 570, null);  
	   g.drawImage(sign, 3200+bgX, 453 ,100, 100, null);
	   drawMonster(g);
	   for (Monster monster : monsterList) {
	    	 monster.moveMonster();
	   }
	   
  
	    
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
			        new Rectangle(930 + bgX, 200, 340, 40),
			        new Rectangle(1500 + bgX, 300, 140, 40),
			        new Rectangle(1700 + bgX, 400, 140, 40),
			        new Rectangle(0 + bgX, 524, 3500, 40),
			        new Rectangle(80 + bgX, 200, 330, 40)
			    };
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
                    //int playerBottom = playerBox.y + playerBox.height;
                    //int tileTop = tileBoundary.y;
                    //int overlap = playerBottom - tileTop;

                    // 플레이어를 경계선 위로 이동시킴
                    //player.setY(player.getY() - overlap);
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
	           case KeyEvent.VK_UP:
	               PortalChek();
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
	}
	public void PortalChek() {
		if (player != null) {
			Rectangle[] tileLine = { // 직사각형 타일 경계선 배열
					new Rectangle(3220 + bgX, 460, 50, 50)
			        // 필요에 따라 다른 타일의 직사각형 추가 가능
			    };
			Rectangle playerBox = player.getRect();
            for (Rectangle tileBoundary : tileLine) {
                if (playerBox.intersects(tileBoundary)) {  
                	canvas.changeStage(0); 
                	player.setX(50);
                	player.setY(445);
                	System.out.println("Portal!");	
                }
            }	
		 }
	}
}//
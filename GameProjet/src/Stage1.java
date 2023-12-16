import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;

public class Stage1 extends Stage {
	private Graphics screenGraphic;
	private GameCanvas canvas;
	protected Image background;
	protected Image floor;
	protected Image b;
	protected Image vine;
	protected Image sign;
	private Tile2 tile2;
	private int bgX = 0;
	private Monster Mhp;
	private PlayerHp hp;
	private Player player;
	private int Px = 1000; //포탈 x좌표
	private int Py = 400; //포탈 y좌표
	
	private LinkedList<Monster> monsterList = new LinkedList<>();
	private LinkedList<Onbject> objectList = new LinkedList<>();
	public Stage1(Player player, GameCanvas canvas){
		this.canvas = canvas;
		this.player = player;
		background = new ImageIcon("rsc/스테이지1art.png").getImage();		
		floor = new ImageIcon("stage/stage1 바닥.png").getImage();
		sign = new ImageIcon("stage/표시판.png").getImage();
		//b = new ImageIcon("stage/stage1 바닥.png").getImage();
		Tile = new ImageIcon("stage/Tiles_01.png").getImage();
		Tile2 = new ImageIcon("stage/Tiles_02.png").getImage();
	 	Tile3 = new ImageIcon("stage/Tiles_03.png").getImage();
	 	
	 	vine = new ImageIcon("stage/덩쿨.png").getImage();
		monsterList.add(new Monster1(player, 300, 420, 90, 110, bgX, 2, 300));//x,y,가로,세로,속도,이동거리
		monsterList.add(new Monster1(player, 700, 240, 90, 110, bgX, 1, 140));
		monsterList.add(new Monster1(player, 1000, 420, 90, 110, bgX, 2, 200));
		monsterList.add(new Monster1(player, 1500, 240, 90, 110, bgX, 1, 230));
		monsterList.add(new Monster1(player, 1700, 420, 90, 110, bgX, 2, 400));
		this.hp = player.getPlayerHp();
	}
	public boolean backCheck() {
		
		
		Rectangle back = new Rectangle(3500, 0, 100, 2000);
		Rectangle playerBox = player.getRect();
		if(back.intersects(playerBox)) {
			return false;
		}
		return true;
	}
	public void draw(Graphics g) {
		super.draw(g);
		 //여기 아래가 왼쪽은 배경이 안나가게 잘 설정되어있는데 오른쪽이 안되쒀ㅇ
		//draw 여기만 있음 배경은 고정이 되는데 플레이어만 빠져나가더라
		
	    // 배경이 내가 설정한 범위를 넘어가지 않도록 고정
	    if (bgX > 0) {
	        bgX = 0;
	    } else if (bgX < -3150) {  // 3500 (배경의 전체 너비) - 350 (화면의 너비)
	        bgX = -3150;
	    }
	     g.drawImage(background, bgX, 0, 3500, 600, null);
	     g.drawImage(floor, bgX, 0, 3500, 560, null);
	     g.drawImage(b, 3000, 0, 100, 2000, null);
	     g.drawImage(sign, 3200+bgX, 453 ,100, 100, null);
	     
	     
	     g.drawImage(Tile, 1500 + bgX, 320,null);
		 g.drawImage(Tile2, 1000 + bgX, 320,null);
		 g.drawImage(Tile3, 600 + bgX, 320,null);
		 g.drawImage(Tile2, 2000 + bgX, 320,null);
		 g.drawImage(Tile3, 2300 + bgX, 320,null);
		 g.drawImage(Tile, 800 + bgX, 100,null);
		 g.drawImage(Tile, 2150 + bgX, 100,null);
		 g.drawImage(vine, 1150 + bgX, 130, 50, 100, null);
		 g.drawImage(vine, 1150 + bgX, 220, 50, 100, null);
		 g.drawImage(vine, 2190 + bgX, 130, 50, 100, null);
		 g.drawImage(vine, 2190 + bgX, 220, 50, 100, null);
		 g.drawImage(vine, 770 + bgX, 340, 50, 100, null);
		 g.drawImage(vine, 770 + bgX, 410, 50, 100, null);
		 g.drawImage(vine, 2500 + bgX, 340, 50, 100, null);
		 g.drawImage(vine, 2500 + bgX, 410, 50, 100, null);
		 
		 g.setColor(Color.RED);
		    int rectX7 = 3220 + bgX; 
		    int rectY7 = 460; 
		    int rectWidth7 = 50; 
		    int rectHeight7 = 50; 
		    g.drawRect(rectX7, rectY7, rectWidth7, rectHeight7);
		 
	     drawMonster(g);
	     for (Monster monster : monsterList) {
	    	 monster.moveMonster();
	     }
	     //PortalChek();
		 check();	   
		 vineCheck();
	}
	public void setPlaer(Player p) {
		player = p;
	}
	public void setBGX(int bgX) {
	      this.bgX=bgX;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		switch(e.getKeyCode())
		{
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
	         /*System.out.println("bgx "+bgX);
	         System.out.println("stage1 키보드 입력 / player x:"+player.getX()+"player y:"+player.getY());
	          int moveAmount = 7; // 한 번에 움직이는 양 설정
	          int repeatCount = 20; // 반복 횟수 설정
	          int sleepDuration = 5; // 1밀리초마다 쉬도록 설정
	          for (int i = 0; i < repeatCount; i++) {
	             if (player.isFlip()) {
	                bgX += moveAmount;
	             } else {
	                bgX -= moveAmount;
	             }
	             setBGX(bgX);
	             try {
	                Thread.sleep(sleepDuration); 
	             } catch (InterruptedException ex) {
	                 ex.printStackTrace();
	             }
	          }
	          break;*/
		}
	}
	public void check() {
		 if (player != null) {
			Rectangle[] tileLine = {
			        new Rectangle(0 + bgX, 524, 3500, 40),
			        new Rectangle(760 + bgX, 340, 125, 1),
			        new Rectangle(1136 + bgX, 340, 180, 1),
			        new Rectangle(830 + bgX, 120, 365, 1),
			        new Rectangle(2190 + bgX, 120, 365, 1),
			        new Rectangle(2130 + bgX, 340, 180, 1),
			        new Rectangle(2460 + bgX, 340, 125, 1),
			        new Rectangle(1535 + bgX, 340, 365, 1)
			    };
			Rectangle playerBox = player.getRect();
			boolean onGround = false;

          for (Rectangle tileBoundary : tileLine) {
              if (playerBox.intersects(tileBoundary)) {
                  //int playerBottom = playerBox.y + playerBox.height;
                  //int tileTop = tileBoundary.y;
                 // int overlap = playerBottom - tileTop;             
                //아래가 추가한 부분
                  // 플레이어가 지정된 범위를 벗어난 경우, 위치를 조정합니다.
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
                  // 플레이어를 경계선 위로 이동시킴
                //  player.setY(player.getY() - overlap);
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
	/*
	public void check() {
		 if (player != null) {
			Rectangle[] tileLine = {
			        new Rectangle(0 + bgX, 524, 3500, 40),
			        new Rectangle(760 + bgX, 340, 125, 1),
			        new Rectangle(1136 + bgX, 340, 180, 1),
			        new Rectangle(830 + bgX, 120, 365, 1),
			        new Rectangle(2190 + bgX, 120, 365, 1),
			        new Rectangle(2130 + bgX, 340, 180, 1),
			        new Rectangle(2460 + bgX, 340, 125, 1),
			        new Rectangle(1535 + bgX, 340, 365, 1)
			    };
			Rectangle playerBox = player.getRect();
			boolean onGround = false;
			 //아래가 추가한 부분
            int newX = player.getX();
            int newY = player.getY();
            
            //여기 아래가 플레이어가 지정한 범위를 나가지 못하게 하는건데
            // 왼쪽은 배경이 안나가는데 오른쪽은 배경흰색이 보여(이부분은 위에 draw 부분)
            //공중에 떠잇는 바닥부분에 점프해서 갔을때 방향키하면 여기도 고정되서 안나가져
            
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
            
            player.setX(newX); 
                  
            // 플레이어를 경계선 위로 이동시킴
            player.setY(player.getY() - overlap);
            onGround = true; // 바닥에 닿음을 표시
            break; // 첫 번째 충돌 발견 시 반복문을 빠져나감    
            }
	


           // 바닥에 닿지 않았을 경우, 플레이어를 내려감 (중력 적용)
           if (!onGround) {
               player.setY(player.getY() + 1); // 플레이어를 아래로 내림 (중력)
           } else {
           System.out.println("플레이어 객체가 null입니다.");	

		 }
		 */
	}
	public void vineCheck() {
	    if (player != null) {
	        Rectangle[] tileLine = {
	            new Rectangle(1170 + bgX, 130, 10, 180),
	            new Rectangle(790 + bgX, 360, 10, 150),
	            new Rectangle(2515 + bgX, 360, 10, 150),
	            new Rectangle(2210 + bgX, 130, 10, 180)
	        };   
	        Rectangle playerBox = player.getRect();
	        boolean onVine = false;
	        for (Rectangle tileBoundary : tileLine) {
	            if (playerBox.intersects(tileBoundary)) {
	            	System.out.println("덩쿨!");
	                onVine = true;
	                break;
	            }
	        }
	        if (onVine) {
	            int newY = player.getY() - 2; // 예시로 10만큼 y좌표를 감소시킵니다.
	            player.setY(newY);
	        }
	    }
	}

	
	@Override
	public void drawTile(Graphics g) {
	}	
	public void paint(Graphics g) {
		 //super.paint(g);
	     
	     //monster1.draw(g); // Monster1 그리기
	}
	@Override
	public void drawBackground(Graphics g) {
		//super.draw(g);
	     //g.drawImage(background, bgX, 0, 3000, 600, null);
	     //g.drawImage(floor, bgX, 0, 3500, 560, null);
	}

	@Override
	public void drawMonster(Graphics g) {
		// TODO Auto-generated method stub
		LinkedList<Monster> removeM = new LinkedList<>();
	      for (Monster monster : monsterList) {
	            monster.draw(g, canvas);  // 몬스터 리스트에 있는 몬스터들을 그
	  		    if(monster.Checkmonster()) {
	  		         hp.draw(g);
	  		    }
	  		    if(monster.Checkattack()) {
	  		         monster.DieMT(50);
	  		    }
		        if(monster.getHP()==0) {
		             removeM.add(monster);
		        }
		        if(player.getPlayerHp().getHp()==0) {
		            new GameStart(); // 새로운 GameStart 창 열기
		        	player.getPlayerHp().returnHP(300);
		        	//dispose();
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
                	canvas.changeStage(2);
                	player.setX(50);
                	player.setY(445);
                	System.out.println("Portal!");	
                }
            }	
		 }
	}	
}
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import java.util.ArrayList;
import java.util.List;

public class Player extends JPanel implements Runnable, KeyListener, ComponentListener {
	//영은이 글추가함
	//그래픽스 함수를 사용하기 위한 클래스
		private Graphics bufferGraphics = null;
		//bufferGraphics로 그림을 그릴 때 실제로 그려지는 가상 버퍼
		private Image offscreen;
		private Image image; // 캐릭터 이미지
		private Image door; // 문 이미지 생성
		private Image background; // 배경 이미지
		private List<Attack> attacks = new ArrayList<>(); // 공격 클래스 목록
		private PlayerHp playerHp; // PlayerHp 클래스 객체 선언
		private Stage2Monster stage2monster;
		private boolean isJump = false; // 캐릭터가 점프 중인지 여부
	    private int jumpHeight = 0; // 점프 높이
	    private static final int PANEL_WIDTH = 1000; // 패널 너비
	    private static final int PANEL_HEIGHT = 600; // 패널 높이
		
		int x = 50, y = 490, sel = 1; // 캐릭터의 초기 위치와 선택 상태
		int bgX = 0; //배경 좌우키 이벤트 추가
		
		public Player(){
			addKeyListener(this);
		    setFocusable(true);
		    setFocusTraversalKeysEnabled(false);
		    addComponentListener(this);
		    playerHp = new PlayerHp(this); // PlayerHp 클래스 객체 초기화, this를 이용하여 Player 객체를 전달
		    stage2monster = new Stage2Monster(); // Stage2Monster 객체 초기화
		    background = Toolkit.getDefaultToolkit().getImage("rsc/스테이지2 art.png"); //맵 이미지
		}
		
		@Override
		public void keyPressed(java.awt.event.KeyEvent e) {
			// TODO Auto-generated method stub
			int key = e.getKeyCode();
			if(key == e.VK_RIGHT || key == e.VK_NUMPAD6 || key == e.VK_KP_RIGHT) {
				sel = (sel == 1)?2:2;
				x = (x < getWidth())?x + 10 : -image.getWidth(this); //x좌표측으로 이동하는 속도가 5
				bgX -= 10; //배경도 같이 움직이는 코드 오른쪽으로 이동
			}
			else {
				int key1 = e.getKeyCode();
				if (key1 == e.VK_LEFT || key1 == e.VK_NUMPAD4 || key1 == e.VK_KP_LEFT){
					sel = (sel == 1)?3:3;  //삼항연산자 
                    x = (x > 0)?x - 10 :getWidth() + image.getWidth(this);
                    bgX += 10; //배경도 같이 움직이는 코드 왼쪽으로 이동
				}
				
				int key2 = e.getKeyCode();
                if(key2 == e.VK_UP || key2 == e.VK_NUMPAD8 || key2 == e.VK_KP_UP ) {
                	//sel = (sel == 1)?4:1;  //삼항연산자 
                   // y = (y > 0)?y - 10 : getHeight() + image.getHeight(this);
                    if (!isJump) { //점프여부 확인하고 점프 기능 실행
                        isJump = true;
                        jump();
                    }
                }
                
                int key3 = e.getKeyCode();
                if(key3 == e.VK_DOWN || key3 == e.VK_NUMPAD2 || key3 == e.VK_KP_DOWN) {
                	sel = (sel == 1)?4:1;  //삼항연산자
                    y = (y < getHeight())?y + 10 : image.getWidth(this);
                }
                
                int key4 = e.getKeyCode();
                if(key == e.VK_SPACE) {
                	int attackSpeed = 5; // 공격의 이동 속도 설정
                    Attack attack = new Attack(x, y, attackSpeed, sel);
                    attacks.add(attack);
                	 sel = (sel == 1)?5:1;
                }
                if (x < 0) {
                    x = 0;
                } else if (x > PANEL_WIDTH - image.getWidth(this)) {
                    x = PANEL_WIDTH - image.getWidth(this);
                }

                if (y < 0) {
                    y = 0;
                } else if (y > PANEL_HEIGHT - image.getHeight(this)) {
                    y = PANEL_HEIGHT - image.getHeight(this);
                }
			}
			 repaint(); //한번 실행하면 다시 원래 이미지로 복귀
		}
		
		private void jump() {
	        Jump jump = new Jump(this); // GameCanvas의 참조 전달
	        jump.start();
	    }
		public boolean isJump() {
	        return isJump;
	    }
	    // isJump 필드의 setter
	    public void setJump(boolean jump) {
	        this.isJump = jump;
	    }
	    public void setY(int newY) {
	        y = newY;
	    }
		@Override
		public void paint(Graphics g) {
		    super.paintComponent(g); // 상위 JPanel의 paintComponent 메서드를 호출하여 배경을 지우도록
		    g.drawImage(background, bgX, 0, this);
		    stage2monster.draw(g);

		    // Attack 그리기
		    for (Attack attack : attacks) {
		        attack.draw(g); // 공격 이미지 그리기
		    }

		    switch (sel) {
		        case 1:
		            image = Toolkit.getDefaultToolkit().getImage("rsc/피스1.png"); // 이미지1
		            break;
		        case 2:
		            image = Toolkit.getDefaultToolkit().getImage("rsc/피스2.png"); // 이미지2
		            break;
		        case 3:
		            image = Toolkit.getDefaultToolkit().getImage("rsc/피스3.png"); // 이미지3
		            break;
		        case 4:
		            image = Toolkit.getDefaultToolkit().getImage("rsc/피스1.png"); // 이미지4
		            break;
		        case 5:
		            image = Toolkit.getDefaultToolkit().getImage("rsc/공격.png");// 공격 이미지
		            break;
		    }
		    
		    g.drawImage(image,
		                 x - image.getWidth(this) / 2,
		                 y - image.getHeight(this) / 2, this); //캐릭터 그리기
		 
	        playerHp.draw(g); // PlayerHp 클래스의 draw 메서드 호출하여 체력 이미지 그리기
	        
		}
		@Override
		public void componentResized(ComponentEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void componentMoved(ComponentEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void componentShown(ComponentEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void componentHidden(ComponentEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void keyTyped(java.awt.event.KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void keyReleased(java.awt.event.KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
	        

		} 
		
		public static void main(String[] args) {
	           new Player();
	    }

}

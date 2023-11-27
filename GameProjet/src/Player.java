import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Player implements KeyListener{
	private Image player;
	private GameCanvas canvas;
	private Jump jump;
	private Stage2 stage2;
	private PlayerHp hp;
	private Stage2Monster monster2;
	private BufferedImage sprite;
	private BufferedImage jumping;
	private Graphics g;
	private State []jumpes;
	private State []states;
	private int stateIdx = 0;
	private int x = 100;
	private int y = 450;
	private int speed = 5;
	private int jumpHeight = 0; // 점프 높이
	private boolean isJump = false; // 캐릭터가 점프 중인지 여부
	private int bgX = 0; //배경 좌우키 이벤트 추가
	private Image map;
	private boolean isOnGround = false; // 바닥에 서 있는지 여부
	private int initialY; // 초기 Y 좌표
	
	private int width; // 추가: 캐릭터의 가로 길이
	private int height; // 추가: 캐릭터의 세로 길이
	private int prevX; // 추가: 이전 X 위치
	private int prevY; // 추가: 이전 Y 위치
    
	public Player() {
		loadImage();
		states = new State[6];
		State state = new State();
		states[0] = state;
		
		state.width = 58;
		state.height = 77;
		state.index_x = 0;
		state.start_x = 0;
		state.start_y = 0;
		state.frame_size = 6;
		state = new State();
		
		states[1] = state;
		state.width = 58;
		state.height = 77;
		state.index_x = 1;
		state.start_x = 121;
		state.start_y = 76;
		state.frame_size = 6;
		//state.stop = true;
		
		state = new State();
		states[2] = state;
		state.width = 58;
		state.height = 77;
		state.index_x = 2;
		state.start_x = 178;
		state.start_y = 76;
		state.frame_size = 6;
		
		state = new State();
		states[3] = state;
		state.width = 58;
		state.height = 77;
		state.index_x = 3;
		state.start_x = 233;
		state.start_y = 76;
		state.frame_size = 6;
		
		state = new State();
		states[4] = state;
		state.width = 58;
		state.height = 77;
		state.index_x = 4;
		state.start_x = 287;
		state.start_y = 76;
		state.frame_size = 6;
		//state.stop = true;
		
		state = new State();
		states[5] = state;
		state.width = 58;
		state.height = 77;
		state.index_x = 5;
		state.start_x = 337;
		state.start_y = 76;
		state.frame_size = 6;
		state.stop = true;
		
		jumpes = new State[4];
		State jump = new State();
		jumpes[0] = jump;
		
		jump.width=58;
		jump.height=79;
		jump.index_x=0;
		jump.start_x=0;
		jump.start_y=0;
		
		jump.width=58;
		jump.height=79;
		jump.index_x=1;
		jump.start_x=111;
		jump.start_y=79;

		jump.width=58;
		jump.height=79;
		jump.index_x=2;
		jump.start_x=164;
		jump.start_y=79;
		
		jump.width=58;
		jump.height=79;
		jump.index_x=3;
		jump.start_x=216;
		jump.start_y=79;
		
		this.x = x;
		this.y = y;
		this.stage2 = stage2;
		this.initialY = y; // 초기 Y 좌표 저장
		this.monster2 = new Stage2Monster();
		this.hp = new PlayerHp(this); // PlayerHp 객체 인스턴스
		//map = new ImageIcon("stage/1.png").getImage();
	}
	private void loadImage() {
		try {
			this.sprite = ImageIO.read(new File("character/step.png"));
			this.jumping = ImageIO.read(new File("character/jump.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private State getState() {
		return states[stateIdx];
	}
	private boolean flip = false;
	private void drawCharacter(State state, Graphics g, GameCanvas gameCanvas) {
		BufferedImage bufferedImage = new BufferedImage(state.width, state.height, BufferedImage.TYPE_INT_ARGB);

	    Graphics gb = bufferedImage.getGraphics();
	    
	    if (isJump) {
	        gb.drawImage(jumping,
	                0, 0,  // 위치
	                0 + state.width, 0 + state.height, // 크기
	                state.width * state.index_x + state.start_x,
	                state.height * state.index_y + state.start_y,
	                state.width * state.index_x + state.width + state.start_x,
	                state.height * state.index_y + state.start_y + state.height,
	                gameCanvas);
	    }
	    else {
	    	gb.drawImage(sprite, 
					0, 0,  //위치 
					0 + state.width, 0 + state.height, //크기 
					state.width*state.index_x + state.start_x, 
					state.height*state.index_y + state.start_y, 
					state.width*state.index_x + state.width + state.start_x, 
					state.height*state.index_y + state.start_y + state.height, 
					gameCanvas);
	    }
	    
	    gb.dispose();
	    
	    if(this.flip) {
		    AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
		    tx.translate(-state.width, 0);
		    AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		    bufferedImage = op.filter(bufferedImage, null);
	    }
	    g.drawImage(bufferedImage, x, y, null);
	    
	    
		if(gameCanvas.getCount() % 50 == 0)
		{
			if(state.index_x < state.frame_size-1)
			{
				state.index_x++;
			}
			else
			{
				if(!state.stop)
					state.index_x = 0;
				else
					state.index_x = state.frame_size-1;
			}
		}
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		switch(e.getKeyCode())
		{
		case KeyEvent.VK_LEFT:
            this.flip = true; // 왼쪽 키 눌렸을 때 flip을 true로 설정하여 이미지 반전
			x -= 8;
			bgX += 10;
			System.out.println("왼쪽");
			break;
		case KeyEvent.VK_RIGHT:
            this.flip = false; // 오른쪽 키 눌렸을 때 flip을 false로 설정하여 이미지 반전 해제
			x += 8;
			bgX -= 10;
			System.out.println("오른쪽");
			break;
		case KeyEvent.VK_SPACE:
			if (!isJump) { //점프여부 확인하고 점프 기능 실행
                isJump = true;
                jump();
            }
			break;
		case KeyEvent.VK_UP:
            // up 키 눌렸을 때의 동작 (사다리 올라가기)
            if (isOnLadder()) { // 사다리 위에 있는지 확인
                y -= speed; // y 좌표를 위로 이동
            }
            break;
		case KeyEvent.VK_DOWN:
			break;
		case KeyEvent.VK_A:
			//this.stateIdx = 4;
			break;
		}
	}
	public int getY() {
        return y;
    }
	private void jump() {
        Jump jump = new Jump(this);
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
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		this.stateIdx = 0;
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	private void monsterCheck() {
	    Rectangle playerBox = new Rectangle(x, y, width, height); // 캐릭터의 충돌 박스
	    Rectangle monsterBox = new Rectangle(monster2.getX(), monster2.getY(), monster2.getWidth(), monster2.getHeight()); // 몬스터의 충돌 박스

	    if (playerBox.intersects(monsterBox)) {
            // 플레이어와 몬스터 간 충돌 감지
            hp.decreaseHp(50); // 충돌 시 플레이어의 체력을 50 감소
            System.out.println("몬스터와 충돌! 플레이어 체력: " + hp.getHp());
        }
	}
	public void setHp(PlayerHp hp) {
        this.hp = hp; // 플레이어 체력 객체 설정
    }

/*
	// Stage2Monster 클래스에 width, height 값을 반환하는 메서드 추가
	public int getWidth() {
	    return monster2.getWidth(); // 이미지의 폭을 반환
	}

	public int getHeight() {
	    return monster2.getHeight(); // 이미지의 높이를 반환
	}
*/

	public void draw(Graphics g, GameCanvas gameCanvas) {
		monsterCheck(); // 충돌 체크
		//g.drawImage(sprite, 50, 50, 700, 150, gameCanvas);
		//g.drawImage(map, bgX, 0, 2000, 600, null); //map 움직이기
		//drawCharacter(getState(), g, gameCanvas);
		int rectX = 600;
		int rectY = 400;
		int rectWidth = 140;
		int rectHeight = 40;
		    
		int rectX2 = 635;
		int rectY2 = 400;
		int rectWidth2 = 35;
		int rectHeight2 = 130;

		// 현재 플레이어의 가로와 세로 길이
		width = getState().width;
		height = getState().height;

		// 캐릭터의 이전 위치 저장
		prevX = x;
		prevY = y;

		// 캐릭터와 사각형 경계 간의 충돌 감지
		if (x < rectX + rectWidth &&
		    x + width > rectX &&
		    y < rectY + rectHeight &&
		    y + height > rectY) {
		// 충돌이 감지되면 캐릭터의 위치를 경계선 위로 고정
		     y = rectY - height;
		     System.out.println("충돌");
		 	}
		    // 캐릭터와 사각형 경계 간의 충돌 감지
		if ((x < rectX || x + width > rectX + rectWidth || y + height < rectY) &&
		    (x < rectX2 || x + width > rectX2 + rectWidth2 || y + height < rectY2)) {
		    // 캐릭터가 사다리 경계선 이외의 영역에 있는 경우, 초기 Y 좌표까지만 떨어지도록 y 좌표를 증가시킴 
		     y += speed;
		     if (y > initialY) {
		         y = initialY; // 초기 Y 좌표까지만 떨어지도록 제한
		        }
		    } else {
		        // 사다리 경계선에 있는 경우나 충돌이 없는 경우 캐릭터를 그리고 이동
		       // drawCharacter(getState(), g, gameCanvas);
		    }
		    drawCharacter(getState(), g, gameCanvas);
	}
	private boolean isOnLadder() {
	    // 사다리에 해당하는 영역을 정의하고, 현재 캐릭터가 해당 영역에 있는지 확인하여 사다리 위에 있는지 여부를 판단
	    int ladderX = 635;
	    int ladderY = 400;
	    int ladderWidth = 35;
	    int ladderHeight = 130;
	    // 캐릭터와 사다리 충돌 여부 확인
	    return x < ladderX + ladderWidth &&
	           x + width > ladderX &&
	           y < ladderY + ladderHeight &&
	           y + height > ladderY;
	}
}

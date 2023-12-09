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
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Player implements KeyListener{
	private Image player;
	private GameCanvas canvas;
	private Jump jump;
	//private Stage2 stage2;
	//private Stage3 stage3;
	private Stage stage;
	private PlayerHp hp;
	private Attack atteck;//이건 클래스 가져온
	private BufferedImage sprite;
	private BufferedImage jumping;
	private BufferedImage attack;
	private Graphics g;
	private int state = 0;
	private State []jumpes;
	private State []states;
	private State []attacks;
	private int stateIdx = 0;
	private int x ;
	private int y = 450;
	private int speed = 5;
	private int jumpHeight = 0; // 점프 높이
	private boolean isJump = false; // 캐릭터가 점프 중인지 여부
	private boolean isFlip = false; // 캐릭터가 반전 중인지 여부
	private boolean isOnGround = false; // 바닥에 서 있는지 여부
	private int initialY; // 초기 Y 좌표
	private long lastTime = 0; // 마지막 충돌 시간 저장
    private final long Delay = 2000; // 충돌 딜레이: 2초(2000ms)
    private int bgX = 0;
    private boolean isAttacking = false; // 공격 중인지 여부
    private int gravitySpeed = 1; // 중력에 의한 낙하 속도

	private int width; // 추가: 캐릭터의 가로 길이
	private int height; // 추가: 캐릭터의 세로 길이
	private int prevX; // 추가: 이전 X 위치
	private int prevY; // 추가: 이전 Y 위치

	public Player(LinkedList<Stage> stageList) {
		this.stage = stageList.get(3);
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
		jump.width = 58;
		jump.height = 79;
		jump.index_x = 0;
		jump.start_x = 0;
		jump.start_y = 0;

		jump = new State(); // 새로운 State 객체 생성
		jumpes[1] = jump;
		jump.width = 58;
		jump.height = 79;
		jump.index_x = 1;
		jump.start_x = 111;
		jump.start_y = 79;
		
		jump = new State(); // 새로운 State 객체 생성
		jumpes[2] = jump;
		jump.width=58;
		jump.height=79;
		jump.index_x=2;
		jump.start_x=164;
		jump.start_y=79;
		
		jump = new State(); // 새로운 State 객체 생성
		jumpes[3] = jump;
		jump.width=58;
		jump.height=79;
		jump.index_x=3;
		jump.start_x=216;
		jump.start_y=79;
		
		attacks = new State[4];
		State attack = new State();
		attacks[0] = attack;
		attack.width = 100;
		attack.height = 74;
		attack.index_x = 0;
		attack.start_x = 0;
		attack.start_y = 0;
		this.x = 0;
		this.y = y;
		//this.stage2 = stage2;
		this.initialY = y; // 초기 Y 좌표 저장
		this.hp = new PlayerHp(); // PlayerHp 객체 인스턴스
	}
	
	private void loadImage() {
		try {
			this.sprite = ImageIO.read(new File("character/step.png"));
			this.jumping = ImageIO.read(new File("character/jump.png"));
			this.attack = ImageIO.read(new File("character/Attack.png"));
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
		hp.draw(g);
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
	    }else if (isAttacking) {
	    	gb.drawImage(attack,
	    			0, 0,  // 위치
	                40 + state.width, 3 + state.height,
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
		this.stage.keyPressed(e);
		switch(e.getKeyCode())
		{
		case KeyEvent.VK_LEFT:
            this.flip = true; // 왼쪽 키 눌렸을 때 flip을 true로 설정하여 이미지 반전
            isFlip = true;
            x -= 4;
			bgX += 10;
			//System.out.println("왼쪽");
			break;
		case KeyEvent.VK_RIGHT:
            this.flip = false; // 오른쪽 키 눌렸을 때 flip을 false로 설정하여 이미지 반전 해제
            isFlip = false;
            x += 4;
			bgX -= 10;
			//System.out.println("오른쪽");
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
			 if (!isAttacking) {
	                // 공격 중이 아닌 경우에만 공격 생성
	                isAttacking = true; // 현재 공격 중
	                // 공격 애니메이션 재생 또는 공격에 따른 동작 수행
	            }
	        break;
		}
	}
	public Rectangle getRect() {
		return new Rectangle(x, y, this.states[state].width, this.states[state].height);
	}
	public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }
	public int prevX() {
		return this.prevX;
	}
	public int prevY() {
		return this.prevY;
	}
	private void jump() {
        Jump jump = new Jump(this);
        jump.start();
    }
	public boolean isJump() {
        return isJump;
    }
	public boolean isFlip() {
        return isFlip;
    }

    // isJump 필드의 setter
    public void setJump(boolean jump) {
        this.isJump = jump;
    }
    public void setY(int newY) {
        y = newY;
    }
    public void setX(int newX) {
        x = newX;
    }

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		this.stateIdx = 0;
		switch(e.getKeyCode()) {
        case KeyEvent.VK_A:
            isAttacking = false; // 공격 상태 해제
            break;
		}
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	private void monsterCheck() {
        Rectangle playerBox = new Rectangle(x, y, width, height);
        Rectangle monsterBox = new Rectangle(stage.getX()+bgX+10, stage.getY()+8, stage.getWidth()-10, stage.getHeight()-10);

        if (playerBox.intersects(monsterBox)) {
            if (System.currentTimeMillis() - lastTime > Delay) {
                hp.decreaseHp(50); // 충돌 시 플레이어의 체력을 50 감소
                lastTime = System.currentTimeMillis(); // 충돌 시간 갱신
                System.out.println("몬스터와 충돌! 플레이어 체력: " + hp.getHp());
            }
        }
        stage.checkMonster(playerBox, prevY, initialY, gravitySpeed);
       
    }
	public void setHp(PlayerHp hp) {
        this.hp = hp; // 플레이어 체력 객체 설정
    }

	public void draw(Graphics g, GameCanvas gameCanvas) {
		stage.draw(g);
		if (isAttacking) {
	        // 공격 이미지를 그리는 로직 추가
	        // 해당 로직을 통해 공격 이미지를 화면에 표시
	    } else {
	        // 기존의 캐릭터 이미지를 그리는 로직
	        drawCharacter(getState(), g, gameCanvas);
	    }
		monsterCheck(); // 충돌 체크
		// 현재 플레이어의 가로와 세로 길이
		width = getState().width;
		height = getState().height;

		// 캐릭터의 이전 위치 저장
		prevX = x;
		prevY = y;
		    drawCharacter(getState(), g, gameCanvas);
	}
	private boolean isOnLadder() {
	    // 사다리에 해당하는 영역을 정의하고, 현재 캐릭터가 해당 영역에 있는지 확인하여 사다리 위에 있는지 여부를 판단
	    int ladderX = 635+bgX;
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
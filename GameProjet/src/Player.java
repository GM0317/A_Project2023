import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
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
	    gb.drawImage(sprite, 
				0, 0,  //위치 
				0 + state.width, 0 + state.height, //크기 
				state.width*state.index_x + state.start_x, 
				state.height*state.index_y + state.start_y, 
				state.width*state.index_x + state.width + state.start_x, 
				state.height*state.index_y + state.start_y + state.height, 
				gameCanvas);
	   /* if (isJump) {
	        gb.drawImage(jumping,
	                0, 0,  // 위치
	                0 + state.width, 0 + state.height, // 크기
	                state.width * state.index_x + state.start_x,
	                state.height * state.index_y + state.start_y,
	                state.width * state.index_x + state.width + state.start_x,
	                state.height * state.index_y + state.start_y + state.height,
	                gameCanvas);
	    }*/
	    
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
			System.out.println("왼쪽");
			break;
		case KeyEvent.VK_RIGHT:
            this.flip = false; // 오른쪽 키 눌렸을 때 flip을 false로 설정하여 이미지 반전 해제
			x += 4;
			System.out.println("오른쪽");
			break;
		case KeyEvent.VK_SPACE:
			if (!isJump) { //점프여부 확인하고 점프 기능 실행
                isJump = true;
                jump();
            }
			break;
		case KeyEvent.VK_A:
			//this.stateIdx = 4;
			break;
		}
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
	public void draw(Graphics g, GameCanvas gameCanvas) {
		//g.drawImage(sprite, 50, 50, 700, 150, gameCanvas);
		drawCharacter(getState(), g, gameCanvas);
	}
}

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Monster1 extends Monster{
    private Image monsterImage;
    private State[] states;
    private int stateIdx = 0;
    private BufferedImage sprite;
    protected int speed; // 몬스터 이동 속도
    protected int move; // 몬스터 움직이는 거리
    protected int maxX; // 몬스터 최대 x좌표
    protected int minX; // 몬스터 최소 x좌표
    protected boolean isFlipped; // 이미지 반전 여부

   public Monster1(Player play, int x, int y, int w, int h, int bgX, int speed, int move) {
	   super(play, x, y, w, h, 100);
	   this.speed=speed;
	   this.move=move;
       monsterImage = new ImageIcon("rsc/모나피.png").getImage();
       loadImage();
        
        states = new State[5];
        State state = new State();
        states[0] = state;
           
        state.width = 90;
        state.height = 105;
        state.index_x = 0;
        state.index_y = 0;
        state.start_x = 0;
        state.start_y = 0;
        state.frame_size = 5;
        
        state = new State();
        states[1] = state;
        state.width = 75;
        state.height = 105;
        state.index_x = 0;
        state.index_y = 0;
        state.start_x = 480;
        state.start_y = 0;
        state.frame_size = 3;
        state.stop = true;
        
        state = new State();
        states[2] = state;
        state.width = 82;
        state.height = 105;
        state.index_x = 0;
        state.index_y = 0;
        state.start_x = 0;
        state.start_y = 120;
        state.frame_size = 6;
        
        state = new State();
        states[3] = state;
        state.width = 82;
        state.height = 105;
        state.index_x = 0;
        state.index_y = 0;
        state.start_x = 540;
        state.start_y = 120;
        state.frame_size = 6;
        
        state = new State();
        states[4] = state;
        state.width = 100;
        state.height = 105;
        state.index_x = 0;
        state.index_y = 0;
        state.start_x = 0;
        state.start_y = 260;
        state.frame_size = 2;
        state.stop = true;
        
      
        maxX=x+move;
        minX=x;

    }
    private void loadImage() {
          try {
            this.sprite = ImageIO.read(new File("rsc/모나피.png"));
            //this.sprite = TransformColorToTransparency(this.sprite, new Color(70, 112, 104));
         } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
      }
    private State getState() {
    	return states[stateIdx];
      }
 
    public void draw(Graphics g, GameCanvas screen) {
        drawMonster(getState(), g, screen); // monster 이미지를 현재 x, y 위치에 그림
    }
    private void drawMonster(State state, Graphics g, GameCanvas screen) {
        g.drawImage(sprite, 
              x, y,  //위치 
              x + state.width, y + state.height, //크기 
              state.width*state.index_x + state.start_x, 
              state.height*state.index_y + state.start_y, 
              state.width*state.index_x + state.start_x + state.width, 
              state.height*state.index_y + state.start_y + state.height, 
              screen);
        
        if(screen.getCount() % 100 == 0)
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
        
        Rectangle playerBox = player.getRect();
        Rectangle monsterBox = new Rectangle(this.x, this.y, this.mWidth, this.mHeight);
        //Rectangle attackbox = new Rectangle(boom.getX(), boom.getY(), 10, 10);
        //g.drawRect(monsterBox.x, monsterBox.y, monsterBox.width, monsterBox.height);
        //g.drawRect(playerBox.x, playerBox.y, playerBox.width, playerBox.height);
        //System.out.println(this.getClass().getName()+", "+ playerBox+", "+monsterBox+":"+playerBox.intersects(monsterBox));
        
     }


    public void moveMonster(int direction) {
    	// direction이 1이면 오른쪽, -1이면 왼쪽으로 이동
        if (direction == 1) {
        	if(player.getX()>3) {
        		minX += 10;
        		maxX += 10;
        	}
        		
        	
        } else if (direction == -1) {
        	if(player.getX()>3)
        	{
        		minX -= 10;
        		maxX -= 10;
        	}
        		
        }
    }
    @Override
    public void moveMonster() {
        // 몬스터가 일정한 속도로 계속해서 왼쪽과 오른쪽으로 반복해서 이동
    	//System.out.println("몬스터 이동");
    	
    		x += speed; // 몬스터의 현재 방향으로 이동

            // x 좌표가 오른쪽 끝에 도달하면 방향을 반전하고 이미지도 반전
            if (x >= maxX) {
                x = maxX;
                speed = -speed; // 방향 반전
                isFlipped = true; // 이미지 반전 플래그 설정
            }

            // x 좌표가 왼쪽 끝에 도달하면 방향을 반전하고 이미지도 반전
            if (x <= minX) {
                x = minX;
                speed = -speed; // 방향 반전
                isFlipped = false; // 이미지 반전 플래그 해제
            }
    	
    }
}
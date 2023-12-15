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
    private int x = 150;
	private int y = 420;

   public Monster1(Player play, int x, int y, int bgX) {

      super(play, x, y, 1000, 1430, 200);
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

/*
    public void draw(Graphics g) {
        g.drawImage(monsterImage, x, y, mWidth, mHeight, null); // monster 이미지를 현재 x, y 위치에 그림
    }
*/    
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
     }

    
   /*
    * private void loadImage() { try { this.sprite = ImageIO.read(new
    * File("resources/ryu.png")); this.sprite =
    * TransformColorToTransparency(this.sprite, new Color(70, 112, 104)); } catch
    * (IOException e) { // TODO Auto-generated catch block e.printStackTrace(); } }
    */

    public void moveMonster(int direction) {
    	// direction이 1이면 오른쪽, -1이면 왼쪽으로 이동
        if (direction == 1) {
        	x += 10;
        } else if (direction == -1) {
        	x -= 10;
        }
    }
}
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Monster1 {
    private Image monster;
    private Player player;
    private State[] states;
    private int stateIdx = 0;
    private int x = 50;
    private int y = 505;
    public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
    public Monster1() {
        monster = new ImageIcon("rsc/모나피.png").getImage();
        //loadImage();
		
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

    public void draw(Graphics g) {
        g.drawImage(monster, x, y, 1000, 1430, null); // monster 이미지를 현재 x, y 위치에 그림
    }
    
	/*
	 * private void loadImage() { try { this.sprite = ImageIO.read(new
	 * File("resources/ryu.png")); this.sprite =
	 * TransformColorToTransparency(this.sprite, new Color(70, 112, 104)); } catch
	 * (IOException e) { // TODO Auto-generated catch block e.printStackTrace(); } }
	 */

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return monster.getWidth(null); // 몬스터 이미지의 너비 반환
    }

    public int getHeight() {
        return monster.getHeight(null); // 몬스터 이미지의 높이 반환
    }
}

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Stage2Monster {
	private int x; //monster X좌표
	private int y; //monster Y좌표
	private int speed; //monster 이동속도
	private State []states; //monster 상태 배열
	private int state = 0;
	private BufferedImage monster2;
	private Image monster;
	private Player player;
	
	public void Stage2Monster(){
		this.x=0; //초기 x좌표
		this.y=0; //초기 y좌표
		this.speed=5; //monster 이동 속도 5
		//this.player = player;
		//this.monster = new ImageIcon("rsc/monster2기본.png").getImage(); // 몬스터 이미지 초기화
		
		
	}
	public void moveLeft() { // 왼쪽으로 이동하는 메소드
        x -= speed; // x 좌표를 왼쪽으로 이동
        // 이동 후의 추가 로직
    }

    public void moveRight() { // 오른쪽으로 이동하는 메소드
        x += speed; // x 좌표를 오른쪽으로 이동
        // 이동 후의 추가 로직
    }
    
	public void draw(Graphics g) {
		monster = new ImageIcon("rsc/monster2기본.png").getImage();
		g.drawImage(monster, 100, 430, 150, 150, null); // monster 이미지를 현재 x, y 위치에 그림
	}
//
}

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Attack {
	private int x; // 공격 x 좌표
	private int y; // 공격 y 좌표
	private int speed; //공격 속도 
	private int direction; // 공격 방향
	private Image attack; // 공격 이미지
	private GameScreen gameScreen;
	private boolean isAttacking = false; // 공격 중인지 여부
	// 움직이는 상태
	private boolean left;
	private boolean right;
	private boolean up;
	protected Image boom;
	
	public Attack(int x, int y, int speed, int direction) {
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.direction = direction;	
		loadImage();
	}
	 private void loadImage() {
	        try {
	            this.boom = ImageIO.read(new File("character/총알.png"));
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	
	
	public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
	
	public void move() {
		// 공격의 위치를 이동
		// 총알의 이동 로직을 직선으로 수정
		//x += speed;
	    switch (direction) {
	       case 1:
	            // 위
	            y -= speed;
	            break;
	        case 2:
	            // 오른쪽
	            x += speed;
	            break;
	        case 3:
	            // 왼쪽
	            x -= speed;
	            break;
	        case 4:
	            // 아래
	            y += speed;
	            break;
	    }
	    System.out.println("x: "+x+", speed: "+speed+", direction:"+ direction);
	}
	
	public boolean isAttacking() {
        return isAttacking;
    }

    public void setAttacking(boolean attacking) {
        isAttacking = attacking;
    }
	public void draw(Graphics g) {
		boom = new ImageIcon("character/총알.png").getImage(); //이미지 파일 가져오기
		g.drawImage(boom, x, y, 50, 50, null); //공격 이미지 생성
	}

}

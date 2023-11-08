import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Attack extends JLabel{
	private int x; // 공격 x 좌표
	private int y; // 공격 y 좌표
	private int speed; //공격 속도 
	private int direction; // 공격 방향
	private Image attack; // 공격 이미지
	//private GameScreen gameScreen;
	// 움직이는 상태
	private boolean left;
	private boolean right;
	private boolean up;
	
	public Attack(int x, int y, int speed, int direction) {
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.direction = direction;		
	}
	
	public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
	
	public void move() {
		// 공격의 위치를 이동
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
	}
	
	public void draw(Graphics g) {
		attack = new ImageIcon("rsc/pngwing.com (2).png").getImage(); //이미지 파일 가져오기
		g.drawImage(attack, x, y, 50, 50, null); //공격 이미지 생성
	}

}

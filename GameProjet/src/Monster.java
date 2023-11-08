import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Monster extends Thread{
	
	private int x; // 괴물의 x좌표 위치
	private int y; // 괴물의 y좌표 위치
	private int speed; // 괴물 속도
	private int attack;
	private int left_x; // Image -> int로 타입 변경
    private int right_x; // Image -> int로 타입 변경
    private String direction = "right";
    private Image nowState;
    
	public Monster(int x, int y, int left_x, int right_x) {
		this.x = x;
        this.y = y;
        this.left_x = left_x;
        this.right_x = right_x;
	}
	public Image getNowState() {
        return nowState;
    }
	 public int getX() {
	        return x;
	}

	 public int getY() {
	        return y;
	}
	    

	    public void run() {
	        while (!Stage.stageClear) {

	            if (direction.equals("left")) {
	                x -= 10;
	                if (x <= left_x)
	                    direction = "right";

	                try {
	                    Thread.sleep(50);
	                } catch (Exception e) {

	                }
	            } else if (direction.equals("right")) {
	                x += 10;
	                if (x >= right_x)
	                    direction = "left";

	                try {
	                    Thread.sleep(50);
	                } catch (Exception e) {

	                }
	            }
	        }
	    }

}

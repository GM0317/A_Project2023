import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Monster2 extends Monster {
    protected Image monsterImage;
    protected boolean isFlipped; // 이미지 반전 여부
    protected int speed; // 몬스터 이동 속도
    protected int maxX; // 몬스터 최대 x좌표
    protected int minX; // 몬스터 최소 x좌표
    
    public Monster2(Player play, int x, int y, int bgX) {
        super(play, x- bgX, y, 43, 122, 123);// x, y, 가로, 세로, 체력
        monsterImage = new ImageIcon("rsc/monster2.png").getImage();
        isFlipped = false;
        speed = 50;
        maxX=250;
        minX=450;
    }

    public void draw(Graphics g) {
    	g.drawImage(monsterImage, x, y, mWidth, mHeight, null);
        if (!isFlipped) {
            g.drawImage(monsterImage, x, y, mWidth, mHeight, null);
        } else {
            g.drawImage(monsterImage, x + mWidth, y, -mWidth, mHeight, null);
        }
    }

    /*@Override
    public void moveMonster() {
        // 몬스터가 일정한 속도로 계속해서 왼쪽과 오른쪽으로 반복해서 이동
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
    }*/

    @Override
    public void moveMonster(int direction) {
    	// direction이 1이면 오른쪽, -1이면 왼쪽으로 이동
        if (direction == 1) {
        	x += 10;
        } else if (direction == -1) {
        	x -= 10;
        }
    }
}

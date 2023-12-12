import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Monster3 extends Monster {
    private Image monsterImage;

    public Monster3(Player play, int x, int y, int bgX){
        // 배경의 초기 위치를 고려하여 몬스터의 초기 x 좌표 설정
        super(play, x + bgX, y, 50, 50, 100);
        monsterImage = new ImageIcon("rsc/3_monster_1.png").getImage();
    }
    @Override
    public void moveMonster(int direction) {
        // direction이 1이면 오른쪽, -1이면 왼쪽으로 이동
        if (direction == 1) {
        	x += 10;
        } else if (direction == -1) {
        	x -= 10;
        }
    }
    
    @Override
    public void draw(Graphics g) {
        g.drawImage(monsterImage, x, y, mWidth, mHeight, null);
    }
}
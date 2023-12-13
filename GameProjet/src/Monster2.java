import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Monster2 extends Monster{
	protected Image monsterImage;
	
	public Monster2(Player play, int x, int y) {
		super(play, x , y, 50, 50, 123);
		monsterImage = new ImageIcon("stage/monster2.png").getImage();
	}
	
	public void draw(Graphics g) {
        g.drawImage(monsterImage, x, y, 1000, 1430, null); // monster 이미지를 현재 x, y 위치에 그림
    }

	@Override
    public void moveMonster(int direction) {

    }
	  
    /*if (movingLeft) {
        g.drawImage(monsterImage, monsterX + bgX, monsterY, monsterWidth, monsterHeight, null);
    } else {
        int imageWidth = monsterImage.getWidth(null);
        int imageHeight = monsterImage.getHeight(null);

        g.drawImage(monsterImage, monsterX + monsterWidth + bgX, monsterY, -monsterWidth, monsterHeight, null);
    }
    // 충돌 판정을 몬스터 이미지 경계를 기준으로 그립니다
    g.setColor(Color.RED);
    g.drawRect(monsterX + bgX+10, monsterY +8, monsterWidth - 10, monsterHeight - 10); // 몬스터의 충돌 판정 영역*/
}
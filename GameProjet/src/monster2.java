import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class monster2 extends Monster{
	protected Image monsterImage;
	public monster2(int monX, int monY, int monsterWidth, int monsterHeight){ 
		super(monX, monY, monsterWidth, monsterHeight);
	}
	
	public void draw(Graphics g) { //몬스터 그림 그리기
		monsterImage = new ImageIcon("stage/monster2.png").getImage();
	};
	public void moveMonster() { //몬스터 이동하는 설명
		
	};
	
	  
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

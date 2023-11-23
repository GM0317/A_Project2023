import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Stage2 {
	private Image map;
	//private GameCanvas Canvas;
	int bgX = 0; //배경 좌우키 이벤트 추가
	public Stage2() {
		map = new ImageIcon("rsc/스테이지2 art.png").getImage();
	}
	public void draw(Graphics g) {
		g.drawImage(map, 0, 0, null);
	}

}

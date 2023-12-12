import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Hide {
	private Image hide; // 숨기 이미지
	
	public Hide() {
		
	}
	
	public void draw(Graphics g) {
		hide = new ImageIcon("character/총알.png").getImage(); //이미지 파일 가져오기
		g.drawImage(hide, 0, 0, 50, 50, null); //공격 이미지 생성
	}
}

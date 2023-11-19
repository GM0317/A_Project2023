import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class GameCanvas extends JPanel {
	private Image image; // 캐릭터 이미지
	private Image background; // 배경 이미지
	private List<Attack> attacks = new ArrayList<>(); // 공격 클래스 목록
	private Player player; // GameCanvas 클래스의 인스턴스에 접근하기 위한 참조
	
	public void GameCanvas(Player player) {
		this.player=player;
	}
	
	@Override
	public void paint(Graphics g) {
		 super.paintComponent(g); // 상위 JPanel의 paintComponent 메서드를 호출하여 배경을 지우도록
		   // g.drawImage(background, 0, 0, background.getWidth(this), background.getHeight(this), this); // 배경 이미지 그리기 

		    // Attack 그리기
		    for (Attack attack : attacks) {
		        attack.draw(g); // 공격 이미지 그리기
		    }

		    switch (player.sel) {
		        case 1:
		            image = Toolkit.getDefaultToolkit().getImage("rsc/피스1.png"); // 이미지1
		            break;
		        case 2:
		            image = Toolkit.getDefaultToolkit().getImage("rsc/피스2.png"); // 이미지2
		            break;
		        case 3:
		            image = Toolkit.getDefaultToolkit().getImage("rsc/피스3.png"); // 이미지3
		            break;
		        case 4:
		            image = Toolkit.getDefaultToolkit().getImage("rsc/피스1.png"); // 이미지4
		            break;
		        case 5:
		            image = Toolkit.getDefaultToolkit().getImage("rsc/공격.png");// 공격 이미지
		            break;
		    }
		    
		    g.drawImage(image,
		                 player.x - image.getWidth(this) / 2,
		                 player.y - image.getHeight(this) / 2, this); //캐릭터 그리기
	}
	
	public static void main(String[] args) {
		new GameCanvas();
	}

}

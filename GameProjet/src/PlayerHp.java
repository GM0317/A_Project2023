import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class PlayerHp {
    private Player player;
    private int hp;
    private Image Heart6;
    private Image Heart5;
    private Image Heart4;
    private Image Heart3;
    private Image Heart2;
    private Image Heart1;
    private Image Heart0;

    public PlayerHp(Player player) { // Player를 받는 생성자 추가
        this.player = player;
        this.hp = 500; // 체력을 500으로 초기화
        Heart6 = new ImageIcon("rsc/Heart6.png").getImage(); // 이미지 파일 가져오기
        Heart5 = new ImageIcon("rsc/Heart5.png").getImage();
        Heart4 = new ImageIcon("rsc/Heart4.png").getImage();
        Heart3 = new ImageIcon("rsc/Heart3.png").getImage();
        Heart2 = new ImageIcon("rsc/Heart2.png").getImage();
        Heart1 = new ImageIcon("rsc/Heart1.png").getImage();
        Heart0 = new ImageIcon("rsc/Heart0.png").getImage();
    }
	public void draw(Graphics g) {
		if (player != null) {
            if (hp >= 500) {
                g.drawImage(Heart6, 18, 18, 98, 30, null); // hp이미지 생성
            } else if (hp >= 450) {
                g.drawImage(Heart5, 18, 18, 98, 30, null); // hp 이미지 생성
            } else if (hp >= 400) {
            	g.drawImage(Heart4, 18, 18, 98, 30, null); // hp 이미지 생성
            } else if (hp >= 350) {
            	g.drawImage(Heart3, 18, 18, 98, 30, null); // hp 이미지 생성
            } else if (hp >= 300) {
            	g.drawImage(Heart2, 18, 18, 98, 30, null); // hp 이미지 생성
            } else if (hp >= 200) {
            	g.drawImage(Heart1, 18, 18, 98, 30, null); // hp 이미지 생성
            } else {
            	g.drawImage(Heart0, 18, 18, 98, 30, null); // hp 이미지 생성
            }
            
        }
	}

}

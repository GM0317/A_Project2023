import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Stage2 {
	private Image map;
	private Image floor;
	private Image ledder;
	private Image Tile;
	private Image Tile2;
	private Image Tile3;
	private Image Tile4;
	private Image monster;
	private int bgX = 0;
	
	private int monsterX = 1000; // 몬스터의 초기 X 좌표
    private int monsterY = 468; // 몬스터의 Y 좌표
    private int width;
    private int height;
    private int monsterSpeed = 1; // 몬스터의 이동 속도
    private boolean movingLeft = true; // 몬스터의 방향을 추적하는 플래그
    private Image monsterImage;
	public Stage2() {
		//this.player = player;
		//player = new Player(this);
		map = new ImageIcon("stage/1.png").getImage();
		floor = new ImageIcon("stage/stage Floor.png").getImage();
		ledder = new ImageIcon("stage/Ladder1.png").getImage();
		Tile = new ImageIcon("stage/Tile_09.png").getImage();
		Tile2 = new ImageIcon("stage/Tile_10.png").getImage();
		Tile3 = new ImageIcon("stage/Tile_11.png").getImage();
		Tile4 = new ImageIcon("stage/Tile_12.png").getImage();
		monsterImage = new ImageIcon("stage/monster2.png").getImage();
		
		this.monsterX = monsterX;
        this.monsterY = monsterY;
        this.width = width;
        this.height = height;
	}
	public void draw(Graphics g) {
		g.drawImage(map, bgX, 0, 3000, 600, null); // 백그라운드 배경
		g.drawImage(floor, bgX, 0, 3500, 570, null); // 바닥	
		// 사다리와 Tile 이미지를 고정된 좌표에 그리기
	    g.drawImage(Tile, 600 + bgX, 400, 35, 40, null);
	    g.drawImage(Tile2, 635 + bgX, 400, 35, 40, null);
	    g.drawImage(Tile3, 670 + bgX, 400, 35, 40, null);
	    g.drawImage(Tile4, 705 + bgX, 400, 35, 40, null);
	    //사다리
	    g.drawImage(ledder, 635 + bgX, 430, 35, 40, null);
	    g.drawImage(ledder, 635 + bgX, 460, 35, 40, null);
	    g.drawImage(ledder, 635 + bgX, 490, 35, 40, null);
	    g.drawImage(ledder, 635 + bgX, 400, 35, 40, null);
	 
	    // 사각형 경계선 그리기
	    g.setColor(Color.RED);
	    int rectX = 600 + bgX; //경계선 X좌표, bgX는 배경이 이동할 때 경계선 위치를 고정
	    int rectY = 400; //경계선 Y좌표
	    int rectWidth = 140; // 경계선 너비
	    int rectHeight = 40; // 경계선 높이
	    // 바닥 경계선 
	    g.setColor(Color.RED);
	    int ladderX = 635 + bgX;
	    int ladderY = 400;
	    int ladderWidth = 35;
	    int ladderHeight = 130;
	    // drawRect() 메서드를 사용하여 사각형의 경계선 그리기
	    g.drawRect(rectX, rectY, rectWidth, rectHeight); //타일 경계선
	    g.drawRect(ladderX, ladderY, ladderWidth, ladderHeight); //사다리 경계선
	    
	    int monsterWidth = 70;
	    int monsterHeight = 70;
	    
	    if (movingLeft) {
	        g.drawImage(monsterImage, monsterX + bgX, monsterY, monsterWidth, monsterHeight, null);
	    } else {
	        int imageWidth = monsterImage.getWidth(null);
	        int imageHeight = monsterImage.getHeight(null);

	        g.drawImage(monsterImage, monsterX + monsterWidth + bgX, monsterY, -monsterWidth, monsterHeight, null);
	    }

	    // 충돌 판정을 몬스터 이미지 경계를 기준으로 그립니다
	    g.setColor(Color.RED);
	    g.drawRect(monsterX + bgX+10, monsterY +8, monsterWidth - 10, monsterHeight - 10); // 몬스터의 충돌 판정 영역
	}
	public void moveMonster() {
        // 몬스터를 수평으로 방향에 따라 이동합니다
        if (movingLeft) {
            monsterX -= monsterSpeed;
        } else {
            monsterX += monsterSpeed;
        }

        // 몬스터가 특정 좌표에 도달하면 방향을 변경합니다
        if (monsterX <= 700) { // X 좌표가 700에 도달하면 방향을 변경합니다
            movingLeft = false;
        } else if (monsterX >= 1000) { // X 좌표가 1000에 도달하면 방향을 변경합니다
            movingLeft = true;
        }
    }
	// getX() 메서드: X 좌표 반환
    public int getX() {
        return monsterX;
    }

    // getY() 메서드: Y 좌표 반환
    public int getY() {
        return monsterY;
    }

    public int getWidth() {
        return monsterImage.getWidth(null); // 몬스터 이미지의 너비 반환
    }

    public int getHeight() {
        return monsterImage.getHeight(null); // 몬스터 이미지의 높이 반환
    }
	
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		switch(e.getKeyCode())
		{
		case KeyEvent.VK_LEFT:
			bgX += 10;
			break;
		case KeyEvent.VK_RIGHT:
			bgX -= 10;
			break;
		}
	}
}

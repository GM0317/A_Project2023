import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Stage2 extends Stage {
	private Player player;
	private PlayerHp hp;
	private GameCanvas canvas;
	private Monster Mhp;
	private int Px = 400; //포탈 x좌표
	private int Py = 400; //포탈 y좌표
	private LinkedList<Onbject> objectList = new LinkedList<>();
	public Stage2(Player player, GameCanvas canvas) {
		this.canvas = canvas;
		this.player = player; //초기화
		if(player == null) {
			System.out.println("plaer는 null...");
		}
		map = new ImageIcon("stage/1.png").getImage();
		floor = new ImageIcon("stage/stage Floor.png").getImage();
		Tile = new ImageIcon("stage/Tile_09.png").getImage();
		Tile2 = new ImageIcon("stage/Tile_10.png").getImage();
		Tile3 = new ImageIcon("stage/Tile_11.png").getImage();
		Tile4 = new ImageIcon("stage/Tile_12.png").getImage();
		Portal = new ImageIcon("stage/유적 입구.png").getImage();
		monsterImage = new ImageIcon("stage/monster2.png").getImage();
		
		super.monsterX = monsterX;
		super.monsterY = monsterY;
		super.width = width;
		super.height = height;
		this.hp = player.getPlayerHp();
	}
	public void setPlaer(Player p) {
		player = p;
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
	@Override
	public void drawBackground(Graphics g) {
		g.drawImage(map, bgX, 0, 3000, 600, null); // 백그라운드 배경
		g.drawImage(floor, bgX, 0, 3500, 570, null); // 바닥	
		g.drawImage(Portal, Px+bgX, Py ,130, 150, null);
		
	}
	@Override
	public void drawTile(Graphics g) {
		// 사다리와 Tile 이미지를 고정된 좌표에 그리기
	    g.drawImage(Tile, 600 + bgX, 400, 35, 40, null);
	    g.drawImage(Tile2, 635 + bgX, 400, 35, 40, null);
	    g.drawImage(Tile3, 670 + bgX, 400, 35, 40, null);
	    g.drawImage(Tile4, 705 + bgX, 400, 35, 40, null);
	    
	    g.drawImage(Tile, 800 + bgX, 300, 35, 40, null);
	    g.drawImage(Tile2, 835 + bgX, 300, 35, 40, null);
	    g.drawImage(Tile3, 870 + bgX, 300, 35, 40, null);
	    g.drawImage(Tile4, 905 + bgX, 300, 35, 40, null);
	    
	    g.drawImage(Tile, 1000 + bgX, 200, 35, 40, null);
	    g.drawImage(Tile2, 1035 + bgX, 200, 35, 40, null);
	    g.drawImage(Tile2, 1070 + bgX, 200, 35, 40, null);
	    g.drawImage(Tile2, 1105 + bgX, 200, 35, 40, null);
	    g.drawImage(Tile2, 1140 + bgX, 200, 35, 40, null);
	    g.drawImage(Tile3, 1175 + bgX, 200, 35, 40, null);
	    g.drawImage(Tile3, 1205 + bgX, 200, 35, 40, null);
	    g.drawImage(Tile3, 1240 + bgX, 200, 35, 40, null);
	    g.drawImage(Tile3, 1275 + bgX, 200, 35, 40, null);
	    g.drawImage(Tile3, 1305 + bgX, 200, 35, 40, null);
	    g.drawImage(Tile3, 1340 + bgX, 200, 35, 40, null);
	    g.drawImage(Tile4, 1375 + bgX, 200, 35, 40, null);
	    
	    g.drawImage(Tile, 1500 + bgX, 300, 35, 40, null);
	    g.drawImage(Tile2, 1535 + bgX, 300, 35, 40, null);
	    g.drawImage(Tile3, 1570 + bgX, 300, 35, 40, null);
	    g.drawImage(Tile4, 1605 + bgX, 300, 35, 40, null);
	    
	    g.drawImage(Tile, 1700 + bgX, 400, 35, 40, null);
	    g.drawImage(Tile2, 1735 + bgX, 400, 35, 40, null);
	    g.drawImage(Tile3, 1770 + bgX, 400, 35, 40, null);
	    g.drawImage(Tile4, 1805 + bgX, 400, 35, 40, null);
	}
	
	public void drawPotal(Graphics g) {
		
	}
	@Override
	public void drawMonster(Graphics g) {
		//g.drawImage(Portal, 2800 + bgX, 460, null); //포털
		 
	    // 사각형 경계선 그리기
	    g.setColor(Color.RED);
	    int rectX = 600 + bgX; //경계선 X좌표, bgX는 배경이 이동할 때 경계선 위치를 고정
	    int rectY = 400; //경계선 Y좌표
	    int rectWidth = 140; // 경계선 너비
	    int rectHeight = 40; // 경계선 높이
	    
	    // 사각형 경계선 그리기
	    g.setColor(Color.RED);
	    int rectX2 = 800 + bgX;
	    int rectY2 = 300; 
	    int rectWidth2 = 140;
	    int rectHeight2 = 40;
	    
	    // 사각형 경계선 그리기
	    g.setColor(Color.RED);
	    int rectX3 = 1000 + bgX;
	    int rectY3 = 200;
	    int rectWidth3 = 410;
	    int rectHeight3 = 40;
	    
	    // 사각형 경계선 그리기
	    g.setColor(Color.RED);
	    int rectX4 = 1500 + bgX; //경계선 X좌표, bgX는 배경이 이동할 때 경계선 위치를 고정
	    int rectY4 = 300; //경계선 Y좌표
	    int rectWidth4 = 140; // 경계선 너비
	    int rectHeight4 = 40; // 경계선 높이
	    
	    // 사각형 경계선 그리기
	    g.setColor(Color.RED);
	    int rectX5 = 1700 + bgX; //경계선 X좌표, bgX는 배경이 이동할 때 경계선 위치를 고정
	    int rectY5 = 400; //경계선 Y좌표
	    int rectWidth5 = 140; // 경계선 너비
	    int rectHeight5 = 40; // 경계선 높이
	    // 바닥 경계선 	    
	    g.setColor(Color.RED);
	    int PortalX = 420 + bgX;
	    int PortalY = 430; 
	    int PortalWidth = 90;
	    int PortalHeight = 100; 
	    
	    // drawRect() 메서드를 사용하여 사각형의 경계선 그리기
	    g.drawRect(rectX, rectY, rectWidth, rectHeight); //타일 경계선
	    g.drawRect(rectX2, rectY2, rectWidth2, rectHeight2);
	    g.drawRect(rectX3, rectY3, rectWidth3, rectHeight3);
	    g.drawRect(rectX4, rectY4, rectWidth4, rectHeight4);
	    g.drawRect(rectX5, rectY5, rectWidth5, rectHeight5);
	    g.drawRect(PortalX, PortalY, PortalWidth, PortalHeight);
	    
	    check();
	    PortalChek();
		
	}
	public void check() {
		 if (player != null) {
			Rectangle[] tileLine = { // 직사각형 타일 경계선 배열
			        new Rectangle(800 + bgX, 300, 35, 40),
			        new Rectangle(800 + bgX, 300, 140, 40),
			        new Rectangle(1000 + bgX, 200, 410, 40),
			        new Rectangle(1500 + bgX, 300, 140, 40),
			        new Rectangle(1700 + bgX, 400, 140, 40),
			        new Rectangle(0 + bgX, 524, 3500, 40)
			        // 필요에 따라 다른 타일의 직사각형 추가 가능
			    };
			Rectangle playerBox = player.getRect();
			boolean onGround = false;

            for (Rectangle tileBoundary : tileLine) {
                if (playerBox.intersects(tileBoundary)) {
                    int playerBottom = playerBox.y + playerBox.height;
                    int tileTop = tileBoundary.y;
                    int overlap = playerBottom - tileTop;

                    // 플레이어를 경계선 위로 이동시킴
                    player.setY(player.getY() - overlap);
                    onGround = true; // 바닥에 닿음을 표시
                    break; // 첫 번째 충돌 발견 시 반복문을 빠져나감
                }
            }

            // 바닥에 닿지 않았을 경우, 플레이어를 내려감 (중력 적용)
            if (!onGround) {
                player.setY(player.getY() + 1); // 플레이어를 아래로 내림 (중력)
            }
        } else {
            System.out.println("플레이어 객체가 null입니다.");	
		 }
	}
	public void PortalChek() {
		if (player != null) {
			Rectangle[] tileLine = { // 직사각형 타일 경계선 배열
			        new Rectangle(Px + bgX, Py, 90, 100)
			        // 필요에 따라 다른 타일의 직사각형 추가 가능
			    };
			Rectangle playerBox = player.getRect();
            for (Rectangle tileBoundary : tileLine) {
                if (playerBox.intersects(tileBoundary)) {  
                	canvas.changeStage(3); //유적으로 이동
                	System.out.println("Portal!");	
                }
            }	
		 }
	}
	
}
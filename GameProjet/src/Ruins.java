import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.LinkedList;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;

public class Ruins extends Stage{
	private Player player;
	protected Image black;
	protected Image map; //유적 이미지
	protected Image statue; //동상
	protected Image statue2;
	protected Image key;
	
	private boolean isKeyCollected = false;//key를 먹었는지 안먹었는지 여부 확인
	
	private int rectX;
    private int rectY;
    private int rectWidth;
    private int rectHeight;
    private LinkedList<Onbject> objectList = new LinkedList<>();
	public Ruins(Player player) {
		if(player == null) {
			System.out.println("plaer는 null...");
		}
		this.player = player; //초기화
		
		
		black = new ImageIcon("stage/검은 바탕.png").getImage();
		map = new ImageIcon("stage/유적.png").getImage();
		//statue = new ImageIcon("stage/여신상.png").getImage();
		//statue2 = new ImageIcon("stage/여신상눈.png").getImage();
		key = new ImageIcon("stage/pixel-key.png").getImage();
        
	}	
	public void setPlaer(Player p) {
		player = p;
	}
	public void draw(Graphics g) {
		g.drawImage(black, bgX, 0, 3000, 600, null);
		g.drawImage(map, bgX, 0, 3000, 600, null);
		g.drawImage(statue, 1518+bgX, 1-97,500,700,null);
		g.drawImage(statue2, 1518+bgX, 1-97,500,700,null);
		if (!isKeyCollected) { // 키가 아직 먹히지 않았을 경우에만 키를 그림
	        g.drawImage(key, 1752 + bgX, 216, 35, 20, null);
	    }
	    	check();
	    	keyCheck();
	}
	public void check() {
        if (player != null) {
            Rectangle playerBox = player.getRect();

            Rectangle[] tileLine = { 
                new Rectangle(470 + bgX, 455, 1190, 90),
                new Rectangle(425 + bgX, 500, 1290, 90),
                new Rectangle(1820 + bgX, 500, 1290, 90),
                new Rectangle(1860 + bgX, 455, 1190, 90),
                new Rectangle(1650 + bgX, 550, 300, 10),
                new Rectangle(0 + bgX, 557, 500, 10),
                // 기존의 경계선들에 추가하고 싶은 영역을 이곳에 추가하십시오.
                new Rectangle(1400 + bgX, 540, 200, 50) // 예시로 추가된 바닥 영역. 수정이 필요합니다.
            };

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
	public void keyCheck() {
		Rectangle playerBox = player.getRect();
		Rectangle keyBox = new Rectangle(1752 + bgX, 216, 35, 20);
		if(playerBox.intersects(keyBox)) {
			if (!isKeyCollected) { // 키를 먹은 상태인 경우에만 사운드 재생
	            isKeyCollected = true; // 키를 먹었으므로 상태 변경
	            statueSound(new File("Sound/여신상 웃음소리.wav")); // 여신상 웃음소리 효과음 재생
	            statueSound(new File("Sound/Ruins.wav"));
	        }
			System.out.println("key!");
			statue2 = new ImageIcon("stage/여신상눈.png").getImage();
		}else {
			statue = new ImageIcon("stage/여신상.png").getImage();
		}
	}
	@Override
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
	
	private void statueSound(File file) { // 여신상 웃음소리 효과음 메서드
		Clip clip = null;
		try {
		clip = AudioSystem.getClip();
		clip.open(AudioSystem.getAudioInputStream(file));
		clip.start();
		} catch (Exception e) {
		e.printStackTrace();
		}
	}
	private void bgSound(File file) { // 백그라운드 배경음악
		Clip clip = null;
		try {
		clip = AudioSystem.getClip();
		clip.open(AudioSystem.getAudioInputStream(file));
		clip.start();
		} catch (Exception e) {
		e.printStackTrace();
		}
	}
	@Override
	public void drawBackground(Graphics g) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void drawTile(Graphics g) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void drawMonster(Graphics g) {
		// TODO Auto-generated method stub
		
	}
}
import java.awt.Color;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.LinkedList;
import java.util.List;
import javax.swing.Timer;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Ruins extends Stage{
	private Player player;
	private PlayerHp hp;
	private Flame flame;
	protected Image black;
	protected Image map; //유적 이미지
	protected Image statue; //동상
	protected Image statue2;
	protected Image key;
	protected Image attack; //불꽃
	private boolean isKeyCollected = false;//key를 먹었는지 안먹었는지 여부 확인
	private long lastTime = 0; // 마지막 충돌 시간 저장
    private final long Delay = 2000; // 충돌 딜레이: 2초(2000ms)
    private boolean flip = false;
    private int energy = 100;
	
	private int rectX;
    private int rectY;
    private int rectWidth;
    private int rectHeight;
    
    private final int delay = 1000; // 1초마다 생성
    private Timer timer;
    private LinkedList<Flame> flames = new LinkedList<>();
    private LinkedList<Onbject> objectList = new LinkedList<>();
    
	public Ruins(Player player) {
		this.flame = new Flame(this);
		flames.add(flame);
		List<String> myList = new ArrayList<String>();
        timer = new Timer(delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	 if (isKeyCollected) {
                     createFlame(); // 키가 수집된 후에만 불꽃 생성
                 }
            }
        });
        timer.start(); // 타이머 시작
    
		if(player == null) {
			System.out.println("plaer는 null...");
		}
		this.hp = new PlayerHp();
		this.player = player; //초기화
		black = new ImageIcon("stage/검은 바탕.png").getImage();
		map = new ImageIcon("stage/유적.png").getImage();
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
	    	flamesCheck();
	    	flamesCheck2();
	    	
	    	if (isKeyCollected) {
	    		drawEnergyBar(g);
	    	}
	    	if (isKeyCollected) {
	    	for (Flame flame : flames) {
	            flame.draw(g);
	        }
	    	}
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
                new Rectangle(1400 + bgX, 540, 200, 50) // 예시로 추가된 바닥 영역. 수정이 필요합니다.
            };

            boolean onGround = false;

            for (Rectangle tileBoundary : tileLine) {
                if (playerBox.intersects(tileBoundary)) {
                    //int playerBottom = playerBox.y + playerBox.height;
                    //int tileTop = tileBoundary.y;
                    //int overlap = playerBottom - tileTop;

                    // 플레이어를 경계선 위로 이동시킴
                    //player.setY(player.getY() - overlap);
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
	            createFlame();
	        }
			System.out.println("key!");
			statue2 = new ImageIcon("stage/여신상눈.png").getImage();
		}else {
			statue = new ImageIcon("stage/여신상.png").getImage();
		}
	}
	public void flamesCheck() {
	    Rectangle playerBox = player.getRect();
	    
	    for (Flame flame : flames) {
	        Rectangle flameBox = flame.getRect(0); // 각 불꽃 객체의 충돌 영역을 가져옴
	        
	        if (playerBox.intersects(flameBox)) {
	            System.out.println("아얏!"); // 충돌 시 수행할 동작
	            if (System.currentTimeMillis() - lastTime > Delay) {
	            	player.getPlayerHp().decreaseHp(50);// 충돌 시 플레이어의 체력을 50 감소
	                lastTime = System.currentTimeMillis(); // 충돌 시간 갱신
	                System.out.println("(flame)몬스터와 충돌! 플레이어 체력: " + hp.getHp());
	            }       
	        }
	    }
	}	
	public void flamesCheck2() {
	    Rectangle playerBox = player.getRect();
	    
	    for (Flame flame : flames) {
	        Rectangle flameBox = flame.getRect(1); // 각 불꽃 객체의 충돌 영역을 가져옴

	        if (playerBox.intersects(flameBox)) {
	        	if (System.currentTimeMillis() - lastTime > Delay) {
	                hp.decreaseHp(50); // 충돌 시 플레이어의 체력을 50 감소
	                lastTime = System.currentTimeMillis(); // 충돌 시간 갱신
	                System.out.println("(flame2)몬스터와 충돌! 플레이어 체력: " + hp.getHp());
	            }   
	            System.out.println("아!"); // 충돌 시 수행할 동작   
	        }
	    }
	}
	public void setHp(PlayerHp hp) {
        this.hp = hp; // 플레이어 체력 객체 설정
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
	
	public void createFlame() {
		if (isKeyCollected) { // 키를 먹은 후에만 불꽃을 생성
	        Flame flame = new Flame(this);
	        flames.add(flame);
	    }
    }
	private void drawEnergyBar(Graphics g) {
		g.drawRect(1615+bgX , 60, 300, 20);
		g.fillRect(1615+bgX, 60, energy * 3, 20);
	}
	public void decreaseEnergy() {
		if(this.energy >= 0)
		this.energy -= 10;
		else
		this.energy = 0;
		}


	public void drawFlame(int x, int y) {
	    //Graphics g = getGraphics(); // 현재 패널의 그래픽스 객체 가져오기
	   // g.drawImage(black, x, y, 20, 20, null); // 불꽃 이미지 그리기 (가로, 세로 크기는 20으로 설정)
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
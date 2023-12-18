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
	private GameCanvas canvas;
	private Stage4 stage4;
	private Ending ending;
	protected Image portal;
	protected Image black;
	protected Image map; //유적 이미지
	protected Image statue; //동상
	protected Image statue2;
	protected Image statue3;
	protected Image key;
	protected Image attack; //불꽃
	protected Image keyEat;
	protected Image Ending;
	private boolean isKeyCollected = false;//key를 먹었는지 안먹었는지 여부 확인
	private long lastTime = 0; // 마지막 충돌 시간 저장
    private final long Delay = 2000; // 충돌 딜레이: 2초(2000ms)
    private boolean flip = false;
    private int energy = 100;
	private int rectX;
    private int rectY;
    private int rectWidth;
    private int rectHeight;
    private Clip bgClip;
    private Clip crackClip;
    private boolean isShattered = false;
    private boolean isPortalActive = false;
    private final int delay = 1000; // 1초마다 생성
    private Timer timer;
    private Color energyColor = Color.RED;
    private LinkedList<Flame> flames = new LinkedList<>();
    private LinkedList<Onbject> objectList = new LinkedList<>();
    private String monsterName = "PERSEPHONE";
    private int endingY = 0;
	public Ruins(Player player, GameCanvas canvas) {
		this.flame = new Flame(this);
		this.canvas = canvas;
		this.ending = ending;
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
	 public boolean isKeyCollected() {
	        return isKeyCollected;
	}
	 
	public void setPlaer(Player p) {
		player = p;
	}
	public void draw(Graphics g) {
		if (bgX > 0) {
	        bgX = 0;
	    } else if (bgX < -2010) {  // 3500 (배경의 전체 너비) - 350 (화면의 너비)
	        bgX = -2010;
	    }
		g.drawImage(black, bgX, 0, 3000, 600, null);
		g.drawImage(map, bgX, 0, 3000, 600, null);
		g.drawImage(keyEat, 140, 20, 80, 30, null);
		g.drawImage(statue, 1518+bgX, 1-97,500,700,null);
		g.drawImage(statue2, 1518+bgX, 1-97,500,700,null);
		g.drawImage(statue3, 1518+bgX, 1-97,500,700,null);
		g.drawImage(portal, 2800+bgX, 327,120,150,null);
		g.drawImage(Ending, 0, endingY, null);
		
		if (isKeyCollected && energy > 0) {
		    g.setColor(Color.WHITE); // Set the color of the text
		    g.drawString(monsterName, 1730 + bgX, 40); // Draw the monster's name at the specified coordinates
		}
		if (!isKeyCollected) { // 키가 아직 먹히지 않았을 경우에만 키를 그림
	        g.drawImage(key, 1752 + bgX, 216, 35, 20, null);
	    }
		if (energy <= 0) {
	        statue = null;
	        statue2 = null;
	        portal = new ImageIcon("stage/유적 입구.png").getImage();
	        statue3 = new ImageIcon("stage/깨진 여신상.png").getImage();
	    }
	    	check();
	    	keyCheck();
	    	flamesCheck();
	    	flamesCheck2();
	    	flamesCheck3();
	    	flamesCheck4();
	    	flamesCheck5();
	    	flamesCheck6();
	    	Checkattack();
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
                	int newX = player.getX();
                    int newY = player.getY();
                    // X 좌표 조정
                    if (player.getX() < tileBoundary.getMinX()) {
                        newX = (int) tileBoundary.getMinX();
                    } else if (player.getX() + player.getWidth() > tileBoundary.getMaxX()) {
                        newX = (int) (tileBoundary.getMaxX() - player.getWidth());
                    }

                    // Y 좌표 조정
                    if (player.getY() < tileBoundary.getMinY()) {
                        newY = (int) tileBoundary.getMinY();
                    } else if (player.getY() + player.getHeight() > tileBoundary.getMaxY()) {
                        newY = (int) (tileBoundary.getMaxY() - player.getHeight());
                    }
                    // 조정된 위치로 설정
                    player.setX(newX);
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
	            keyEat = new ImageIcon("stage/key 먹음 표시.png").getImage();
	            statueSound(new File("Sound/여신상 웃음소리.wav")); // 여신상 웃음소리 효과음 재생
	            bgSound(new File("Sound/Ruins.wav"));
	            createFlame();      
	        }
			System.out.println("key!");
			statue2 = new ImageIcon("stage/여신상눈.png").getImage();
		}else {
			statue = new ImageIcon("stage/여신상.png").getImage();
		}
		if (energy <= 0) {
	        statue = null;
	        statue2 = null;
	        statue3 = new ImageIcon("stage/깨진 여신상.png").getImage();
	        stopBgSound();
	        if (!isShattered) {
	            shattering(new File("Sound/깨지는 소리.wav"));
	            isShattered = true;
	        }
	        //PortalChek();
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
	        		player.getPlayerHp().decreaseHp(50);
	                lastTime = System.currentTimeMillis(); // 충돌 시간 갱신
	                System.out.println("(flame2)몬스터와 충돌! 플레이어 체력: " + hp.getHp());
	            }   
	            System.out.println("아!"); // 충돌 시 수행할 동작   
	        }
	    }
	}
	public void flamesCheck3() {
	    Rectangle playerBox = player.getRect();    
	    for (Flame flame : flames) {
	        Rectangle flameBox = flame.getRect(2); // 각 불꽃 객체의 충돌 영역을 가져옴

	        if (playerBox.intersects(flameBox)) {
	        	if (System.currentTimeMillis() - lastTime > Delay) {
	        		player.getPlayerHp().decreaseHp(50);
	                lastTime = System.currentTimeMillis(); // 충돌 시간 갱신
	                System.out.println("(flame2)몬스터와 충돌! 플레이어 체력: " + hp.getHp());
	            }   
	            System.out.println("흥!"); // 충돌 시 수행할 동작   
	        }
	    }
	}public void flamesCheck4() {
	    Rectangle playerBox = player.getRect();	    
	    for (Flame flame : flames) {
	        Rectangle flameBox = flame.getRect(3); // 각 불꽃 객체의 충돌 영역을 가져옴

	        if (playerBox.intersects(flameBox)) {
	        	if (System.currentTimeMillis() - lastTime > Delay) {
	        		player.getPlayerHp().decreaseHp(50);
	                lastTime = System.currentTimeMillis(); // 충돌 시간 갱신
	                System.out.println("(flame2)몬스터와 충돌! 플레이어 체력: " + hp.getHp());
	            }   
	            System.out.println("아우치!"); // 충돌 시 수행할 동작   
	        }
	    }
	}
	public void flamesCheck5() {
	    Rectangle playerBox = player.getRect();    
	    for (Flame flame : flames) {
	        Rectangle flameBox = flame.getRect(4); // 각 불꽃 객체의 충돌 영역을 가져옴

	        if (playerBox.intersects(flameBox)) {
	        	if (System.currentTimeMillis() - lastTime > Delay) {
	        		player.getPlayerHp().decreaseHp(50);
	                lastTime = System.currentTimeMillis(); // 충돌 시간 갱신
	                System.out.println("(flame2)몬스터와 충돌! 플레이어 체력: " + hp.getHp());
	            }   
	            System.out.println("핫!"); // 충돌 시 수행할 동작   
	        }
	    }
	}
	public void flamesCheck6() {
	    Rectangle playerBox = player.getRect();  
	    for (Flame flame : flames) {
	        Rectangle flameBox = flame.getRect(5); // 각 불꽃 객체의 충돌 영역을 가져옴

	        if (playerBox.intersects(flameBox)) {
	        	if (System.currentTimeMillis() - lastTime > Delay) {
	        		player.getPlayerHp().decreaseHp(50);
	                lastTime = System.currentTimeMillis(); // 충돌 시간 갱신
	                System.out.println("(flame2)몬스터와 충돌! 플레이어 체력: " + hp.getHp());
	            }   
	            System.out.println("뜨거!"); // 충돌 시 수행할 동작   
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
		case KeyEvent.VK_UP:
			if(energy <= 0) {
				PortalChek();
			}
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
	private void shattering(File file) { // 여신상 웃음소리 효과음 메서드	
			try {				
				crackClip = AudioSystem.getClip();
				crackClip.open(AudioSystem.getAudioInputStream(file));
				crackClip.start();
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
	}
	private void bgSound(File file) { // 백그라운드 배경음악
		//statueSound(new File("Sound/Ruins.wav"));
	    try {
	        bgClip = AudioSystem.getClip();
	        bgClip.open(AudioSystem.getAudioInputStream(file));
	        bgClip.start();
	        if (energy <= 0) {
	            bgClip.stop();
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	private void stopBgSound() {
	    if (bgClip != null && bgClip.isRunning()) {
	        bgClip.stop();
	        System.out.println("Background music stopped.");
	    } else {
	       // System.out.println("No background music playing.");
	    }
	}

	public void createFlame() {
	    if (isKeyCollected && energy > 0) { // 키를 먹은 후에 에너지가 0보다 큰 경우에만 불꽃을 생성
	        Flame flame = new Flame(this);
	        flames.add(flame);
	    }
	}
	private void drawEnergyBar(Graphics g) {
	    if (energy > 0) {
	    	g.setColor(energyColor);
	        g.drawRect(1615 + bgX, 60, 300, 20);
	        g.fillRect(1615 + bgX, 60, energy * 3, 20);
	    }
	}
	public void decreaseEnergy() {
		if(this.energy >= 0)
		this.energy -= 10;
		else
		this.energy = 0;
	}
	public Rectangle BossRect() {
		return new Rectangle(1720 + bgX, 100, 100, 800);
	}
	public boolean Checkattack() {
	    boolean retValue = false;
	    if (isKeyCollected) { // 키를 먹은 후에만 여신상과 충돌 체크
	        for (Attack attack : player.getAttackList()) {
	            Rectangle bossBox = BossRect();
	            Rectangle attackBox = new Rectangle(attack.getX(), attack.getY(), 10, 10);
	            if (bossBox.intersects(attackBox)) {
	                if (System.currentTimeMillis() - lastTime > Delay) {
	                    lastTime = System.currentTimeMillis(); // 충돌 시간 갱신
	                    System.out.println("여신상 공격!");
	                    retValue = true;
	                    decreaseEnergy(); // 에너지 감소
	                }
	            }
	        }
	    }
	    return retValue;
	}
	public void PortalChek() {
		System.out.println("포탈");
		if (player != null) {
			Rectangle[] tileLine = { // 직사각형 타일 경계선 배열
					new Rectangle(2850 + bgX, 365, 35, 60)
			};
			Rectangle playerBox = player.getRect();
	        for (Rectangle tileBoundary : tileLine) {
	        	if (playerBox.intersects(tileBoundary)) { 
	        		statueSound(new File("Sound/Mae.wav"));
	        		setPortalActive(true);
	            } else {
	            	System.out.println("NO");
	            }
	        }
		 }
	}
	public void setPortalActive(boolean isActive) {
	    this.isPortalActive = isActive;
	}
	public boolean isPortalActive() {
        return isPortalActive;
    }
	public void drawFlame(int x, int y) {
	}
	public void taste() {
		if(isPortalActive()) {
			System.out.println("활성화");
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
}//
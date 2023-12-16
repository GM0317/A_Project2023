import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

public abstract class Stage {
	protected Image map;
	protected Image floor;
	//protected Image ledder;
	protected Image Tile;
	protected Image Tile2;
	protected Image Tile3;
	protected Image Tile4;
	protected Image Portal;
	protected Image monster;
	protected int bgX = 0;
	
	private Ledder ledder;
	private Tile tile;
	private Tile2 tile2;
	private Ending ending;
	
	private Monster1 m1;
	private Monster2 m2;
	private Monster3 m3;	
	protected int monsterX = 1000; // 몬스터의 초기 X 좌표
	protected int monsterY = 468; // 몬스터의 Y 좌표
	protected int width;
	protected int height;
	protected int monsterSpeed = 1; // 몬스터의 이동 속도
	protected boolean movingLeft = true; // 몬스터의 방향을 추적하는 플래그
	protected Image monsterImage = null;
	private LinkedList<Onbject> objectList = new LinkedList<>();
	private LinkedList<Monster> monsterList = new LinkedList<>();
	public Stage() {
		/*this.ledder = new Ledder(this);
		this.tile = new Tile(this);
		this.tile2 = new Tile2(this);
		objectList.add(ledder);
<<<<<<< HEAD
		objectList.add(tile);
		objectList.add(tile2);
=======
		objectList.add(tile);*/
		
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
	public int getBGX() {
		return bgX;
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
    	if(monsterImage == null)
    		return 0;
        return monsterImage.getWidth(null); // 몬스터 이미지의 너비 반환
    }
    public int getHeight() {
    	if(monsterImage == null)
    		return 0;
        return monsterImage.getHeight(null); // 몬스터 이미지의 높이 반환
    }
    
    
    public void checkMonster(Rectangle playerBox, int y, int initialY, int gravitySpeed) {
	    boolean isOnTile = false; // 캐릭터가 타일 위에 있는지 여부를 확인하는 플래그
	 // Stage2의 각 타일에 대한 직사각형 정의
	  /*  Rectangle[] tileLine = { // 직사각형 타일 경계선 배열
	        new Rectangle(600 + bgX, 400, 140, 40),
	        new Rectangle(800 + bgX, 300, 140, 40),
	        new Rectangle(1000 + bgX, 200, 410, 40),
	        new Rectangle(1500 + bgX, 300, 140, 40),
	        new Rectangle(1700 + bgX, 400, 140, 40)
	        // 필요에 따라 다른 타일의 직사각형 추가 가능
	    };
	    // 각 타일과의 충돌 확인
	    for (Rectangle tileBoundary : tileLine) {
	        if (playerBox.intersects(tileBoundary)) {
	            // 타일과의 충돌 감지
	            // 원하는 작업 수행
	            System.out.println("타일과 충돌 발생!");
	            // 여기서 필요한 작업 수행 가능
	            // 예를 들어, 플레이어의 위치 초기화 또는 특정 동작 수행 등
	        }
	    }
	    // 각 타일 경계와의 충돌 확인
	    for (Rectangle tileBoundary : tileLine) {
	        if (playerBox.intersects(tileBoundary)) {
	            // 타일과의 충돌 감지
	            isOnTile = true; // 캐릭터가 타일 위에 서있는 것으로 플래그 설정
	
	            // 캐릭터 위치를 타일의 윗 표면에 서 있는 위치로 조정
	            y = tileBoundary.y - height; // 캐릭터의 아래쪽을 타일의 윗쪽에 맞춤
	
	            // 필요한 경우 충돌 시 다른 동작 수행
	
	            break; // 이미 서 있는 타일이 있으면 다른 타일은 확인할 필요 없음
	        }
	    }
	    if (!isOnTile && y < initialY) {
	        // 중력이나 낙하 효과 적용
	        y += gravitySpeed; // 낙하를 위해 'y' 위치 조정
	        // 낙하 속도를 제어하기 위해 gravitySpeed 조절 가능
	    }*/
    }
    public abstract void drawBackground(Graphics g);
    public void drawObject(Graphics g) {
    	for(Onbject ob : objectList) {
			//ob.draw(g);
		}
    }
    public abstract void drawTile(Graphics g);
    public abstract void drawMonster(Graphics g);
	public void draw(Graphics g) {
		drawBackground(g);
		drawTile(g);
		drawObject(g);
		drawMonster(g);
		
	}
	public abstract void keyPressed(KeyEvent e);
}

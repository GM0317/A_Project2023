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

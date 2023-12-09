import java.awt.Graphics;
import java.awt.Image;
import java.util.LinkedList;

public abstract class Monster {
	protected int monX;
	protected int monY;
	protected int mWidth;
	protected int mHeight;
	private LinkedList<Monster> monsterList = new LinkedList<>();
	protected Monster1 m1;
	protected monster2 m2;
	protected monster3 m3;
	
	public Monster(int monX, int monY, int mWidth, int mHeight) {
	    this.monX = monX;
	    this.monY = monY;
	    this.mWidth = mWidth;
	    this.mHeight = mHeight;

	    /*this.m1 = new Monster1(10, 20, 30, 40); // 적절한 초기값을 넣어주세요
	    this.m2 = new monster2(50, 60, 70, 80); // 적절한 초기값을 넣어주세요
	    this.m3 = new monster3(90, 100, 110, 120); // 적절한 초기값을 넣어주세요*/
	}

	public Monster() {
		this.m1 = new Monster1();
		//this.m2 = new monster2();
		//this.m3 = new monster3();
		//monsterList.add(m1);
		monsterList.add(m2);
		monsterList.add(m3);
	}

	
	public int getX() {
		return monX;
	}
	
	public int getY() {
		return monY;
	}
	/*public int getWidth() {
		return getWidth(null); // 몬스터 이미지의 너비 반환
	}

    public int getHeight() {
    	return getHeight(null); // 몬스터 이미지의 높이 반환
	}*/
	public abstract void moveMonster();
	
	

}

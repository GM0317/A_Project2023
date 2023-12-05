import java.awt.Graphics;
import java.util.LinkedList;

public abstract class Monster {
	protected int monX;
	protected int monY;
	protected int monsterWidth;
	protected int monsterHeight;
	
	public Monster(int monX, int monY, int monsterWidth, int monsterHeight){ 
		this.monX=monX;
		this.monY=monY;
		this.monsterWidth=monsterWidth;
		this.monsterHeight=monsterHeight;
	}

	public int getX() {
		return monX;
	}
	
	public int getY() {
		return monY;
	}
	
	public abstract void draw(Graphics g);
	public abstract void moveMonster();
	
	

}

import java.awt.Graphics;

public abstract class Monster {
    protected int x;
    protected int y;
    protected int mWidth;
    protected int mHeight;

    public Monster(int x, int y, int mWidth, int mHeight) {
        this.x = x;
        this.y = y;
        this.mWidth = mWidth;
        this.mHeight = mHeight;
    }

    public abstract void moveMonster(int direction);
    public abstract void draw(Graphics g);
    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
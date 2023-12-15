import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Monster {
	protected int x;
	protected int y;
    protected int mWidth;
    protected int mHeight;
    protected Player player;
    private int Mhp;
    private long lastTime = 0; // 마지막 충돌 시간 저장
    private final long Delay = 2000; // 충돌 딜레이: 2초(2000ms)

    public Monster(Player play, int x, int y, int mWidth, int mHeight, int Mhp) {
        this.x = x;
        this.y = y;
        this.mWidth = mWidth;
        this.mHeight = mHeight;
        this.Mhp=Mhp;
        this.player = play;
        
    }

    public abstract void moveMonster(int direction);
    public abstract void draw(Graphics g, GameCanvas screen);
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    public int getHP() {
       return Mhp;
    }
    
    public boolean Checkmonster() {
       Rectangle playerBox = player.getRect();
       Rectangle monsterBox = new Rectangle(this.getX(), this.getY(), this.mWidth, this.mHeight);
       //Rectangle attackbox = new Rectangle(boom.getX(), boom.getY(), 10, 10);
       //System.out.println(this.getClass().getName()+", "+playerBox+", "+monsterBox+":"+playerBox.intersects(monsterBox));
        if (monsterBox.intersects(playerBox)) {
            if (System.currentTimeMillis() - lastTime > Delay) {
                player.getPlayerHp().decreaseHp(50); // 충돌 시 플레이어의 체력을 50 감소
                lastTime = System.currentTimeMillis(); // 충돌 시간 갱신
                System.out.println("몬스터와 충돌! 플레이어 체력: " + player.getPlayerHp().getHp()+"몬스터 체력 : " + Mhp);
                return true;
            }
        }
        return false;
    }
    
    public boolean Checkattack() {
        Rectangle monsterBox = new Rectangle(this.x, this.y, this.mWidth, this.mHeight);
        Rectangle attackbox = new Rectangle(player.getAttack().getX(), player.getAttack().getY(), 10, 10);
        //System.out.println("x:"+boom.getX()+", y:"+boom.getY() + monsterBox);
        
         if (monsterBox.intersects(attackbox)) {
             if (System.currentTimeMillis() - lastTime > Delay) {
                 DieMT(50); // 충돌 시 몬스터의 체력을 50 감소
                 lastTime = System.currentTimeMillis(); // 충돌 시간 갱신
                 System.out.println("몬스터 공격! 플레이어 체력: " + player.getPlayerHp().getHp()+"몬스터 체력 : " + Mhp);
                 return true;
             }
         }
         return false;
     }
    public void DieMT(int amount) { // Hp 감소시키기
        Mhp -= amount;
        if (Mhp < 0) {
            Mhp = 0;
            System.out.println(Mhp);
        }
    }
  
}
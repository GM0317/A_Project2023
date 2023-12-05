import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Stage3_M {
    private Image m3;
    private static int monsterX=300;  // 몬스터의 X 좌표 (static으로 변경)
    private static int monsterY=490;  // 몬스터의 Y 좌표 (static으로 변경)
    private static int monsterSpeed = 2;  // 몬스터의 이동 속도 (static으로 변경)
    private Player player;

    public Stage3_M() {
        this.player = player;
        
    }

    public void getMethod() {
        int playerX = player.getX();
        int playerY = player.getY();
        // 캐릭터의 위치 정보 사용
    }

    public void moveMonster() {
        if (monsterX < player.getX()) {
            monsterX += monsterSpeed;  // 몬스터를 오른쪽으로 이동
        } else if (monsterX > player.getX()) {
            monsterX -= monsterSpeed;  // 몬스터를 왼쪽으로 이동
        }
    }

    /*public static void main(String[] args) {
        Stage3_M stage3Monster = new Stage3_M(new Player());  // 플레이어 인스턴스를 생성하여 전달해줘야 합니다
        while (true) {
            stage3Monster.moveMonster();

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }*/
    public void draw(Graphics g) {
        g.drawImage(m3, monsterX, monsterY, 50, 50, null);
    }
}
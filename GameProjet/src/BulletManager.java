import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class BulletManager {
    private Stage stage;
    private Player player;
    private List<Boolean> isBulletActive; // 총알 활성화 여부를 저장할 리스트
    private List<Image> bullets; // 여러 개의 총알 이미지를 저장할 리스트
    private List<Integer> xList; // 총알의 x 좌표를 저장할 리스트
    private List<Integer> yList; // 총알의 y 좌표를 저장할 리스트
    private int numberOfBullets = 5; // 생성할 총알 개수
    private int bulletSpeed = 5; // 총알 이동 속도
    protected Image boom;

    public BulletManager(Stage stage, Player player) {
        this.stage = stage;
        this.player = player;
//        bullets = new ArrayList<>();
//        xList = new ArrayList<>();
//        yList = new ArrayList<>();
        isBulletActive = new ArrayList<>();
        for (int i = 0; i < numberOfBullets; i++) {
           bullets.add(boom);
           xList.add(player.getX() + 20);
           yList.add(player.getY() + 20);
           isBulletActive.add(false); // 처음에는 모든 총알이 비활성 상태
        }
        
        // 총알 이미지 로드 및 초기화
        try {
            boom = ImageIO.read(new File("character/총알.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 총알 리스트 초기화
        for (int i = 0; i < numberOfBullets; i++) {
           // bullets.add(boom);
            // 초기 총알 위치 설정 (플레이어 위치에 따라 다르게 조정 가능)
            xList.add(player.getX() + 20); // 예시 값, 조정 가능
            yList.add(player.getY() + 20); // 예시 값, 조정 가능
        }
    }

    public void shoot() {
        // 총알 이동 로직
       /* for (int i = 0; i < numberOfBullets; i++) {
            int x = xList.get(i);
            x += bulletSpeed; // 총알의 이동 로직 (예시로 오른쪽으로 이동)

            // 업데이트된 좌표 적용
            xList.set(i, x);
        }*/
     // A키를 누르면 활성화되지 않은 총알을 찾아 발사
        for (int i = 0; i < numberOfBullets; i++) {
            if (!isBulletActive.get(i)) {
                int x = xList.get(i);
                x += bulletSpeed;

                xList.set(i, x);
                isBulletActive.set(i, true); // 총알 발사됨
                break; // 한 발씩 발사하고 반복 종료
            }
        }
    }

    public void draw(Graphics g) {
       // 총알 이미지 그리기
        for (int i = 0; i < numberOfBullets; i++) {
            if (isBulletActive.get(i)) {
                Image bulletImage = bullets.get(i);
                int x = xList.get(i);
                int y = yList.get(i);
                g.drawImage(bulletImage, x, y, 50, 50, null);
            }
        }
        // 총알 이미지 그리기
      /*  for (int i = 0; i < numberOfBullets; i++) {
            Image bulletImage = bullets.get(i);
            int x = xList.get(i);
            int y = yList.get(i);
            g.drawImage(bulletImage, x, y, 50, 50, null);
        }*/
    }
}
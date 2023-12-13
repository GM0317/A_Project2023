import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.ImageIcon;

public class Flame{
	private Stage stage;
    private List<Image> flames; // 여러 개의 불꽃 이미지를 저장할 리스트
    private List<Integer> xList; // 불꽃의 x 좌표를 저장할 리스트
    private List<Integer> yList; // 불꽃의 y 좌표를 저장할 리스트
    private int numberOfFlames = 3; // 생성할 불꽃 개수

    public Flame(Stage stage) {
    	this.stage = stage;
        flames = new ArrayList<>();
        xList = new ArrayList<>();
        yList = new ArrayList<>();

        // 여러 개의 불꽃 생성 및 초기 위치 설정
        for (int i = 0; i < numberOfFlames; i++) {
            Image flameImage = new ImageIcon("stage/불꽃.png").getImage();
            flames.add(flameImage);

            Random rand = new Random();
            int x = rand.nextInt(3000); // 가로 위치 랜덤 설정
            int y = -100 - rand.nextInt(1000); // 세로 위치 랜덤 설정 (위에서 떨어지도록)

            xList.add(x);
            yList.add(y);
        }
    }
    public Rectangle getRect(int index) {
        int x = xList.get(index);
        int y = yList.get(index);
        return new Rectangle(x+ stage.getBGX(), y, 20, 20);
    }
    public void draw(Graphics g) {
        // 모든 불꽃 이미지를 그림
        for (int i = 0; i < numberOfFlames; i++) {
            Image flameImage = flames.get(i);
            int x = xList.get(i);
            int y = yList.get(i);

            g.drawImage(flameImage, x+ stage.getBGX(), y, 50, 70, null);
            g.setColor(Color.RED); // 예시: 빨간색으로 설정
            g.fillRect(x+ stage.getBGX(), y, 5, 5); // 사각형으로 불꽃을 그림

            // 불꽃의 움직임 업데이트
            y += 2; // 아래로 움직이게 설정
            yList.set(i, y); // 좌표 리스트 업데이트
        }
    }
}//sdfc

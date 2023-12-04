import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

public class GameScreen extends JFrame{
	
	public static final int WIDTH = 1000; // 너비 700
	public static final int HEIGHT = 600; // 높이 400
	public GameScreen() {
		setTitle("어드벤처 게임"); // 게임 타이틀
		setSize(WIDTH, HEIGHT); // 창 사이트 (너비, 높이)
		setResizable(false); // 창 조절할 수 없도록 창 크기 고정.
		setLocationRelativeTo(null); // 창 화면을 중아에 위치시켜놓음.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 프로그램을 완전히 꺼줌 (이거없으면 프로그램 꺼도 실행중임)
		//Player player = new Player(); // player에 있는 기능 불러오긷
		GameCanvas canvas = new GameCanvas();
		add(canvas);
		setVisible(true); // 창을 화면애 보여준다. (이거 없으면 창 안보임.)

	}
	public static void main(String[] args) {
        new GameStart();
    }

}

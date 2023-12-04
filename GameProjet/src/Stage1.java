import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Canvas;
import java.awt.Dimension;


import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Stage1 extends JFrame {
	private Graphics screenGraphic;
	private Image background;
	private Graphics bufferGraphics;
	private Image floor;
	
	private int monsterX = 300; // 몬스터의 초기 X 좌표
    private int monsterY = 440; // 몬스터의 Y 좌표
    private int width;
    private int height;
    private int monsterSpeed = 1; // 몬스터의 이동 속도
    private boolean movingLeft = true; // 몬스터의 방향을 추적하는 플래그
    private Image monsterImage;
	
    
	public Stage1(){
		setTitle("Stage1");
		setSize(GameScreen.WIDTH, GameScreen.HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null);
		setFocusable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		background = new ImageIcon("stage/stage1.png").getImage();
		floor = new ImageIcon("stage/stage1 바닥.png").getImage();
		monsterImage = new ImageIcon("rsc/모나피.png").getImage();
		setLayout(null);
		setVisible(true);
		
	}
	
	public void paint(Graphics g) {
		 super.paint(g);
	     g.drawImage(background, 0, 0, 3000, 600, this);
	     g.drawImage(floor, 0, 0, 3000, 600, this);
	}
}
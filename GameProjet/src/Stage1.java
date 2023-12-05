import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Stage1 extends JFrame {
	private Graphics screenGraphic;
	private Image background;
	private Graphics bufferGraphics;
	private Image floor;
	//private Monster1 monster1;

	public Stage1(){
		setTitle("Stage1");
		setSize(GameScreen.WIDTH, GameScreen.HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null);
		setFocusable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		background = new ImageIcon("stage/stage1.png").getImage();
		floor = new ImageIcon("stage/stage1 바닥.png").getImage();
		//monster1 = new Monster1();// Monster1 객체 생성
		setLayout(null);
		setVisible(true);
		
	}
	
	public void paint(Graphics g) {
		 super.paint(g);
	     g.drawImage(background, 0, 0, 3000, 600, this);
	     g.drawImage(floor, 0, 0, 3000, 600, this);
	     //monster1.draw(g); // Monster1 그리기
	}
	
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
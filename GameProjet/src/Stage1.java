import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.awt.Canvas;
import java.awt.Dimension;
<<<<<<< HEAD
=======
import java.awt.event.KeyEvent;

>>>>>>> branch 'main' of https://github.com/GM0317/A_Project2023.git
import javax.swing.ImageIcon;

<<<<<<< HEAD

public class Stage1 extends Stage {
=======
public class Stage1 extends JFrame {
	private Graphics screenGraphic;
>>>>>>> branch 'main' of https://github.com/GM0317/A_Project2023.git
	private Image background;
<<<<<<< HEAD
	private Image floor;
	private int bgX = 0;
	private LinkedList<Onbject> objectList = new LinkedList<>();
	public Stage1(){;
		background = new ImageIcon("rsc/스테이지1art.png").getImage();		
		floor = new ImageIcon("stage/stage1 바닥.png").getImage();
	}
	public void draw(Graphics g) {
		super.draw(g);
	     g.drawImage(background, bgX, 0, 3000, 600, null);
	     g.drawImage(floor, bgX, 0, 3500, 560, null);
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		switch(e.getKeyCode())
		{
		case KeyEvent.VK_LEFT:
			bgX += 10;
			break;
		case KeyEvent.VK_RIGHT:
			bgX -= 10;
			break;
		}
	}
	@Override
	public void drawBackground(Graphics g) {
		// TODO Auto-generated method stub
=======
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
>>>>>>> branch 'main' of https://github.com/GM0317/A_Project2023.git
		
	}
<<<<<<< HEAD
	@Override
	public void drawTile(Graphics g) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void drawMonster(Graphics g) {
=======
	
	public void paint(Graphics g) {
		 super.paint(g);
	     g.drawImage(background, 0, 0, 3000, 600, this);
	     g.drawImage(floor, 0, 0, 3000, 600, this);
	     //monster1.draw(g); // Monster1 그리기
	}
	
	public void keyPressed(KeyEvent e) {
>>>>>>> branch 'main' of https://github.com/GM0317/A_Project2023.git
		// TODO Auto-generated method stub
		
	}
}
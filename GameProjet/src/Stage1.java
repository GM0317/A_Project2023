import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.ImageIcon;

public class Stage1 extends Stage {
	private Image background;
	private Image floor;
	private int bgX = 0;
	private LinkedList<Onbject> objectList = new LinkedList<>();
	private LinkedList<Monster> monsterList = new LinkedList<>();
	public Stage1(){;
		background = new ImageIcon("rsc/스테이지1art.png").getImage();		
		floor = new ImageIcon("stage/stage1 바닥.png").getImage();
	}
	public void draw(Graphics g) {
		super.draw(g);
	     g.drawImage(background, bgX, 0, 3000, 600, null);
	     g.drawImage(floor, bgX, 0, 3500, 560, null);
	}
	
	public Stage1(LinkedList<Stage1> monsterList) {
		
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
		
	}
	@Override
	public void drawTile(Graphics g) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void drawMonster(Graphics g) {
	}
	
	public void paint(Graphics g) {
		 //super.paint(g);
	     //g.drawImage(background, 0, 0, 3000, 600, this);
	     //g.drawImage(floor, 0, 0, 3000, 600, this);
	     //monster1.draw(g); // Monster1 그리기
	}
	
}
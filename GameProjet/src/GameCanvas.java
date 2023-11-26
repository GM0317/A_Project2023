import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ComponentListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.ComponentEvent;
import javax.swing.JPanel;

public class GameCanvas extends JPanel implements ComponentListener{
	private static final long serialVersionUID = 5203228742370884076L;
	private Graphics buffer;
	private Image offScreen;
	private Dimension dim;
	private Player player; // GameCanvas 클래스의 인스턴스에 접근하기 위한 참조
	private PlayerHp hp;
	private Stage2 sg2;
	private Stage1 sg1;
	private Stage2Monster monster2;
	private Player step = new Player();
	private int countNumber = 0;
	public GameCanvas(Player player) {
		addKeyListener(player);
		addComponentListener(this);
		addKeyListener(step);
		setFocusable(true);	 //키를 눌렀을 때 동작이 되도록해줌.
		requestFocus(); //키를 눌렀을 때 동작이 되도록해줌.
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {		
			@Override
			public void run() {
				// TODO Auto-generated method stub
				repaint();
				counting();
			}
		}, 0, 1);
		this.player=player;
		this.hp = new PlayerHp(player); // hp 초기화
		this.sg2 = new Stage2();
		this.monster2 = new Stage2Monster();
		setLayout(new GridLayout(1, 1)); // 예시로 GridLayout을 사용하여 한 개의 컴포넌트만 추가할 때
        //add(player);	
	}
	public void counting() {
		this.countNumber++;
	}
	public int getCount() {
		return this.countNumber;
	}
	@Override
	public void paint(Graphics g) {
	    initBuffer(); //Offscreen buffer 초기화
	    buffer.clearRect(0, 0, dim.width, dim.height);
	    sg2.draw(buffer); // stage2 그리기
	    hp.draw(buffer);  // HP 이미지 그리기
	    monster2.draw(buffer);
	    step.draw(buffer, this); //player 그리기
	    g.drawImage(this.offScreen, 0, 0, this);
	    
	    // 사각형 경계선 그리기
	    g.setColor(Color.RED);
	    int rectX = 600;
	    int rectY = 400;
	    int rectWidth = 140;
	    int rectHeight = 40;
	    // 바닥 경계선 
	    g.setColor(Color.RED);
	    int rectX1 = 635;
	    int rectY2 = 400;
	    int rectWidth3 = 35;
	    int rectHeight4 = 130;
	    // drawRect() 메서드를 사용하여 사각형의 경계선 그리기
	    g.drawRect(rectX, rectY, rectWidth, rectHeight);
	    g.drawRect(rectX1, rectY2, rectWidth3, rectHeight4);
	    
	    repaint();
	}
	private void initBuffer() {
		this.dim = getSize();
		this.offScreen = createImage(dim.width, dim.height);
		this.buffer = this.offScreen.getGraphics();
	}
	@Override
	public void update(Graphics g) {
		paint(g);
	}
	@Override
	public void componentResized(ComponentEvent e) {
		// TODO Auto-generated method stub
		initBuffer();
	}
	@Override
	public void componentMoved(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}

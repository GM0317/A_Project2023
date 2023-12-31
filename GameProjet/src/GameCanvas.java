import java.awt.Color;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ComponentListener;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.ComponentEvent;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class GameCanvas extends JPanel implements ComponentListener{
	private static final long serialVersionUID = 5203228742370884076L;
	private Graphics buffer;
	private Image offScreen;
	private Dimension dim;
	private Stage2 sg2;
	private Stage1 sg1;
	private Stage3 sg3;
	private Stage4 sg4;
	private Ruins ruins;
	private Attack attack;
	private Player step;
	private GameOver gaemOver;
	private GameScreen gameScreen;
	private Ending endings;
	private int countNumber = 0;
	private LinkedList<Stage> stageList = new LinkedList<>();
	public GameCanvas() {	
		this.step = new Player();
		this.sg1 = new Stage1(step,this,gaemOver);
		this.sg2 = new Stage2(step,this);
		this.sg3 = new Stage3(step,this);
		this.sg4 = new Stage4(step, ruins, this);
		this.ruins = new Ruins(step, this);
		stageList.add(ruins);
		stageList.add(sg1);
		stageList.add(sg2);
		stageList.add(sg3);
		stageList.add(sg4);
		this.step.setStage(stageList.get(1));
		ruins.setPlaer(step);
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
		setLayout(new GridLayout(1, 1)); // 예시로 GridLayout을 사용하여 한 개의 컴포넌트만 추가할 때
		changeStage(3);
		if(ruins.isPortalActive()) {
			
		}
	}
	public void changeStage(int num) {
		this.step.setStage(stageList.get(num));
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
	   // buffer.clearRect(0, 0, dim.width, dim.height);
	    step.draw(buffer, this); //player 그리기
	    g.drawImage(this.offScreen, 0, 0, this);
	    sg2.moveMonster();// 몬스터 호출
	    if(step.getPlayerHp().getHp()==0) {//만약 hp가 0이면 게임 over 화면 출력
       	 GameOver gameOver = new GameOver(step);
            gameOver.showGameOver();
            gameOver.draw(g);
       }
	   if(ruins.isPortalActive()) {
	    	Ending ending = new Ending(ruins);
       	 	ending.showEnding();
       	 	ending.draw(g);  	
       	 	
	    }     	
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
	private void statueSound(File file) { // 여신상 웃음소리 효과음 메서드
		Clip clip = null;
		try {
		clip = AudioSystem.getClip();
		clip.open(AudioSystem.getAudioInputStream(file));
		clip.start();
		} catch (Exception e) {
		e.printStackTrace();
		}
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

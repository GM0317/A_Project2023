import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Canvas;
import java.awt.Dimension;


import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Stage1 extends JFrame {
	private Image screenImage;
	private Graphics screenGraphic;
	private Image background;
	private Image bufferImage;
	private Graphics bufferGraphics;
	private Image offScreen;
	private Player player = new Player();	
	private Monster1 monster1 = new Monster1();
	
	public Stage1(){
		setTitle("Stage1");
		setSize(GameScreen.WIDTH, GameScreen.HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null);
		setFocusable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		background = new ImageIcon("rsc/스테이지1art.png").getImage();
		setLayout(null);
		addKeyListener(player);
		setVisible(true);

		this.monster1 = new Monster1();
	}
	
	public void paint(Graphics g) {
		 super.paint(g);
		 g.drawImage(this.offScreen, 0, 0, this);
	     g.drawImage(background, 0, 0, 2000, 600, this);
	}
}
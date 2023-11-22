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
	private Player ryu = new Player();	
	
	public Stage1(){
		setTitle("Stage1");
		setSize(GameScreen.WIDTH, GameScreen.HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null);
		setFocusable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		background = new ImageIcon("rsc/스테이지1art.png").getImage();
		setLayout(null);
		addKeyListener(ryu);
		setVisible(true);
		
		
	}
	/*
	private BufferedImage resizeImage(BufferedImage image, int newWidth) {
		int imageWidth = image.getWidth(null);
        int imageHeight = image.getHeight(null);

        double ratio = (double)newWidth/(double)imageWidth;
        int w = (int)(imageWidth * ratio);
        int h = (int)(imageHeight * ratio);
		Image resizeImage = image.getScaledInstance(w, h, Image.SCALE_SMOOTH);
		 
        // 새 이미지  저장하기
        BufferedImage newImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics g = newImage.getGraphics();
        g.drawImage(resizeImage, 0, 0, null);
        g.dispose();
        return newImage;
        */
	public void paint(Graphics g) {
		 super.paint(g);
		 g.drawImage(this.offScreen, 0, 0, this);
	     g.drawImage(background, 0, 0, GameScreen.WIDTH, GameScreen.HEIGHT, this);
	}
}
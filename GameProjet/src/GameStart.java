import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;



public class GameStart extends JFrame {
	private Image screenImage;
	private Graphics screenGraphic;
	private Image background;
	private JButton startButton;
	private JButton finshButton;
	
	private Image bufferImage;
	
	private Image mainScreen = new ImageIcon("rsc/map.png").getImage();
	
	public GameStart() {
		setTitle("Unknown Island");
		setSize(GameScreen.WIDTH, GameScreen.HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        background = new ImageIcon("rsc/map.png").getImage();
		setLayout(null);
		
        // 게임시작버튼
        startButton = new JButton("Start Game");
        startButton.setBounds(400, 300, 200, 50);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 버튼클릭시
                JOptionPane.showMessageDialog(null, "Game Started!");
            }
        });
        add(startButton);

        //게임종료
        finshButton = new JButton("finsh Game");
        finshButton.setBounds(400, 400, 200, 50);
        finshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Game finsh!");
            }
        });
        add(finshButton);

		
		setVisible(true);
		
	}
	public void paint(Graphics g) {
		 super.paint(g);
	     g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
		/*
		bufferImage = createImage(GameScreen.WIDTH, GameScreen.HEIGHT);
		screenGraphic = bufferImage.getGraphics();
		screenDraw(screenGraphic);
		g.drawImage(background, 0, 0, 1000, 600, this);*/
	}
	/*
	public void screenDraw(Graphics g) {
		g.drawImage(background, 0, 0, 1000, 600, this);
		this.repaint();
	}
	*/
}


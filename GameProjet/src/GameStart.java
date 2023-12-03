import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.Image;
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
	
	private Image mainScreen = new ImageIcon("rsc/startimg.png").getImage();
	
	public GameStart() {
		setTitle("Unknown Island");
		setSize(GameScreen.WIDTH, GameScreen.HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		background = mainScreen;
		setLayout(null);
		
        // 게임시작버튼
        startButton = new JButton("Start Game");
        startButton.setBounds(400, 300, 200, 50);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	dispose();
                // 버튼클릭시
            	if(e.getSource()== startButton) {
                	new Stage2();
                }
            }
        });
        add(startButton);

        //게임종료
        finshButton = new JButton("finsh Game");
        finshButton.setBounds(400, 400, 200, 50);
        finshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	dispose();
            }
        });
        add(finshButton);
	
        
		setVisible(true);
		
	}
	
    
	public void paint(Graphics g) {
		 super.paint(g);
	     g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
	}
}


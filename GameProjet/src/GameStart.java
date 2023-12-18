import java.io.File;
import java.io.BufferedInputStream;
import java.io.FileInputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;


import javax.swing.JFrame;
public class GameStart extends JFrame {
	
    private Image background = new ImageIcon("rsc/Startback.png").getImage();
    private Image startButton = new ImageIcon("rsc/STARTbt.png").getImage();
    private Image finishButton = new ImageIcon("rsc/EXITbt.png").getImage();
    private Image Gamename = new ImageIcon("rsc/GameName.png").getImage();
    private Clip bgClip;
    private Clip crackClip;
    
    private static boolean backgroundMusicPlayed = false;
    private Clip clip;
    
    public GameStart() {
        setTitle("Unknown Island");
        setSize(1050, 650);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setVisible(true);
        
        
        bgSound(new File("Sound/gamestart.wav"));
       // bgSound();
       
       
     // 게임 시작 버튼
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int mouseX = e.getX();
                int mouseY = e.getY();
                
                // Image startButton이 마우스 클릭 범위에 포함되어 있는지 확인
                if (mouseX >= 384 && mouseX <= 664 && mouseY >= 451 && mouseY <= 531) {
                    statueSound(new File("Sound/시작화면버튼.wav"));
                    // Stage1 클래스의 화면 보이도록 호출
                    new GameScreen();
                    stopBgSound();
                    // 현재 화면은 종료
                    dispose();
                }

            }
        });
     // 게임 종료 버튼
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int mouseX = e.getX();
                int mouseY = e.getY();
                
                // Image finishButton이 마우스 클릭 범위에 포함되어 있는지 확인
                if (mouseX >= 384 && mouseX <= 664 && mouseY >= 530 && mouseY <= 610) {
                	statueSound(new File("Sound/시작화면버튼.wav"));
                	// 현재 화면은 종료
                    dispose();
                }
            }
        });
    }
    private void statueSound(File file) {
		Clip clip = null;
		try {
		clip = AudioSystem.getClip();
		clip.open(AudioSystem.getAudioInputStream(file));
		clip.start();
		} catch (Exception e) {
		e.printStackTrace();
		}
	}
    private void bgSound(File file) { // 백그라운드 배경음악
        try {
            bgClip = AudioSystem.getClip();
            bgClip.open(AudioSystem.getAudioInputStream(file));
            bgClip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void stopBgSound() {
	    if (bgClip != null && bgClip.isRunning()) {
	        bgClip.stop();
	        System.out.println("Background music stopped.");
	    } else {
	        System.out.println("No background music playing.");
	    }
	}
    
	private boolean isClassWindowOpen() {
	    return true; // 예시로 임의로 true 반환
	}
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(background, 0, 0, 1050, 650, this);
        g.drawImage(startButton, 384, 451, 280, 80, this);
        g.drawImage(finishButton, 384, 530, 280, 80, this);
        g.drawImage(Gamename, 240, 30, 580, 142, this);
        
    }
}

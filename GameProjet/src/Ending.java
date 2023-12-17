import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Timer;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Ending extends JPanel implements ActionListener{
    protected Image Ending;
    protected Ruins ruins;
    private boolean showEnding;
    private int x = 0;  // 이미지의 x 좌표
    private Timer timer;
    private static double deltaY = 0.5;
    protected static int y;
    private Clip bgClip;
    private Clip crackClip;

    public Ending(Ruins ruins) {
        this.ruins = ruins;
        Ending = new ImageIcon("Ending/엔딩 크레딩 수정.png").getImage();
        
    }

    public void moveImage() {
        if (showEnding && y > -1840) {
            y--;
            repaint();
            try {
                Thread.sleep(10); // 30밀리초마다 슬립
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } 
    }
    public void draw(Graphics g) {
        moveImage();
        g.drawImage(Ending, 0, y, null);
       
    }

    public void showEnding() {
        showEnding = true;
        moveImage();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
    }
    public void playe() {
        bgSound(new File("Sound/Mae.wav"));
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
}//

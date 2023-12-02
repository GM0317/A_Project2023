//import java.awt.Color;
//import java.awt.Graphics;
//import java.awt.Image;
//import java.awt.Rectangle;
//import java.awt.event.KeyEvent;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.IOException;
//
//import javax.imageio.ImageIO;
//import javax.swing.ImageIcon;
//
//public class Stage2Monster {
//    private Image monster;
//    private Player player;
//    private Stage2 stage2;
//    private State[] states;
//    private int stateIdx = 0;
//    private int x = 300;
//    private int y = 430;
//    private int bgX = 0;
//
//    public Stage2Monster() {
//    	stage2 = new Stage2();
//        monster = new ImageIcon("rsc/monster2기본.png").getImage();
//    }
//
//    public void draw(Graphics g) {
//        g.drawImage(monster, x + bgX, y, 150, 150, null); // monster 이미지를 현재 x, y 위치에 그림
//    }
//
//    public int getX() {
//        return x+bgX;
//    }
//
//    public void setX(int x) {
//        this.x = x+bgX;
//    }
//
//    public int getY() {
//        return y;
//    }
//
//    public void setY(int y) {
//        this.y = y;
//    }
//
//    public int getWidth() {
//        return monster.getWidth(null); // 몬스터 이미지의 너비 반환
//    }
//
//    public int getHeight() {
//        return monster.getHeight(null); // 몬스터 이미지의 높이 반환
//    }
//    public void keyPressed(KeyEvent e) {
//		// TODO Auto-generated method stub
//		switch(e.getKeyCode())
//		{
//		case KeyEvent.VK_LEFT:
//			bgX += 6;
//			break;
//		case KeyEvent.VK_RIGHT:
//			bgX -= 6;
//			break;
//		}
//	}
//}

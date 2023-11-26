import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Stage2Monster {
    private Image monster;
    private Player player;
    private State[] states;
    private int stateIdx = 0;
    private int x = 300;
    private int y = 430;

    public Stage2Monster() {
        monster = new ImageIcon("rsc/monster2기본.png").getImage();
    }

    public void draw(Graphics g) {
        g.drawImage(monster, x, y, 150, 150, null); // monster 이미지를 현재 x, y 위치에 그림
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return monster.getWidth(null); // 몬스터 이미지의 너비 반환
    }

    public int getHeight() {
        return monster.getHeight(null); // 몬스터 이미지의 높이 반환
    }
}

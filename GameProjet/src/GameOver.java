import java.awt.Color;
import java.awt.Graphics;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.JFrame;

public class GameOver extends JPanel {
    private Player player;
    protected Image GameOverImage;
    protected Image blackImage;
    private boolean gameOverShown;
    protected Image Ending;
	protected Ruins ruins;
    private GameScreen gameScreen;
    private String text = "다시 시작은 up키를 누르세요..";
    public GameOver(Player player) {
        this.player = player;
        this.gameOverShown = false;
        this.blackImage = new ImageIcon("stage/검은 바탕.png").getImage();
        this.GameOverImage = new ImageIcon("stage/GameOver.png").getImage();
		setFocusable(true);	 //키를 눌렀을 때 동작이 되도록해줌.
		requestFocus(); //키를 눌렀을 때 동작이 되도록해줌.
    }

    public void draw(Graphics g) {
        if (gameOverShown) {
            g.drawImage(blackImage, 0, 0, 3000, 600, null);
            g.drawImage(GameOverImage, 0, 0, null);
            g.setColor(Color.WHITE); // Set the color of the text
		    g.drawString(text, 425, 396);
        }
    }

    public void showGameOver() {
    	 if (player.getPlayerHp().getHp() <= 0) {
             gameOverShown = true;
             //System.out.println("Game Over");
         }
    }
    
}

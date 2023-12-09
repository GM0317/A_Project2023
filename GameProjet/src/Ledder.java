import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Ledder extends Onbject{
	protected Image ledder;
	protected Rectangle[] collisionBoxes;
	public Ledder(Stage stage) {
		super(stage);
		ledder = new ImageIcon("stage/Ladder1.png").getImage();
	}
	public void draw(Graphics g) {
		//사다리
	    g.drawImage(ledder, 635 + stage.getBGX(), 430, 35, 40, null);
	    g.drawImage(ledder, 635 + stage.getBGX(), 460, 35, 40, null);
	    g.drawImage(ledder, 635 + stage.getBGX(), 490, 35, 40, null);
	    g.drawImage(ledder, 635 + stage.getBGX(), 400, 35, 40, null);
	    
	    g.drawImage(ledder, 1760 + stage.getBGX(), 430, 35, 40, null);
	    g.drawImage(ledder, 1760 + stage.getBGX(), 460, 35, 40, null);
	    g.drawImage(ledder, 1760 + stage.getBGX(), 490, 35, 40, null);
	    g.drawImage(ledder, 1760 + stage.getBGX(), 400, 35, 40, null);
	    
	    g.setColor(Color.RED);
	    int ladderX = 635 + stage.getBGX();
	    int ladderY = 400;
	    int ladderWidth = 35;
	    int ladderHeight = 130;
	    
	    g.setColor(Color.RED);
	    int ladderX2 = 1760 + stage.getBGX();
	    int ladderY2 = 400;
	    int ladderWidth2 = 35;
	    int ladderHeight2 = 130;
	    
	    g.drawRect(ladderX, ladderY, ladderWidth, ladderHeight); //사다리 경계선
	    g.drawRect(ladderX2, ladderY2, ladderWidth2, ladderHeight2);	
	}
	public void ledderRect() {
	
		int rectX;
		int rectY;
		int rectWidth = 35;
		int rectHeight = 160;
	}
	public void rectCheck() {
		
	}
}

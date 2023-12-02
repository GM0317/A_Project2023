import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Stage3 {
   private Image map;
   private Image floor;
   public Stage3() {
      map = new ImageIcon("stage/stage3.png").getImage();
      floor = new ImageIcon("stage/stage Floor.png").getImage();
   }
   public void draw(Graphics g) {
      g.drawImage(map, 0, 0, 3500, 600, null);
      g.drawImage(floor, 0, 0, 3500, 570, null);
   }
}
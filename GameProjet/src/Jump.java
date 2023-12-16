public class Jump extends Thread  {
    private Player player;
    private Stage stage;
    private int jumpHeight; //높이 제한

    public Jump(Player player, int jumpHeight) {
        this.player = player;
        this.jumpHeight = jumpHeight;
    }

    @Override
    public void run() {
    	int initialY = player.getY();
    	player.setJump(true);
        int Y = player.getY();
        int X = player.getX();
        //this.jumpHeight = Y - this.jumpHeight;
        //System.out.println("jumpHeight: "+jumpHeight+", y: "+Y);
        final int MoveX = 1; // 앞으로 가는 길이
        final int gravity = 2; // 중력을 낮춤으로써 천천히 점프 및 낙하되도록 설정
        
        for (int i=0; i<jumpHeight; i++) {
           Y = Y-gravity;
           if(player.isFlip()) {
              X = X-MoveX;
           }
           else {
              X = X+MoveX;
           }
           player.setY(Y);
           player.setX(X);
           try {
                Thread.sleep(5); // 20ms마다 증가하도록 수정하여 천천히 올라가게 함
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
           //System.out.println("jumpHeight: "+jumpHeight+", y: "+Y);
        }
        
        for (int i=0; i<jumpHeight; i++) {
           Y = Y+gravity;
           if(player.isFlip()) {
              X = X-MoveX;
           }
           else {
              X = X+MoveX;
           }
           player.setX(X);
           //player.setY(Y);
           try {
                Thread.sleep(5); // 20ms마다 증가하도록 수정하여 천천히 올라가게 함
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        player.setJump(false);
   }  
}
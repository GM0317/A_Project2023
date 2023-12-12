/*public class Jump extends Thread  {
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
        }
      /*  for (int i=0; i<jumpHeight; i++) {
           Y = Y+gravity;
           if(player.isFlip()) {
              X = X-MoveX;
           }
           else {
              X = X+MoveX;
           }
           player.setX(X);
           player.setY(Y);
           try {
                Thread.sleep(5); // 20ms마다 증가하도록 수정하여 천천히 올라가게 함
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        player.setJump(false);*/
  // }  
//}*/
public class Jump extends Thread {
    private Player player;

    public Jump(Player player) {
        this.player = player;
    }

    @Override
    public void run() {
        int initialY = player.getY();
        final int jumpHeight = 100; // 점프 높이 제한
        final int gravity = 2; // 중력
        int Y = player.getY();
        int X = player.getX();
        final int MoveX = 1; // 앞으로 가는 길이

        player.setJump(true);

        // 캐릭터가 jumpHeight만큼 점프하는 로직
        for (int i = 0; i < jumpHeight; i += gravity) {
           // int Y = player.getY() - gravity;
            Y = Y-gravity;
            if (player.isFlip()) {
            	 X = X-MoveX;
                // 좌우 이동 로직 적용
            } else {
            	 X = X+MoveX;
                // 좌우 이동 로직 적용
            }
            player.setY(Y);
            player.setX(X);

            try {
                Thread.sleep(5); // 점프 중 캐릭터를 20ms마다 이동시켜 천천히 올라가게 함
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 캐릭터가 초기 Y 좌표로 낙하하는 로직
        int newY = initialY; // 초기 Y 좌표로 설정
        while (newY < initialY) {
            newY += gravity; // 중력 값을 적용하여 Y 좌표를 증가시킴
            player.setY(newY);

            try {
                Thread.sleep(5); // 5ms마다 증가하도록 수정하여 천천히 내려오게 함
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        player.setJump(false); // 점프 종료
    }
}

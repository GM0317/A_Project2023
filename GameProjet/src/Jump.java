

public class Jump extends Thread {
    private Player player; // player 클래스의 인스턴스에 접근

    public Jump(Player player) {
        this.player = player;
    }

    @Override
    public void run() {
    	final int jumpHeight = 250; //높이 제한
        final int jumpSpeed = 2; // 점프 속도, 좀 더 자연스러운 점프 구현
        System.out.println("위로 점프");
        System.out.println("x: "+player.x);
        System.out.println("y:"+player.y);
        int originalY = 490; // 캐릭터의 초기 y 좌표 저장
        for (int i = 0; i < jumpHeight; i += jumpSpeed) {
            player.setY(originalY - i); // 캐릭터의 y 좌표 설정
            player.repaint(); // 화면 다시 그리기

            try {
                Thread.sleep(5); // 잠시 대기 시간
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (int i = jumpHeight; i > 0; i -= jumpSpeed) {
            player.setY(originalY - i); // 캐릭터의 y 좌표 설정
            player.repaint(); // 화면 다시 그리기

            try {
                Thread.sleep(5); // 잠시 대기 시간
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        player.setY(originalY); // 초기 y 좌표로 복귀
        player.setJump(false); // 점프 상태 변경
        player.repaint(); // 화면 다시 그리기
     
       // player.y = 300; // Y좌표 고정
    }
}

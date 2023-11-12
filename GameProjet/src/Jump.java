

public class Jump extends Thread {
    private int jumpHeight = 0; // 점프 높이
    private GameCanvas canvas; // GameCanvas 클래스의 인스턴스에 접근하기 위한 참조

    public Jump(GameCanvas canvas) {
        this.canvas = canvas;
    }

    @Override
    public void run() {
        System.out.println("위로 점프");
        // up : sleep(5)
        for (int i = 0; i < 50; i++) {
            canvas.y = canvas.y - 3; // GameCanvas 클래스의 y 변수 업데이트
            jumpHeight += 3;
            canvas.setLocation(canvas.x, canvas.y); // GameCanvas의 위치 업데이트

            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //down : sleep(3)
        for (int i = 0; i < 50; i++) {
            canvas.y = canvas.y + 3; // GameCanvas 클래스의 y 변수 업데이트
            jumpHeight -= 3;
            canvas.setLocation(canvas.x, canvas.y); // GameCanvas의 위치 업데이트

            try {
                Thread.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        canvas.setJump(false); // GameCanvas의 점프 상태 업데이트
    }
}
//
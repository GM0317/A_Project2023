import java.awt.Graphics;
import java.util.ArrayList;

public class Stage extends Thread{
	private GameCanvas gameCanvas;
	private ArrayList<Monster> monList = new ArrayList<>(); //몬스터 담을 리스트
    public static boolean stageClear = false;
    public static boolean eatGrape = false;
    
    // Stage 클래스의 생성자에 gameCanvas 객체를 전달받도록함.
    public Stage(GameCanvas gameCanvas) {
        this.gameCanvas = gameCanvas;
    }
    
    public void makeMons(Monster monster) {
        monList.add(monster);
    }
    
    public void drawMons(Graphics g) {
        for (int i = 0; i < monList.size(); i++) {
            Monster monster = monList.get(i);
            g.drawImage(monster.getNowState(), monster.getX(), monster.getY(), null);
        }
    }

    public ArrayList<Monster> getMonsters() {
        return monList;
    }

    public void resetMonsters() {
        monList.clear();
        // 초기 위치에 몬스터 생성 및 시작
        Monster m1 = new Monster(109, 270, 109, 485);
        Monster m2 = new Monster(643, 270, 643, 1019);
        Monster m3 = new Monster(856, 270, 856, 1232);
        monList.add(m1);
        monList.add(m2);
        monList.add(m3);
        for (Monster monster : monList) {
            monster.start();
        }
    }
 // Stage 클래스에서 충돌을 검사하는 메서드를 추가합니다.
    public void checkCollisions() {
        ArrayList<Monster> monsters = getMonsters();
        for (int i = 0; i < monsters.size(); i++) {
            Monster monster = monsters.get(i);
            int monsterX = monster.getX();
            int monsterY = monster.getY();
        }
    }
    @Override
    public void run() {
        // 스테이지 초기 설정
        resetMonsters();
    }

}

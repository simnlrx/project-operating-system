package LoadRunner.thread;

import LoadRunner.game.Scene;
import LoadRunner.handler.GameManager;

public class BlockBreakThread extends Thread {

    private GameManager gameManager;
    private int x, y;

    public BlockBreakThread(GameManager gameManager, int x, int y) {
        this.gameManager = gameManager;
        this.x = x;
        this.y = y;
    }

    @Override
    public void run() {
        try {
            Scene scene = gameManager.getScene();
            scene.setValuePosition(x, y, 14);
            sleep(4000);
            scene.setValuePosition(x, y, 2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}

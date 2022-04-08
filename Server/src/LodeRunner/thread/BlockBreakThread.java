package LodeRunner.thread;

import LodeRunner.game.Scene;
import LodeRunner.handler.GameManager;

public class BlockBreakThread extends Thread {

    private GameManager gameManager;
    private int x, y;

    /*
     * Constructeur d'un joueur
     * @param gameManager GameManager
     * @param int x coorodnnée en x du bloc
     * @param int y coodronnée en y du bloc
     */

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

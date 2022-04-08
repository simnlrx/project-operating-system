package LodeRunner.thread;

import LodeRunner.game.Scene;
import LodeRunner.handler.GameManager;
import LodeRunner.handler.GameState;

public class RefreshScene extends Thread {
    private final Scene scene;
    // scene de jeu
    private final GameManager gameManager;
    // GameManager

    /*
     * Constructeur de RefreshScene
     * @param GameManager gameManager
     */

    public RefreshScene(GameManager gameManager) {
        this.scene = gameManager.getScene();
        this.gameManager = gameManager;
    }


    @Override
    public void run() {
        try {
            while (gameManager.getGameState().isGame()) {
                if (gameManager.isServer() || gameManager.getGameState().equals(GameState.SOLOGAME)) {
                    scene.matrix2Screen();
                    sleep(100);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

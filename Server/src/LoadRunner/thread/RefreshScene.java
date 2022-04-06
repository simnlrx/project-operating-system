package LoadRunner.thread;

import LoadRunner.game.Scene;
import LoadRunner.handler.GameManager;

public class RefreshScene extends Thread {
    private Scene scene;
    // scene de jeu
    private GameManager gameManager;
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
                scene.matrix2Screen();
                this.sleep(1000);
              }
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
    }
}

package LoadRunner.thread;

import LoadRunner.game.Scene;
import LoadRunner.handler.GameManager;

public class RefreshScene extends Thread {
    private Scene scene;
    private GameManager gameManager;

    public RefreshScene(GameManager gameManager) {
        this.scene = gameManager.getScene();
        this.gameManager = gameManager;
    }

    @Override
    public void run() {
        while (gameManager.getGameState().isGame()) {
            scene.printMatrix();
            try {
                this.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

package LoadRunner.thread;
import java.util.concurrent.Semaphore;

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
            try {
              while (gameManager.getGameState().isGame()) {
                scene.matrix2Screen();
                this.sleep(800);
              }
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
    }
}

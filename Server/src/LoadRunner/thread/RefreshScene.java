package LoadRunner.thread;

import LoadRunner.game.Scene;
import LoadRunner.handler.GameManager;

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
                System.out.println("\033[H\033[2J");//supprime tout ce qu'il y a dans la console auparavant
                System.out.println(scene.getFinalBoard());
                sleep(300);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

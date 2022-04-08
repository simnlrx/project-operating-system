package LodeRunner.thread;

import LodeRunner.game.Scene;
import LodeRunner.handler.GameManager;

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
                System.out.println("\033[H\033[2J");//efface tout ce qu'il y a dans la console auparavant
                System.out.println(scene.getFinalBoard());
                sleep(100);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

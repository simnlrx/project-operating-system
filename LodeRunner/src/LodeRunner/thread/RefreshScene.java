package LodeRunner.thread;

import LodeRunner.game.Scene;
import LodeRunner.handler.GameManager;
import LodeRunner.handler.GameState;

public class RefreshScene extends Thread {
    private final Scene scene;
    // scene de jeu
    private final GameManager gameManager;
    // GameManager
    private int lvl;

    /**
     * Constructeur de RefreshScene
     * @param gameManager GameManager
     */
    public RefreshScene(GameManager gameManager) {
        this.scene = gameManager.getScene();
        this.gameManager = gameManager;
        this.lvl = gameManager.getLevel();
    }


    @Override
    public void run() {
        try {
            while (gameManager.getLevel() == lvl || gameManager.getGameState().isGame()) {
                if (gameManager.isServer() || gameManager.getGameState().equals(GameState.SOLOGAME)) {
                    scene.matrix2Screen();
                }else{
                    System.out.println("\033[H\033[2J");
                    System.out.println(gameManager.getScene().getFboard());
                }
                sleep(100);

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

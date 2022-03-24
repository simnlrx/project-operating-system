package LoadRunner.thread;

import LoadRunner.game.Player;
import LoadRunner.game.Scene;
import LoadRunner.handler.GameManager;

public class BlockBreakThread extends Thread {

    private GameManager gameManager;
    private Player player;

    public BlockBreakThread(GameManager gameManager, Player player){
        this.gameManager = gameManager;
        this.player = player;
    }

    @Override
    public void run() {
        try {
            wait();

            int x = player.getPosX();
            int y = player.getPosY();
            Scene scene = gameManager.getScene();

            scene.setValuePosition(x+1, y, 0);
            sleep(4000);
            scene.setValuePosition(x+1, y, 2);

            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }




    }
}

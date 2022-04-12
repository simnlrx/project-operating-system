import LodeRunner.game.Scene;
import LodeRunner.handler.FrameManager;
import LodeRunner.handler.GameManager;
import LodeRunner.handler.GameState;
import LodeRunner.handler.LoadingManager;
import LodeRunner.utils.Display;

import javax.swing.*;
import java.io.IOException;

public class LodeRunner {

    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("\033[H\033[2J");//supprime tout ce qu'il y a dans la console auparavant
        System.out.println("Lode Runner | Runnig ...");
        Display.title();


        GameManager gameManager = new GameManager(GameState.LOADING, 8059);
        LoadingManager loading = new LoadingManager(gameManager); // 40 30

        loading.start();
        gameManager.setLevel(1);
        gameManager.start();
    }
}

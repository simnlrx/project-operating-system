import LoadRunner.Server.TCPTask;
import LoadRunner.game.Scene;
import LoadRunner.handler.FrameManager;
import LoadRunner.handler.GameManager;
import LoadRunner.handler.GameState;
import LoadRunner.handler.LoadingManager;
import LoadRunner.utils.Display;

import javax.swing.*;
import java.io.IOException;

public class LoadRunner {

    public static void main(String[] args) throws IOException {
        System.out.println("\033[H\033[2J");//supprime tout ce qu'il y a dans la console auparavant
        System.out.println("Load Runner | Runnig ...");
        Display.title();

        Scene scene = new Scene(30,40);//les valeurs 17 et 36 sont faites pour coller avec les méthodes de création des escaliers =>17-1(pour le bord)= 4 escaliers
        GameManager gameManager = new GameManager(scene, GameState.GAMEMODE, 8059, "localhost");
        JFrame frame = new JFrame("Contrôles");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        LoadingManager loading = new LoadingManager(gameManager); // 40 30

        loading.start();
        gameManager.setGameState(GameState.LEVEL);
        FrameManager frameManager = new FrameManager(frame, gameManager);
        frameManager.generate();
        gameManager.setLevel(1);
        gameManager.setGameState(GameState.LOADING);
        gameManager.start();
    }
}

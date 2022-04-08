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

        Scene scene = new Scene(30, 40);//les valeurs 17 et 36 sont faites pour coller avec les méthodes de création des escaliers =>17-1(pour le bord)= 4 escaliers
        GameManager gameManager = new GameManager(scene, GameState.LOADING, 8059);
        JFrame frame = new JFrame("Contrôles");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        LoadingManager loading = new LoadingManager(gameManager); // 40 30

        loading.start();
        gameManager.setLevel(1);
        FrameManager frameManager = new FrameManager(frame, gameManager);
        frameManager.generate();
        gameManager.start();
    }
}

import LoadRunner.game.Scene;

import LoadRunner.handler.FrameManager;
import LoadRunner.handler.GameManager;
import LoadRunner.handler.GameState;
import LoadRunner.handler.LoadingManager;
import LoadRunner.utils.Display;

import javax.swing.*;

public class LoadRunner {

    public static void main(String[] args) {
        System.out.println("\033[H\033[2J");//supprime tout ce qu'il y a dans la console auparavant
        System.out.println("Load Runner | Runnig ...");
        Display.title();

        Scene scene = new Scene(30,40);//les valeurs 17 et 36 sont faites pour coller avec les méthodes de création des escaliers =>17-1(pour le bord)= 4 escaliers
        GameManager gameManager = new GameManager(scene, GameState.GAMEMODE);

        JFrame frame = new JFrame("Contrôles");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        FrameManager frameManager = new FrameManager(frame, gameManager);

        LoadingManager loading = new LoadingManager(gameManager); // 40 30

        frameManager.generate();

        loading.start();
        gameManager.setGameMode(loading.getGamemode()); //lors de la récupération du mode de jeu, on set les joueurs
        gameManager.setGameState(GameState.LEVEL);
        gameManager.setLevel(loading.getLevel());
        gameManager.setGameState(GameState.LOADING);
        gameManager.start();
    }
}

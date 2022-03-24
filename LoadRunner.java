import LoadRunner.game.Scene;

import LoadRunner.handler.GameManager;
import LoadRunner.handler.LoadingManager;
import LoadRunner.utils.Display;

public class LoadRunner {
    public static void main(String[] args) {
        System.out.println("\033[H\033[2J");//supprime tout ce qu'il y a dans la console auparavant
        System.out.println("Load Runner | Runnig ...");
        Display.title();

        LoadingManager loading = new LoadingManager(18,34);
        Scene scene = new Scene(18,34);//les valeurs 17 et 36 sont faites pour coller avec les méthodes de création des escaliers =>17-1(pour le bord)= 4 escaliers
        GameManager gameManager = new GameManager(scene);

        gameManager.setGameMode(loading.selectGameMode()); //lors de la récupération du mode de jeu, on set les joueurs
        gameManager.setLevel(loading.getLevel());
        gameManager.start();
    }
}

package LoadRunner;

import LoadRunner.game.Player;
import LoadRunner.game.Scene;
import LoadRunner.handler.GameManager;
import LoadRunner.handler.LoadingManager;
import LoadRunner.thread.EnemyThread;
import LoadRunner.thread.RefreshScene;

public class LoadRunner {
    public static void main(String[] args) {
        System.out.println("Load Runner | Runnig ...");
        Player player1 = new Player(0, "Bastien", 1);
        LoadingManager loading = new LoadingManager(18,34);
        GameManager game = new GameManager(player1);
        game.setGameMode(loading.selectGameMode());
        Scene scene = new Scene(18,34,player1,true); //les valeurs 17 et 36 sont faites pour coller avec les méthodes de création des escaliers =>17-1(pour le bord)= 4 escaliers
        scene.genSceenLevel1();
        EnemyThread ennemi1 = new EnemyThread(22,11, true, scene);
        EnemyThread ennemi2 = new EnemyThread(16,7, false, scene);
        EnemyThread ennemi3 = new EnemyThread(15,3,true, scene);
        RefreshScene refresh = new RefreshScene(scene, true);
        scene.printMatrix();
        refresh.start();
        ennemi1.start();
        ennemi2.start();
        ennemi3.start();
    }
}

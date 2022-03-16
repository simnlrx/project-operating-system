import LoadRunner.game.Player;
import LoadRunner.game.Scene;
import LoadRunner.handler.GameManager;
import LoadRunner.handler.GameState;
import LoadRunner.handler.ChooseLevel;
import LoadRunner.handler.LoadingManager;
import LoadRunner.thread.EnemyThread;

public class LoadRunner {
    public static void main(String[] args) {
        System.out.println("Load Runner | Runnig ...");

        Player player1 = new Player(0, "BastienLPB", 1);
        LoadingManager loading = new LoadingManager(18,34);
        Scene scene = new Scene(18,34,player1);//les valeurs 17 et 36 sont faites pour coller avec les méthodes de création des escaliers =>17-1(pour le bord)= 4 escaliers
        GameManager game = new GameManager(scene, player1);
        ChooseLevel choose = new ChooseLevel(18,34, game);
        game.setGameMode(loading.selectGameMode());

        game.start();


        //EnemyThread ennemi1 = new EnemyThread(22,11, true, scene, game);
        //EnemyThread ennemi2 = new EnemyThread(16,7, false, scene, game);
        //EnemyThread ennemi3 = new EnemyThread(15,3,true, scene, game);


        //ennemi1.start();
        //ennemi2.start();
        //ennemi3.start();
    }
}

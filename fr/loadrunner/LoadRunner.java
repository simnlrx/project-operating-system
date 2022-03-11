import game.Player;
import game.Scene;
import thread.EnemyThread;
import handler.GameManager;
import handler.LoadingManager;

//
public class LoadRunner {
    public static void main(String[] args) {
        System.out.println("Load Runner | Runnig ...");
        Player player1 = new Player(0, "Bastien", 1);
        LoadingManager loading = new LoadingManager(18,34);
        GameManager game = new GameManager(player1);
        game.setGameMode(loading.selectGameMode());
        Scene scene = new Scene(18,34,player1); //les valeurs 17 et 36 sont faites pour coller avec les méthodes de création des escaliers =>17-1(pour le bord)= 4 escaliers
        EnemyThread ennemi1 = new EnemyThread(22,11,scene);
        EnemyThread ennemi2 = new EnemyThread(16,7,scene);
        EnemyThread ennemi3 = new EnemyThread(15,3,scene);
        
    }
}

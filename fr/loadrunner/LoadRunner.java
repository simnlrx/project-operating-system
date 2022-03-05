import game.Player;
import game.Scene;
import handler.GameManager;
import game.Loading;

//
public class LoadRunner {
    public static void main(String[] args) {
        System.out.println("Load Runner | Runnig ...");
        Player player1 = new Player(0, "Bastien", 1);
        Loading loading = new Loading(18,34);
        GameManager game = new GameManager(player1);
        game.setGameMode(loading.selectGameMode());
        Scene scene = new Scene(18,34,player1); //les valeurs 17 et 36 sont faites pour coller avec les méthodes de création des escaliers =>17-1(pour le bord)= 4 escaliers
        scene.genSceenLevel1();
        scene.matrix2Screen();
        //game.start();
    }
}

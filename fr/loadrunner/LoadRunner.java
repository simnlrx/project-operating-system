import game.GameManager;
import game.Player;
import game.Scene;
//
public class LoadRunner {

    public static void main(String[] args) {
        System.out.println("Load Runner | Runnig ...");
        Player player1 = new Player(0, "Bastien");
        Player player2 = new Player(0, "Simon");
        Scene scene = new Scene(18,34,player1, player2); //les valeurs 17 et 36 sont faites pour coller avec les méthodes de création des escaliers =>17-1(pour le bord)= 4 escaliers
        GameManager game = new GameManager(player1, player2, scene);

        game.start();
    }
}

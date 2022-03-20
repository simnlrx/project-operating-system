import LoadRunner.events.KeyboardEvent;
import LoadRunner.game.Player;
import LoadRunner.game.Scene;

import LoadRunner.handler.FrameManager;
import LoadRunner.handler.GameManager;
import LoadRunner.handler.LoadingManager;
import LoadRunner.utils.Display;

import javax.swing.*;
import java.awt.*;


public class LoadRunner {
    public static void main(String[] args) {

        //Frame f = new Frame("Demo");
        //f.setLayout(new FlowLayout());
        //f.setSize(800, 500);
        //JLabel l = new JLabel("Jeux", SwingConstants.CENTER);

        //l.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        //f.add(l);
        //f.setVisible(true);
        //KeyboardEvent k = new KeyboardEvent();
        //f.addKeyListener(k);

        System.out.println("\033[H\033[2J");//supprime tout ce qu'il y a dans la console auparavant
        System.out.println("Load Runner | Runnig ...");
        Display.title();

        Player player1 = new Player(100, "Player1", 1);
        Player player2 = new Player(0, "Player2", 2);
        LoadingManager loading = new LoadingManager(18,34);
        Scene scene = new Scene(18,34);//les valeurs 17 et 36 sont faites pour coller avec les méthodes de création des escaliers =>17-1(pour le bord)= 4 escaliers
        GameManager gameManager = new GameManager(scene);

        gameManager.setGameMode(loading.selectGameMode());
        //lors de la récupération du mode de jeu, on set les joueurs

        if(gameManager.getGameMode()==1){
          scene.set1Player(player1);
        }else{
          scene.set2Players(player1,player2);
        }

        gameManager.setLevel(loading.getLevel());
        gameManager.start();
        //l.setText(scene.matrix2Screen());
    }
}

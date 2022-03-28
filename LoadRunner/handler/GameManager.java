package LoadRunner.handler;

import java.util.Scanner;

import LoadRunner.events.KeyboardEvent;
import LoadRunner.game.Player;
import LoadRunner.game.Scene;
import LoadRunner.thread.RefreshScene;
import LoadRunner.handler.LevelManager;
import LoadRunner.handler.EnemiesManager;
import LoadRunner.handler.LoadingManager;
import LoadRunner.thread.EnemyThread;
import LoadRunner.thread.RegenSceneThread;

import java.awt.*;

public class GameManager {

    private Player player1;
    private Player player2;
    private final Scene scene;
    private int gamemode;
    private GameState gameState;
    private int level;
    private ThreadManager threadManager;


    public GameManager(Scene scene, GameState gameState) {
        this.player1 = scene.getPlayer1();
        this.player2 = scene.getPlayer2();
        this.scene = scene;
        this.gameState = gameState;
    }

    //lors du lancement de la partie, les joueurs choisis auparavant sont ajoutés au GameManager
    public void start() {
        LevelManager levelManager = new LevelManager(this);
        threadManager = new ThreadManager();
        EnemiesManager enemiesManager = new EnemiesManager(this, threadManager);
        RefreshScene refresh = new RefreshScene(this);
        RegenSceneThread regenScene = new RegenSceneThread(this);
        threadManager.addThread(refresh);
        threadManager.addThread(regenScene);

        if(getGameMode()==1){
            scene.set1Player(player1);
            gameState = GameState.SOLOGAME;
        }else{
            scene.set2Players(player1,player2);
            gameState = GameState.MULTIGAME;
        }
        threadManager.startThreads();
    }

    public void EndGame(){
      //méthoe pour terminer la partie
      System.out.println("\033[H\033[2J");//supprime tout ce qu'il y a dans la console auparavant
      LoadingManager loadingManager = new LoadingManager(this);

      Scanner scanner;
      String continueToEnd;

      String [] scorePage = new String[]{"GAME OVER","Player : "+player1.getName(),"Score :"+player1.getScore(),"Press c to continue"};
      String[][] end = loadingManager.getDisplay(scorePage);

      loadingManager.printBoard(end);
    }

    public void end(){
      gameState = GameState.END;
        try{
          wait(1000);
          EndGame();
          wait(10000);
        } catch (Exception e) {
          e.printStackTrace();
        }
    }


    public Scene getScene() {
        return this.scene;
    }

    public void setGameMode(int gamemode) {
        this.gamemode = gamemode;
    }

    public int getGameMode() {
        return this.gamemode;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public void setLevel(int level){
      this.level = level;
    }

    public int getLevel(){
      return this.level;
    }

    public void setPlayer1(Player player){
      this.player1 = player;
    }

    public Player getPlayer1(){
      return this.player1;
    }

}

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

    private Player player1;// joueur 1
    private Player player2;// joueur 2
    private final Scene scene;// scene de jeu
    private int gamemode;// mode de jeu - solo ou multi -
    private GameState gameState;// GameState pour l'état de la partie
    private int level;// niveau de la partie
    private ThreadManager threadManager;// liste de thread
    private boolean printEndGame;// boolean pour l'affichage de fin de la partie


    public GameManager(Scene scene, GameState gameState) {
      // constructeur de GameManager
      // lors du lancement de la partie, les joueurs choisis auparavant sont ajoutés au GameManager
        this.player1 = scene.getPlayer1();
        this.player2 = scene.getPlayer2();
        this.scene = scene;
        this.gameState = gameState;
        this.printEndGame = true;// possibilité de faire l'affichage de fin de partie quand printEndGame est à true
    }

    public void start() {
      // méthode pour initialiser la partie
        LevelManager levelManager = new LevelManager(this);
        threadManager = new ThreadManager();
        EnemiesManager enemiesManager = new EnemiesManager(this, threadManager);
        RefreshScene refresh = new RefreshScene(this);
        RegenSceneThread regenScene = new RegenSceneThread(this);
        threadManager.addThread(refresh);
        threadManager.addThread(regenScene);

        if(getGameMode()==1){
          // affectation du joueur 1 et du mode de jeu de gameState
            scene.set1Player(player1);
            gameState = GameState.SOLOGAME;
        }else{
          // affectation du joueur 1 et du joueur 2, suivi du mode de jeu de gameState
            scene.set2Players(player1,player2);
            gameState = GameState.MULTIGAME;
        }
        threadManager.startThreads();
        // méthode qui permet de lancer tout les threads présents dans la liste et donc de la partie
    }

    public void EndGame(){
      //méthode pour afficher la fin de partie
      if(this.printEndGame){

        Scanner scanner;
        String continueToEnd;

        //vérification si l'affichage de la partie est déja en cours
        System.out.println("\033[H\033[2J");

        //supprime tout ce qu'il y a dans la console auparavant
        LoadingManager loadingEnd = new LoadingManager(this);
        // création de l'affichage de fin de partie
        this.printEndGame = false;

        do{
          loadingEnd.loadEnd();
          scanner = new Scanner(System.in);
          continueToEnd = scanner.nextLine();
        }while(!continueToEnd.equals("c"));
        System.exit(0);
      }
    }

    public synchronized void end(){
      // méthode qui va permettre de mettre fin à la partie suivi de son affichage
      gameState = GameState.END;
      EndGame();
    }


    public Scene getScene() {
      // récupération de la scene
        return this.scene;
    }

    public void setGameMode(int gamemode) {
      // affectation du mode de jeu
        this.gamemode = gamemode;
    }

    public int getGameMode() {
      // récupération du mode de jeu
        return this.gamemode;
    }

    public GameState getGameState() {
      // récupération de GameState
        return gameState;
    }

    public void setGameState(GameState gameState) {
      // affecetation de gameState
        this.gameState = gameState;
    }

    public void setLevel(int level){
      // affectation du niveau
      this.level = level;
    }

    public int getLevel(){
      // récupération du niveau
      return this.level;
    }

    public void setPlayer1(Player player){
      // affectation du joueur 1
      this.player1 = player;
    }

    public Player getPlayer1(){
      // récupération du joueur 1
      return this.player1;
    }

}

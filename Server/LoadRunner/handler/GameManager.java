package LoadRunner.handler;

import LoadRunner.game.Player;
import LoadRunner.game.Scene;
import LoadRunner.thread.RefreshScene;
import LoadRunner.thread.RegenSceneThread;

import java.util.Scanner;

public class GameManager {

    private Player player1;
    // joueur 1
    private Player player2;
    // joueur 2
    private final Scene scene;
    // scene de jeu
    private int gamemode;
    // mode de jeu - solo ou multi -
    private GameState gameState;
    // GameState pour l'état de la partie
    private int level;
    // niveau de la partie
    private ThreadManager threadManager;
    // liste de thread
    private boolean printEndGame;
    // boolean pour l'affichage de fin de la partie

    /*
     * Constructeur de GameManager
     * @param Scene scene qui est la scène du jeu
     * @param GameState gameState pour l'état du jeu
     */

    public GameManager(Scene scene, GameState gameState) {
      // lors du lancement de la partie, les joueurs choisis auparavant sont ajoutés au GameManager
        this.player1 = scene.getPlayer1();
        this.player2 = scene.getPlayer2();
        this.scene = scene;
        this.gameState = gameState;
        this.printEndGame = true;
        // possibilité de faire l'affichage de fin de partie quand printEndGame est à true
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

    public synchronized void nextLevel(){
      //méthode pour respawn le joueur 1 dans un nouveau niveau
      try{
          this.endLevel();
          scene.setScene();

          GameManager gameManager2 = new GameManager(scene, GameState.GAMEMODE);
          // créer une nouvelle insatnce de gameMangaer mais avec la même scene et les memes joueurs

          if(this.getLevel()<3){
            gameManager2.setLevel(this.level+1);
            System.out.println("Loading Level "+gameManager2.getLevel()+", please wait. . .");
            wait(3000);
            gameManager2.setGameMode(gamemode); //lors de la récupération du mode de jeu, on set les joueurs
            gameManager2.setGameState(GameState.LEVEL);
            gameManager2.setLevel(gameManager2.getLevel());
            gameManager2.setGameState(GameState.LOADING);
            gameManager2.start();
          }else{
            gameManager2.endGame();
          }
      } catch (Exception e) {
          e.printStackTrace();
      }
    }

    public void printEndGame(){
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
        }while(!continueToEnd.equals("e"));
        System.exit(0);
      }
    }

    public synchronized void endGame(){
      // méthode qui va permettre de mettre fin à la partie suivi de son affichage
      gameState = GameState.END;
      printEndGame();
    }

    public synchronized void endLevel(){
      // méthode qui va permettre de mettre fin à la partie suivi de son affichage
      gameState = GameState.END;
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

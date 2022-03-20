package LoadRunner.handler;

import LoadRunner.events.KeyboardEvent;
import LoadRunner.game.Player;
import LoadRunner.game.Scene;
import LoadRunner.thread.RefreshScene;
import LoadRunner.handler.LevelManager;
import LoadRunner.handler.EnemiesManager;
import LoadRunner.thread.EnemyThread;
import LoadRunner.thread.RegenSceneThread;

public class GameManager {

    private Player player1;
    private Player player2;
    private Scene scene;
    private int gamemode;
    private GameState gameState;
    private int level;

    public GameManager(Scene scene) {
        this.scene = scene;
        this.gameState = GameState.LOADING;
    }

    //lors du lancement de la partie, les joueurs choisis auparavant sont ajoutés au GameManager
    public void start() {
        LevelManager levelManager = new LevelManager(this);
        ThreadManager threadManager = new ThreadManager();
        EnemiesManager enemiesManager = new EnemiesManager(this, threadManager);
        RefreshScene refresh = new RefreshScene(this);
        RegenSceneThread regenScene = new RegenSceneThread(this);
        //KeyboardEvent key = new KeyboardEvent();

        threadManager.addThread(refresh);
        threadManager.addThread(regenScene);

        scene.getPlayer1();
        scene.getPlayer2();
        if (player2 == null)
            gameState = GameState.SOLOGAME;
        else
            gameState = GameState.MULTIGAME;

        threadManager.startThreads();
    }


    public Scene getScene() {
        return this.scene;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
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

}

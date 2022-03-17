package LoadRunner.handler;


import LoadRunner.game.Player;
import LoadRunner.game.Scene;
import LoadRunner.thread.RefreshScene;

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

    public void start() {
        scene.getPlayer1();
        scene.getPlayer2();
        RefreshScene refresh = new RefreshScene(this);
        if (player2 == null)
            gameState = GameState.SOLOGAME;
        else
            gameState = GameState.MULTIGAME;
        refresh.start();
    }

    public void end() {

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

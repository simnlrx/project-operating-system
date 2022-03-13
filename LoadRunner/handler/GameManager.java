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

  RefreshScene refresh = new RefreshScene(scene, true);

  public GameManager(Player player, Scene scene){
    this.player1 = player;
    this.scene = scene;
  }

  public GameManager(Player player1, Player player2, Scene scene){
    this.player1 = player1;
    this.player2 = player2;
  }

  public void start(){
    scene.genSceenLevel1();
    scene.matrix2Screen();
    refresh.start();
  }

  public void end(){

  }

  public void setScene(Scene scene){
    this.scene = scene;
  }

  public Scene getScene(){
    return this.scene;
  }

  public void setPlayer1(Player player1){
    this.player1 = player1;
  }

  public void setPlayer2(Player player2){
    this.player2 = player2;
  }

  public void setGameMode(int gamemode){
    this.gamemode = gamemode;
  }

  public int getGameMode(){
    return this.gamemode;
  }
}

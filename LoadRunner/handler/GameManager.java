package LoadRunner.handler;

import LoadRunner.game.Player;
import LoadRunner.game.Scene;

public class GameManager {

  private Player player1;
  private Player player2;
  private Scene scene;
  private int gamemode;

  public GameManager(Player player){
    this.player1 = player;
    this.scene = scene;
  }

  public GameManager(Player player1, Player player2){
    this.player1 = player1;
    this.player2 = player2;
  }

  public void start(){
    int optionGame;
    scene.genSceenLevel1();//test d'affichage de mla méthode Matrix2Screen
    scene.matrix2Screen();
    scene.printMatrix();
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

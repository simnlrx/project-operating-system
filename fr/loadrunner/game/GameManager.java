package game;

public class GameManager {

  private Player player1;
  private Player player2;
  private Scene scene;

  public GameManager(Player player, Scene scene){
    this.player1 = player;
    this.scene = scene;
  }

  public GameManager(Player player1, Player player2, Scene scene){
    this.player1 = player1;
    this.player2 = player2;
    this.scene = scene;
  }

  public void start(){
    int optionGame;
    scene.genSceenLevel1();//test d'affichage de mla méthode Matrix2Screen
    scene.matrix2Screen();
    scene.printMatrix();
  }

  public void end(){

  }
}

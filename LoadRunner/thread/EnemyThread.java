package LoadRunner.thread;

import LoadRunner.game.Scene;
import LoadRunner.game.Player;
import LoadRunner.handler.GameManager;
import LoadRunner.handler.GameState;

public class EnemyThread extends Thread {
  private int posX;//position en x de l'ennemi
  private int posY;//position en y de l'ennemi
  private Scene scene;//scene de l'ennemi
  private GameManager gameManager;// Game Manager
  private Player player1;

  private int DistanceToPlayer; //distance entre l'ennemi et le joueur



  public EnemyThread(int posX, int posY, GameManager gameManager){
    this.scene = gameManager.getScene();
    this.posX = posX;
    this.posY = posY;
    scene.setValuePosition(posX,posY,4);//les coordonnées de l'ennemi sont directement placés dans la scene
    this.gameManager = gameManager;
    this.player1 = gameManager.getScene().getPlayer1();
  }

  public double getDistanceToPlayer(int posXEnemy, int posYEnemy){
    double dist = Math.sqrt(Math.pow((posXEnemy- player1.getPosX()),2)+ Math.pow((posYEnemy - player1.getPosY()),2));
    return dist;
  }

  @Override
  public void run() {
    try {
    while(gameManager.getGameState().isGame()){
      this.sleep(500);
      System.out.println("La distance de base est " + getDistanceToPlayer(posX, posY));
      System.out.println("La nouvelle distance est " + getDistanceToPlayer(posX-1, posY));
      if(getDistanceToPlayer(posX-1, posY)<getDistanceToPlayer(posX, posY)){
        System.out.println("La distance est moindre, déplacement");
        if(scene.getValuePosition(posX-1, posY-1)!=0){
          if(scene.getValuePosition(posX-1, posY)==5){
            this.posX = posX-2;
            scene.setValuePosition(posX, posY, 0);
          }else{
            scene.setValuePosition(posX, posY, 0);
            this.posX--;
          }
        }
      }else{
        if(scene.getValuePosition(posX+1, posY-1)!=0){
          if(scene.getValuePosition(posX+1, posY)==5){
            scene.setValuePosition(posX, posY, 0);
            this.posX = posX-2;
          }else{
            scene.setValuePosition(posX, posY, 0);
            this.posX--;
          }
        }
      }

      scene.setValuePosition(posX, posY, 4);

      if(getDistanceToPlayer(posX, posY-1)<getDistanceToPlayer(posX, posY)){
        if(scene.getValuePosition(posX, posY-1)==3){
          scene.setValuePosition(posX, posY, 0);
          this.posY--;
        }
      }else{
        if(scene.getValuePosition(posX, posY)==3){
          scene.setValuePosition(posX, posY, 0);
          this.posY++;
        }
      }
      scene.setValuePosition(posX, posY, 4);
    }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

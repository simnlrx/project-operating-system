package LoadRunner.thread;

import LoadRunner.game.Scene;
import LoadRunner.game.Player;
import LoadRunner.handler.GameManager;
import LoadRunner.handler.GameState;

public class EnemyThread extends Thread {
  private int posX;//position en x de l'ennemi
  private int posY;//position en y de l'ennemi
  private Scene scene;//scene de l'ennemi
  private boolean sens;//sens du mouvement de l'ennemi
  private GameManager gameManager;// Game Manager
  private Player player1;

  private int DistanceToPlayer; //distance entre l'ennemi et le joueur



  public EnemyThread(int posX, int posY, boolean sens, GameManager gameManager){
    this.scene = gameManager.getScene();
    this.posX = posX;
    this.posY = posY;
    scene.setValuePosition(posX,posY,4);//les coordonnées de l'ennemi sont directement placés dans la scene
    this.sens = sens;
    this.gameManager = gameManager;
    this.player1 = GameManager.getPlayer1();
  }

  public int getDistanceToPlayer(int posXEnemy, int posYEnemy){
    int dist = Math.sqrt(Math.pow((posXEnemy- player1.getPosX()),2)+ Math.pow((posYEnemy - player1.getPosY()),2));
    return dist;
  }

  public void testDistanceX(){//test si un deplacement sur X se rapprocherait de
    if(getDistanceToPlayer(posX-1, posY)<getDistanceToPlayer(posX, posY)){
      if(scene.getValuePosition(posX-1, posY-1)!=0){
        if(scene.getValuePosition(posX-1, posY)==5){
          this.posX = posX-2;
        }else{
          this.posX--;
        }
      }
    }else{
      if(scene.getValuePosition(posX+1, posY-1)!=0){
        if(scene.getValuePosition(posX+1, posY1)==5){
          this.posX = posX-2;
        }else{
          this.posX--;
        }
      }
    }
    scene.setValuePosition(posX, posY);
  }

  public void testDistanceY(){
    if(getDistanceToPlayer(posX, posY-1)<getDistanceToPlayer(posX, posY)){
      if(scene.getValuePosition(posX, posY-1)==3{
        this.posY--;
      }
    }else{
      if(scene.getValuePosition(posX, posY)==3){
        this.posY++;
      }
    }
    scene.setValuePosition(posX, posY);
  }




  @Override
  public void run() {
    try{
      while(gameManager.getGameState().isGame()){
        testDistanceX();
        testDistanceY();
    }catch(InterruptedException e){
          e.printStackTrace();
    }
  }
}

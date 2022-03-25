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

  public EnemyThread(int posX, int posY, GameManager gameManager){
    this.scene = gameManager.getScene();
    this.posX = posX;
    this.posY = posY;
    scene.setValuePosition(posX,posY,4);//les coordonnées de l'ennemi sont directement placés dans la scene
    this.gameManager = gameManager;
    this.player1 = gameManager.getScene().getPlayer1();
  }

  public double getDistanceToPlayer(int posXEnemy, int posYEnemy){//calcul distance entre enenmy et joueur
    double dist = Math.sqrt(Math.pow((posXEnemy- player1.getPosX()),2)+ Math.pow((posYEnemy - player1.getPosY()),2));
    return dist;
  }

  public void KillPlayer(){
    int spawnX = (int)(Math.random()*scene.getLenght()+1);
    if((posX == player1.getPosX() && posY == player1.getPosY())){
      System.out.println("Rencontre avec un ennemi");
      player1.getKill();
      player1.setPosition(spawnX, scene.getHeight()-2);
    }
  }

  @Override
  public void run() {
    try {
    while(gameManager.getGameState().isGame()){
      this.sleep(500);
      if(getDistanceToPlayer(posX, posY+1)<getDistanceToPlayer(posX, posY)){
        if(scene.getValuePosition(posX, posY+1)==3){
          scene.setValuePosition(posX, posY, 0);
          this.posY++;
          scene.setValuePosition(posX, posY, 4);
        }
      }else{
        if(scene.getValuePosition(posX, posY)==3){
          scene.setValuePosition(posX, posY, 0);
          this.posY--;
          scene.setValuePosition(posX, posY, 4);
        }
      }
      KillPlayer();
      if(getDistanceToPlayer(posX-1, posY)<getDistanceToPlayer(posX, posY)){
        int valueBlocInf = scene.getValuePosition(posX-1, posY+1);
        int valueBlocMid = scene.getValuePosition(posX-1, posY);
        if(valueBlocInf!=0 && valueBlocInf!=9 && valueBlocMid!=12){
          if(valueBlocMid==5){
            scene.setValuePosition(posX, posY, 0);
            this.posX = posX-2;
            scene.setValuePosition(posX, posY, 4);
          }else if(valueBlocMid!=2 && (valueBlocInf==2|| valueBlocInf==3)){
            scene.setValuePosition(posX, posY, 0);
            this.posX--;
            scene.setValuePosition(posX, posY, 4);
          }
        }
      }else{
        int valueBlocInf = scene.getValuePosition(posX+1, posY+1);
        int valueBlocMid = scene.getValuePosition(posX+1, posY);
        if(valueBlocInf!=0 && valueBlocInf!=9 && valueBlocMid!=12){
          if(valueBlocMid==5){
            scene.setValuePosition(posX, posY, 0);
            this.posX = posX+2;
            scene.setValuePosition(posX, posY, 4);
          }else if(valueBlocMid!=2 && (valueBlocInf==2|| valueBlocInf==3)){
            scene.setValuePosition(posX, posY, 0);
            this.posX++;
            scene.setValuePosition(posX, posY, 4);
          }
        }
      }
      KillPlayer();
    }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

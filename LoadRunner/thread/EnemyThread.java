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
    int spawnX = 0;
    int basScene = scene.getHeight()-2;
    if(player1.getLife()>=1){
      if((posX == player1.getPosX() && posY == player1.getPosY())){
        player1.getKill();
        do{
          spawnX = (int)(Math.random()*scene.getLenght()+1);
        }while(scene.getValuePosition(spawnX,basScene+1)!=2 && scene.getValuePosition(spawnX,basScene)!=2);
        player1.setPosition(spawnX, basScene);
        scene.setValuePosition(spawnX, basScene, 10);
      }
    }else{
      System.out.println("Fin de partie");//partie à remplacer avec un affichage de fin de partie
      gameManager.end();
    }
  }


  @Override
  public void run() {
    try {
    while(gameManager.getGameState().isGame()){
      this.sleep(500);
      if(getDistanceToPlayer(posX, posY+1)<getDistanceToPlayer(posX, posY)){//vérification si un deplacement vers le haut pourrai rapprocher l'ennemi du joueur
        if(scene.getValuePosition(posX, posY+1)==3 || scene.getValuePosition(posX, posY+1)==10){//vérification si le bloc suivant est un escalier et pas une platforme
          scene.setValuePosition(posX, posY, 0);//postion actuelle de l'ennemi placée à 0
          this.posY++;// incrémantation de sa position
          scene.setValuePosition(posX, posY, 4);//valeur de l'ennemi placé à sa nouvelle position
        }
      }else{//si un deplacment vers le haut éloigne l'ennemi
        if(scene.getValuePosition(posX, posY-1)==3 || scene.getValuePosition(posX, posY-1)==10){//vérification si le bloc suivant est un escalier et pas une platforme
          scene.setValuePosition(posX, posY, 0);//postion actuelle de l'ennemi placée à 0
          this.posY--;// décrémantation de sa position
          scene.setValuePosition(posX, posY, 4);//valeur de l'ennemi placé à sa nouvelle position
        }
      }
      KillPlayer();


      if(getDistanceToPlayer(posX-1, posY)<getDistanceToPlayer(posX, posY)){//vérification si un deplacement vers la gauche pourrai rapprocher l'ennemi du joueur
        int valueBlocInf = scene.getValuePosition(posX-1, posY+1);//récupération de la valeur du bloc en bas à gauche
        int valueBlocMid = scene.getValuePosition(posX-1, posY);//récupérationn de la valeur du bloc à gauche


        if(valueBlocInf!=0 && valueBlocInf!=9 && valueBlocMid!=12){//si les critères correspondent
          if(valueBlocMid==5){//et si le prochain bloc est un object
            scene.setValuePosition(posX, posY, 0);
            this.posX = posX-2;//deplacement de 2 blocs à gauche
            scene.setValuePosition(posX, posY, 4);
          }else if(valueBlocMid!=2 && valueBlocInf==14){
            scene.setValuePosition(posX, posY, 0);
            this.posX--;
            this.posY++;
            scene.setValuePosition(posX, posY, 4);
            sleep(5000);
            scene.setValuePosition(posX, posY, 2);
            posX = scene.getPosXSpawnEnemy();
            posY = scene.getPosYSpawnEnemy();
            scene.setValuePosition(posX, posY, 4);
          }else if(valueBlocMid!=2 && (valueBlocInf==2|| valueBlocInf==3)){//sinon
            scene.setValuePosition(posX, posY, 0);//déplacement d'un bloc à gauche
            this.posX--;
            scene.setValuePosition(posX, posY, 4);
          }
        }
      }else{//si un deplacment vers la gauche éloigne l'ennemi
        int valueBlocInf = scene.getValuePosition(posX+1, posY+1);//récupération de la valeur du bloc en bas à droite
        int valueBlocMid = scene.getValuePosition(posX+1, posY);//récupérationn de la valeur du bloc à droite

        if(valueBlocInf!=0 && valueBlocInf!=9 && valueBlocMid!=12){//si les critères correspondent
          if(valueBlocMid==5){//et si le prochain bloc est un object
            scene.setValuePosition(posX, posY, 0);
            this.posX = posX+2;//deplacement de 2 blocs à gauche
            scene.setValuePosition(posX, posY, 4);
          }else if(valueBlocMid!=2 && valueBlocInf==14){
            scene.setValuePosition(posX, posY, 0);
            this.posX++;
            this.posY++;
            scene.setValuePosition(posX, posY, 4);
            sleep(5000);
            scene.setValuePosition(posX, posY, 2);
            posX = scene.getPosXSpawnEnemy();
            posY = scene.getPosYSpawnEnemy();
            scene.setValuePosition(posX, posY, 4);
          }else if(valueBlocMid!=2 && (valueBlocInf==2|| valueBlocInf==3)){
            scene.setValuePosition(posX, posY, 0);
            this.posX++;//déplacement d'un bloc à droite
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

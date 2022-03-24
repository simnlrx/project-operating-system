package LoadRunner.thread;

import LoadRunner.game.Scene;
import LoadRunner.handler.GameManager;
import LoadRunner.handler.GameState;

public class EnemyThread extends Thread {
  private int posX;//position en x de l'ennemi
  private int posY;//position en y de l'ennemi
  private Scene scene;//scene de l'ennemi
  private boolean sens;//sens du mouvement de l'ennemi
  private GameManager gameManager;

  public EnemyThread(int posX, int posY, boolean sens, GameManager gameManager){
    this.scene = gameManager.getScene();
    this.posX = posX;
    this.posY = posY;
    scene.setValuePosition(posX,posY,4);//les coordonnées de l'ennemi sont directement placés dans la scene
    this.sens = sens;
    this.gameManager = gameManager;
  }

  public void chooseDirection(){
    if(scene.getValuePosition(posX-1, posY+1)!=2 && scene.getValuePosition(posX-1, posY+1)!=3){
      //si l'ennemi se deplace de droite à gauche et qu'il rencontre le bord de la platforme ou le bord de l'écran
      this.sens = false;//alors il change de sens de déplacement
    }else if(scene.getValuePosition(posX+1, posY+1)!=2 && scene.getValuePosition(posX+1, posY+1)!=3){
      //si l'ennemi se deplace de gauche à droite et qu'il rencontre le bord de la platforme ou le bord de l'écran
      this.sens = true;//alors il change de sens de déplacement
    }
  }


  @Override
  public void run() {
    try{
      while(gameManager.getGameState().isGame()){
        chooseDirection();
        if(sens){//si le sens est à true
          this.sleep(800);
          if(scene.getValuePosition(posX-1, posY)==5){
            scene.setValuePosition(posX-2, posY, 4);//on déplace le joueur d'une case à gauche
            scene.setValuePosition(posX, posY, 0);//la position précédente de l'ennemi repasse à un espace vide
            this.posX = this.posX-2;
          }else{
            scene.setValuePosition(posX-1, posY, 4);//on déplace le joueur d'une case à gauche
            scene.setValuePosition(posX, posY, 0);//la position précédente de l'ennemi repasse à un espace vide
            this.posX--;
          }
        }
        else {//si le sens est à false
          this.sleep(800);
          if(scene.getValuePosition(posX+1, posY)==5){
            scene.setValuePosition(posX+2, posY, 4);//on déplace le joueur d'une case à gauche
            scene.setValuePosition(posX, posY, 0);//la position précédente de l'ennemi repasse à un espace vide
            this.posX = this.posX+2;
          }else{
            scene.setValuePosition(posX+1, posY, 4);//on déplace le joueur d'une case à gauche
            scene.setValuePosition(posX, posY, 0);//la position précédente de l'ennemi repasse à un espace vide
            this.posX++;
          }
        }
      }
    }catch(InterruptedException e){
          e.printStackTrace();
    }
  }
}

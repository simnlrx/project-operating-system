package thread;
import game.Scene;

public class EnemyThread extends Thread{
  private int posX;//position en x de l'ennemi
  private int posY;//position en y de l'ennemi
  private Scene scene;//scene de l'ennemi
  private boolean sens;//sens du mouvement de l'ennemi

  public EnemyThread(int posX, int posY, Scene scene){
    this.scene = scene;
    this.posX = posX;
    this.posY = posY;
    scene.setValuePosition(posX,posY,4);//les coordonnées de l'ennemi sont directement placés dans la scene
    this.sens = true;
  }

  public void chooseDirection(){
    if(scene.getValuePosition(posX-1, posY+1)==0 || scene.getValuePosition(posX-1, posY+1)==9){
      //si l'ennemi se deplace de droite à gauche et qu'il rencontre le bord de la platforme ou le bord de l'écran
      this.sens = false;//alors il change de sens de déplacement
    }else if(scene.getValuePosition(posX+1, posY+1)==0 || scene.getValuePosition(posX-1, posY+1)==9)
      //si l'ennemi se deplace de gauche à droite et qu'il rencontre le bord de la platforme ou le bord de l'écran
      this.sens = true;//alors il change de sens de déplacement
  }

  @Override
  public void run(){
    chooseDirection();
    try{
      if(sens){//si le sens est à true
        scene.setValuePosition(posX-1, posY+1, 4);//on déplace le joueur d'une case à gauche
        scene.setValuePosition(posX, posY, 0);//la position précédente de l'ennemi repasse à un espace vide
        this.posX--;//on décrémente la position en x du joueur de 1
        this.sleep(500);
      }
      else if(!sens){//si le sens est à false
        scene.setValuePosition(posX+1, posY+1, 4);//on déplace le joueur d'une case à droite
        scene.setValuePosition(posX, posY, 0);//la position précédente de l'ennemi repasse à un espace vide
        this.posX++;//on incrémente la position en x du joueur de 1
        this.sleep(500);
      }
    }
    catch(InterruptedException e){
        e.printStackTrace();
      }
  }
}

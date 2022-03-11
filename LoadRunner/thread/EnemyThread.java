package thread;
import game.Scene;

public class EnemyThread extends Thread{
  private int posX;//position en x de l'ennemi
  private int posY;//position en y de l'ennemi
  private Scene scene;//scene de l'ennemi
  private boolean sens;//sens du mouvement de l'ennemi

  public EnemyThread(int posX, int posY, boolean sens, Scene scene){
    this.scene = scene;
    this.posX = posX;
    this.posY = posY;
    scene.setValuePosition(posX,posY,4);//les coordonnées de l'ennemi sont directement placés dans la scene
    this.sens = sens;
  }

  public void chooseDirection(){
    System.out.println(scene.getValuePosition(posX-1, posY-1) + " ");
    if(scene.getValuePosition(posX-1, posY-1)!=2 || scene.getValuePosition(posX-1, posY-1)!=3){
      //si l'ennemi se deplace de droite à gauche et qu'il rencontre le bord de la platforme ou le bord de l'écran
      this.sens = !sens;//alors il change de sens de déplacement
    }else if(scene.getValuePosition(posX+1, posY-1)!=2 || scene.getValuePosition(posX+1, posY-1)!=3)
      //si l'ennemi se deplace de gauche à droite et qu'il rencontre le bord de la platforme ou le bord de l'écran
      this.sens = !sens;//alors il change de sens de déplacement
  }

  public void StairAfterEnemyPass(){
    if(scene.getValuePosition(18,11)!=3 && scene.getValuePosition(18,11)!=4){//si l'escalier1 avait disparut lors du passage d'un ennemi
      scene.setValuePosition(18,11,3);//on refix un esclaier là où il était
    }
    if(scene.getValuePosition(10,7)!=3 && scene.getValuePosition(10,7)!=4){//si l'escalier2 avait disparut lors du passage d'un ennemi
      scene.setValuePosition(10,7,3);//on refix un esclaier là où il était
    }
    if(scene.getValuePosition(22,3)!=3 && scene.getValuePosition(22,3)!=4){//si l'escalier3 avait disparut lors du passage d'un ennemi
      scene.setValuePosition(22,3,3);//on refix un esclaier là où il était
    }
  }

  @Override
  public void run(){
    try{
      System.out.println("test");
      chooseDirection();
      while(scene.getinGame()){
        if(sens){//si le sens est à true
          this.sleep(500);
          scene.setValuePosition(posX-1, posY, 4);//on déplace le joueur d'une case à gauche
          scene.setValuePosition(posX, posY, 0);//la position précédente de l'ennemi repasse à un espace vide
          this.posX--;//on décrémente la position en x du joueur de 1
        }
        else if(!sens){//si le sens est à false
          this.sleep(500);
          scene.setValuePosition(posX+1, posY, 4);//on déplace le joueur d'une case à droite
          scene.setValuePosition(posX, posY, 0);//la position précédente de l'ennemi repasse à un espace vide
          this.posX++;//on incrémente la position en x du joueur de 1
        }
        StairAfterEnemyPass();
      }
    }
    catch(InterruptedException e){
        e.printStackTrace();
    }
  }
}

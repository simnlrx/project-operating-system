package LoadRunner.thread;

import LoadRunner.game.Scene;

public class RefreshScene extends Thread{
  private Scene scene;

  public RefreshScene(Scene scene, boolean inGame){
    this.scene = scene;
  }

  @Override
  public void run(){
    while(scene.getinGame()){
      try{
        this.scene.matrix2Screen();
        scene.printMatrix();
        this.sleep(500);
      }
      catch(InterruptedException e){
            e.printStackTrace();
      }
    }
  }
}
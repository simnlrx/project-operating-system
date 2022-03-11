package fr.loadrunner.thread;

import fr.loadrunner.game.Scene;

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
        this.sleep(500);
      }
      catch(InterruptedException e){
            e.printStackTrace();
      }
    }
  }
}

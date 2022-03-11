package thread;

public class RefreshSceen extends Thread{
  private Scene scene;

  public RefreshSceen(Scene scene){
    this.scene = scene;
  }

  @Override
  public void run(){
    try{
      this.scene.matrix2Screen();
      this.sleep(100);
    }
    catch(InterruptedException e){
          e.printStackTrace();
    }
  }
}

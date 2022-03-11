package game;

public class Enemy extends Thread{
  private int posX;
  private int posY;
  private Scene scene;
  private boolean direction;

  public Enemy(int posX, int posY, Scene scene){
    this.posX = posX;
    this.posY = posY;
    this.scene = scene;
  }

  @Override
  public void run(){

  }
}

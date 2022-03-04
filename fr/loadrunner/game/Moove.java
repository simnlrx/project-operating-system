package game;

public class Moove{
  private Player player;

  public Moove(Player player){
    this.player = player;
  }
  public void setmoove(char direction){
    switch(direction){
      case 'z':{
        if(scene.getValueScene(player.getPosX, player.getPosY-1)==3){
          player.setPosition(player.getPosX, player.getPosY-1);
        }break;
      }
      case 'q':{
        if(scene.getValueScene(player.getPosX-1, player.getPosY)==0){
          player.setPosition(player.getPosX-1, player.getPosY);
        }break;
      }
      case 's':{
        if(scene.getValueScene(player.getPosX, player.getPosY+1)==0){
          player.setPosition(player.getPosX, player.getPosY+1);
        }break;
      }
      case 'd':{
        if(scene.getValueScene(player.getPosX+1, player.getPosY)==0){
          player.setPosition(player.getPosX+1, player.getPosY+1);
        }break;
      }
    }
  }
}

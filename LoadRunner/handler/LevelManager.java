package LoadRunner.handler;

import java.io.File;

public class LevelManager {
    private GameManager gameManager;
    private Scene scene;
    private File file;//= new File("../files/level1.txt");

    public LevelManager(GameManager gameManager){
      this.gameManager = gameManager;
      this.scene = gameManager.getScene;
      if(gameManager.getLevel()==1){
        this.file = new File("../files/level1.txt");
      }
      else if(gameManager.getLevel()==2){
        this.file = new File("../files/level2.txt");
      }else{
        this.file = new File("../files/level3.txt");
      }
      for(int i=0;i< scene.getHeight();i++){
        for(int y=0;y<scene.getLength();y+){

        }
      }
    }

}

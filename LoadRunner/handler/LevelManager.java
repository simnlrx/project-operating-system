package LoadRunner.handler;
import java.io.BufferedReader;
import java.io.StreamTokenizer;
import java.io.File;

public class LevelManager {
    private GameManager gameManager;
    private Scene scene;
    private File file;//= new File("../files/level1.txt");


    public LevelManager(GameManager gameManager){
      this.gameManager = gameManager;
      this.scene = gameManager.getScene;
      this.ligne = 0;
      try{
        file = new File("../files/level" + scene.getLevel() + .txt");
      }catch(Exception e){
        e.printStackTrace();
      }
      BufferedReader obj = new BufferedReader(new FileReader(file));
      StreamTokenizer st = new StreamTokenizer(obj);
      while(st.nextToken()!=StreamTokenizer.TT_EOF){
        if(st.ttype==StreamTokenizer.TT_NUMBER){
          scene.setValuePosition(int x, int y, int value);
        }
      }

}

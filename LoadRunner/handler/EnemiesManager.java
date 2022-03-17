package LoadRunner.handler;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.StreamTokenizer;
import java.io.File;
import java.util.ArrayList;

import LoadRunner.handler.GameManager;
import LoadRunner.game.Scene;
import LoadRunner.thread.EnemyThread;

public class EnemiesManager{
  private GameManager gameManager;
  private File file;
  private ArrayList<EnemyThread> enemies;
  private boolean sens;
  private int index;
  private int lenghtab;
  private int heighttab;

  public EnemiesManager(GameManager gameManager){
    this.enemies = new ArrayList<EnemyThread>();
    this.gameManager = gameManager;
    this.lenghtab = gameManager.getScene().getLength();
    this.sens = true;
    AddEnemiesList();
  }

  public void AddEnemiesList(){
    this.gameManager = gameManager;
    this.index = 0;
    try{
      file = new File("LoadRunner/files/level" + gameManager.getLevel() + ".txt");
      BufferedReader obj = new BufferedReader(new FileReader(file));
      StreamTokenizer st = new StreamTokenizer(obj);
      while(st.nextToken()!=StreamTokenizer.TT_EOF){
        if(st.ttype==StreamTokenizer.TT_NUMBER){
          //this.tab[(int)(index/lenghtab)][index%lenghtab] = (int)st.nval;
          if((int)st.nval==4){
            EnemyThread enemi = new EnemyThread(index%lenghtab, (int)(index/lenghtab), sens, gameManager.getScene(),gameManager);
            this.sens = !sens;
            enemies.add(enemi);
          }
        }
        index++;
      }
    }catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void startEnemies(){
    for(int i=0;i<enemies.size();i++){
      enemies.get(i).start();
    }
  }
}

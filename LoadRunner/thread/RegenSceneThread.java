package LoadRunner.thread;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.StreamTokenizer;
import java.io.File;

import LoadRunner.handler.GameManager;
import LoadRunner.game.Scene;

public class RegenSceneThread extends Thread{
    private int tab[][];
    private int lenghtab;
    private int heighttab;
    private int index;
    private File file;
    private GameManager gameManager;

    public RegenSceneThread(GameManager gameManager){
      this.gameManager = gameManager;
      this.lenghtab = gameManager.getScene().getLenght();
      this.heighttab = gameManager.getScene().getHeight();
      this.index = 0;
      try{
        this.tab = new int[heighttab][lenghtab];
        file = new File("LoadRunner/files/level" + gameManager.getLevel() + ".txt");
        BufferedReader obj = new BufferedReader(new FileReader(file));
        StreamTokenizer st = new StreamTokenizer(obj);
        while(st.nextToken()!=StreamTokenizer.TT_EOF){
          if(st.ttype==StreamTokenizer.TT_NUMBER){
            this.tab[(int)(index/lenghtab)][index%lenghtab] = (int)st.nval;
          }
          index++;
        }
      }catch (Exception e) {
        e.printStackTrace();
      }
    }

    @Override
    public void run(){
      try{
        while(gameManager.getGameState().isGame()){
          for(int y=0;y<heighttab;y++){
            for(int x=0;x<lenghtab;x++){
              if(gameManager.getScene().getValuePosition(x,y)==0 && this.tab[y][x]==3){
                gameManager.getScene().setValuePosition(x,y,3);
              }
            }
          }
        }
      }catch(Exception e){
            e.printStackTrace();
      }
    }
}

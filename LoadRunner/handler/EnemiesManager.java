package LoadRunner.handler;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.StreamTokenizer;
import java.io.File;
import java.util.ArrayList;

import LoadRunner.handler.ThreadManager;
import LoadRunner.handler.GameManager;

import LoadRunner.game.Scene;

import LoadRunner.thread.EnemyThread;

//classe de gestion des threads ennemies
public class EnemiesManager{
  private GameManager gameManager;
  private File file;
  private ThreadManager threadManager;
  private boolean sens;
  private int index;
  private int lenghtab;
  private int heighttab;

  public EnemiesManager(GameManager gameManager, ThreadManager threadManager){
    this.threadManager = threadManager;//liste des threads
    this.gameManager = gameManager;//l'attribut gameManager récuépère le gameManager passé en parametre
    this.lenghtab = gameManager.getScene().getLenght();//on recupère la longueur du tableau
    this.sens = true;//on choisis un sens lors de l'instanciation
    AddEnemiesList();//on appel la méthode pour ajouter les ennemies dans la ArrayList
  }

  //méthode qui va parcourir l'ensemble du fichier d'initialisation et créer des ennemies en fonction de celui-ci
  public void AddEnemiesList(){
    this.index = 0;//index pour le parcour du fichier
    try{
      file = new File("LoadRunner/files/level" + gameManager.getLevel() + ".txt");//on récupère le fichier de création du niveau
      BufferedReader obj = new BufferedReader(new FileReader(file));//instanciation d'un BufferedReaders
      StreamTokenizer st = new StreamTokenizer(obj);//instanciation d'un StreamTokenizer
      while(st.nextToken()!=StreamTokenizer.TT_EOF){//parcours du fichier jusqu'à sa fin
        if(st.ttype==StreamTokenizer.TT_NUMBER){//si le mot courant est un nombre
          if((int)st.nval==4){//si l'entier est 4, il s'agit d'un ennemi
          //alors on instancie un objet EnemyThread avec en parametre, son numéro de colonne, son numéro de ligne, son sens de deplacement, sa scene et l'état le gameManager
            EnemyThread enemi = new EnemyThread(index%lenghtab, (index/lenghtab), sens, gameManager);
            //on change le sens de déplacement de l'ennemi
            this.sens = !sens;
            //on ajoute l'ennemi à la liste
            threadManager.addThread(enemi);
          }
        }
        index++;
      }
    }catch (Exception e) {
      e.printStackTrace();
    }
  }
}

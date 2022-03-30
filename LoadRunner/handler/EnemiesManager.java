package LoadRunner.handler;

import LoadRunner.thread.EnemyThread;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.StreamTokenizer;

//classe de gestion des threads ennemies

public class EnemiesManager {
    private GameManager gameManager;
    private File file;
    private ThreadManager threadManager;
    private int index;
    private int lenghtab;
    private int heighttab;

    /*
     * Constructeur de EnemiesManager
     * @param gameManager GameManager du jeu
     * @param threadManager ThreadManager liste de thread du jeu
     */

    public EnemiesManager(GameManager gameManager, ThreadManager threadManager) {
        this.threadManager = threadManager;
        //liste des threads
        this.gameManager = gameManager;
        //l'attribut gameManager récuépère le gameManager passé en parametre
        this.lenghtab = gameManager.getScene().getLenght();
        //on recupère la longueur du tableau
        AddEnemiesList();
        //on appel la méthode pour ajouter les ennemies dans la ArrayList
    }


    public void AddEnemiesList() {
      // méthode qui va parcourir l'ensemble du fichier d'initialisation et créer des ennemies en fonction de celui-ci
        this.index = 0;
        // index pour le parcour du fichier
        try {
            file = new File("LoadRunner/files/level" + gameManager.getLevel() + ".txt");
            // on récupère le fichier de création du niveau
            BufferedReader obj = new BufferedReader(new FileReader(file));
            // instanciation d'un BufferedReaders
            StreamTokenizer st = new StreamTokenizer(obj);
            // instanciation d'un StreamTokenizer
            while (st.nextToken() != StreamTokenizer.TT_EOF) {
              // parcours du fichier jusqu'à sa fin
                if (st.ttype == StreamTokenizer.TT_NUMBER) {
                  / /si le mot courant est un nombre
                    if ((int) st.nval == 4) {
                      // si l'entier est 4, il s'agit d'un ennemi
                        EnemyThread enemi = new EnemyThread(index % lenghtab, (index / lenghtab), gameManager);
                        // alors on instancie un objet EnemyThread avec en parametre, son numéro de colonne, son numéro de ligne, sa scene et l'état le gameManager
                        threadManager.addThread(enemi);
                        // on ajoute l'ennemi à la liste
                    }
                }
                index++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

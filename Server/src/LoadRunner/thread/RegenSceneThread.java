package LoadRunner.thread;

import LoadRunner.handler.GameManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.StreamTokenizer;

public class RegenSceneThread extends Thread {
    private int tab[][];
    // tableau à 2 dimensions d'entiers
    private int lenghtab;
    // longueur du tableau
    private int heighttab;
    // hauteur du tableau
    private int index;
    // index pour le tableau
    private File file;
    // fichier du niveau
    private GameManager gameManager;
    // GameManager

    /*
     * Constructeur de RegenSceneThread
     * @param GameManager gameManager
     */

    public RegenSceneThread(GameManager gameManager) {
      // méthode permettant d'initialiser le tableau tab avec les valeurs comprises dans le fichier du niveau
        this.gameManager = gameManager;
        this.lenghtab = gameManager.getScene().getLenght();
        this.heighttab = gameManager.getScene().getHeight();
        this.index = 0;
        try {
            this.tab = new int[heighttab][lenghtab];
            file = new File("LoadRunner/files/level" + gameManager.getLevel() + ".txt");
            BufferedReader obj = new BufferedReader(new FileReader(file));
            StreamTokenizer st = new StreamTokenizer(obj);
            while (st.nextToken() != StreamTokenizer.TT_EOF) {
                if (st.ttype == StreamTokenizer.TT_NUMBER) {
                    this.tab[index / lenghtab][index % lenghtab] = (int) st.nval;
                }
                index++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
      // méthode qui va permettre de comparer le tableau tab et les valeurs de la scene pour regénérer les blocs, les échelles et les passerelleaprès le passage d'un ennemi ou d'un joueur
        try {
            while (gameManager.getGameState().isGame()) {
                for (int y = 0; y < heighttab; y++) {
                    for (int x = 0; x < lenghtab; x++) {
                        if (gameManager.getScene().getValuePosition(x, y) == 0 && this.tab[y][x] == 3) {
                            gameManager.getScene().setValuePosition(x, y, 3);
                        }
                        if (gameManager.getScene().getValuePosition(x, y) == 0 && this.tab[y][x] == 6) {
                            gameManager.getScene().setValuePosition(x, y, 6);
                        }
                        if (gameManager.getScene().getValuePosition(x, y) == 0 && this.tab[y][x] == 12) {
                            gameManager.getScene().setValuePosition(x, y, 12);
                        }
                    }
                }
                if(gameManager.getScene().getPosXSpawnPlayer()==gameManager.getPlayer1().getPosX() && gameManager.getScene().getPosYSpawnPlayer()==gameManager.getPlayer1().getPosY()){
                  // vérification si le joueur ne se trouve pas sur la porte de sortie du niveau
                  gameManager.nextLevel();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

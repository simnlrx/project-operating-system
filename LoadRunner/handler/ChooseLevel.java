package LoadRunner.handler;

public class ChooseLevel{
    private GameManager gameManager;
    private int height; //Hauteur de l'écran
    private int length; //Largeur de l'écran
    private String board[][]; //scene représentée par une matrice 2*2

    public ChooseLevel(int height, int length, GameManager gameManager){
      this.board = new String[height][length];//instanciation  d'une matrice 2x2 de String
      this.gameManager = gameManager;
      String choose = "Choose your level"
      String level1 = "1 - Easy         ";//niveau facile
      String level2 = "2 - Medium       ";//niveau moyen
      String level3 = "3 - Hard         ";//niveau difficile
      initLoadingScene();
      for(int z = 0;  z < 17; z ++) {//affichage des différentes chaines de caracteres dans le board
        board[height-14][7+z] = choose.charAt(z)+" ";
        board[height-10][7+z] = level1.charAt(z)+" ";
        board[height-8][7+z] = level2.charAt(z)+" ";
        board[height-6][7+z] = level3.charAt(z)+" ";
      }
      selectGameMode();
    }

    public void initLoadingScene(){//initialise uen scene vide pour le chargement chargement
      for (int i = 0; i < this.height; i++) {
          for (int y = 0; y < this.length; y++) {
              //déclarations des bordures du terrain
              if (i == 0 || i == (this.height - 1)) {
                  board[i][y] = "–-";
              } else if (y == 0 || y == (this.length - 1)) {
                  board[i][y] = "||";
              } else {
                  board[i][y] = "  ";
              }
          }
      }
    }

    public int selectGameMode(){//fonction qui permet de rentrer un entier et de retourner le mode de jeu
      int levelMode;
      String pressenter;
      this.printLoading();
      Scanner sc1 = new Scanner(System.in);
      levelMode = sc1.nextInt();
      while(levelMode!=1 && levelMode!=2 && levelMode!=3){
        this.printLoading();
        levelMode = sc1.nextInt();
      }
      gameManager.setLevel(levelMode);
      System.out.println("\033[H\033[2J");//supprime tout ce qu'il y a dans la console auparavant
    }
}

package game;
import java.util.Scanner;

public class Loading{
    private int height; //Hauteur de l'écran
    private int length; //Largeur de l'écran
    private String board[][]; //scene représentée par une matrice 2*2

    public Loading(int height, int length){
      this.height = height;//Hauteur de l'écran
      this.length = length;//Largeur de l'écran
      String gamemode = "Select your Game Mode:";//String de gamemode
      String solo = "1 - Local Game        ";//String de solo
      String multi = "2 - Online Game       ";//String de multi
      this.board = new String[height][length];//instanciation  d'une matrice 2x2 de String
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
      for(int z = 0;  z < 22; z ++) {//affichage des différentes chaines de caracteres dans le board
          board[height-12][7+z] = gamemode.charAt(z)+" ";
          board[height-10][10+z] = solo.charAt(z)+" ";
          board[height-8][10+z] = multi.charAt(z)+" ";
      }
    }

    public void printLoading() {//affichage de board
        for (int i = 0; i < (this.height); i++) {
            for (int y = 0; y < (this.length); y++) {
                System.out.print(board[i][y]);
            }
            System.out.print("\n");
        }
    }

    public int selectGameMode(){//fonction qui permet de rentrer un entier et de retourner le mode de jeu
        this.printLoading();
        int gamemode;
        Scanner sc = new Scanner(System.in);
        gamemode = sc.nextInt();
        while(gamemode!=1 && gamemode!=2){
          this.printLoading();
          gamemode = sc.nextInt();
        }
        return gamemode;
    }
}

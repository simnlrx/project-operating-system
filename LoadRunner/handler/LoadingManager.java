package LoadRunner.handler;

import java.util.Scanner;

public class LoadingManager {
    private int height; //Hauteur de l'écran
    private int length; //Largeur de l'écran
    private String board[][]; //scene représentée par une matrice 2*2

    public LoadingManager(int height, int length){
      this.height = height;//Hauteur de l'écran
      this.length = length;//Largeur de l'écran
      String loderunner = "Lode Runner           ";
      String software = "by best software inc  ";
      String gamemode = "Select your Game Mode:";//String de gamemode
      String solo = "1 - Local Game        ";//String de solo
      String multi = "2 - Online Game       ";//String de multi
      String copyright1 = "copyright ©2022 B GOUN";
      String copyright2 = "copyright ©2022 S LERX";
      String licence = "licenced by           ";
      String ufr = "NINTUFR OF FRANCE INC ";
      this.board = new String[height][length];//instanciation  d'une matrice 2x2 de String
      initLoadingScene();


      for(int z = 0;  z < 22; z ++) {//affichage des différentes chaines de caracteres dans le board
          board[height-16][10+z] = loderunner.charAt(z)+" ";
          board[height-15][7+z] = software.charAt(z)+" ";
          board[height-12][7+z] = gamemode.charAt(z)+" ";
          board[height-10][10+z] = solo.charAt(z)+" ";
          board[height-8][10+z] = multi.charAt(z)+" ";
          board[height-5][7+z] = copyright1.charAt(z)+" ";
          board[height-4][7+z] = copyright2.charAt(z)+" ";
          board[height-3][10+z] = licence.charAt(z)+" ";
          board[height-2][7+z] = ufr.charAt(z)+" ";
      }
    }

    public void loadingStage(int gamemode){//affichage de l'écran de chargement
      System.out.println("\033[H\033[2J");//supprime tout ce qu'il y a dans la console auparavant
      String solo =  "PLAYER 1               ";
      String multi = "PLAYER 2               ";
      String stage = "STAGE 1 - 3 LIVES      ";
      String score = "SCORE   000000000      ";
      String hiscore = "HISCORE 000000000      ";
      String pressenter= "press enter to continue";
      this.initLoadingScene();


      if(gamemode==1){
        for(int z = 0;  z < 17; z ++) {//affichage des différentes chaines de caracteres dans le board
          board[height-12][7+z] = solo.charAt(z)+" ";
          board[height-9][7+z] = stage.charAt(z)+" ";
          board[height-6][7+z] = score.charAt(z)+" ";
          board[height-5][7+z] = hiscore.charAt(z)+" ";
          board[height-3][7+z] = pressenter.charAt(z)+" ";
        }
        this.printLoading();
      }else if(gamemode==2){
        for(int z = 0;  z < 17; z ++) {//affichage des différentes chaines de caracteres dans le board
          board[height-12][9+z] = solo.charAt(z)+" ";
          board[height-11][9+z] = multi.charAt(z)+" ";
          board[height-9][7+z] = stage.charAt(z)+" ";
          board[height-6][7+z] = score.charAt(z)+" ";
          board[height-5][7+z] = hiscore.charAt(z)+" ";
          board[height-3][5+z] = pressenter.charAt(z)+" ";
        }
        this.printLoading();
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
        int gamemode;
        String pressenter;
        this.printLoading();
        Scanner sc1 = new Scanner(System.in);
        gamemode = sc1.nextInt();
        while(gamemode!=1 && gamemode!=2){
          this.printLoading();
          gamemode = sc1.nextInt();
        }
        this.loadingStage(gamemode);
        Scanner sc2 = new Scanner(System.in);
        pressenter = sc2.nextLine();
        while(pressenter!=""){
          this.loadingStage(gamemode);
        }
        return gamemode;
    }
}

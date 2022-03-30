package LoadRunner.game;

// -- Lexique des différents entiers avec leur représentation graphique --

// 0 - représente un espace vide dans la scene
// 1 - représente les contours horizontaux de l'écran
// 2 - représente un morceau de platforme
// 3 - représente un morceau d'une échelle
// 4 - représente un ennemi
// 5 - représente un objet à récupérer

// 6 - spawn d'un joueur
// 9 - représente les contours vertiacaux de l'écran
// 10 - représente le joueur courant
// 11 - représente le joueur en multijoueur
// 12 - paserelle
// 13 - spawn ennemie
// 14 - case vide où les méchants tombent
// 15 - niveau suivant



public class Scene {
    private final int height; //Hauteur de l'écran
    private final int lenght; //Largeur de l'écran
    private final int[][] board; //scene représentée par une matrice 2*2

    private int posXSpawnEnemy;// position en X du spawn ennemi
    private int posYSpawnEnemy;// position en Y du spawn ennemi

    private int posXSpawnPlayer1;// position en X du spawn du joueur 1
    private int posYSpawnPlayer1;// position en Y du spawn du joueur 1

    private Player player1 = new Player(0, "Player1", 1);//joueur1
    private Player player2 = new Player(0, "Player2", 2);//joueur2


    public Scene(int height, int lenght) {
      //constructeur de Scene
        this.height = height;
        this.lenght = lenght;
        this.board = new int[height][lenght];
        this.setScene();

    }


    public void setScene() {
      //méthode qui permet d'initialiser la scene sous forme brut
        for (int i = 0; i < this.height; i++) {
            for (int y = 0; y < this.lenght; y++) {
                //déclarations des bordures du terrain
                if (i == 0 || i == (this.height - 1)) {
                    board[i][y] = 1;
                } else if (y == 0 || y == (this.lenght - 1)) {
                    board[i][y] = 9;
                } else {
                    board[i][y] = 0;
                }
            }
        }
    }


    public void printMatrix() {
      //fonction permettant d'afficher la matrice de l'écran
        for (int i = 0; i < (this.height); i++) {
            for (int y = 0; y < (this.lenght); y++) {
                System.out.print(board[i][y] + " ");
            }
            System.out.print("\n");
        }
    }

    public synchronized void matrix2Screen() {
      //fonction permettant d'afficher l'écran à partir de la matrice
        System.out.println("\033[H\033[2J");//supprime tout ce qu'il y a dans la console auparavant
        int value;
        for (int i = 0; i < (this.height); i++) {//parcours de la matrice en y
            for (int y = 0; y < (this.lenght); y++) {//parcours de la matrice en x
                value = board[i][y];
                switch (value) {
                    case 0:
                    case 13 :
                    case 14 :
                    case 6: {
                        System.out.print("  ");
                        break;
                    }//espace vide
                    case 1:
                    case 2:
                    case 9: {
                        System.out.print("▓▓");
                        break;
                    }//bord horizontal
                    case 15:
                    // porte de sortie du niveau
                    case 3: {
                        System.out.print("│┤");
                        break;
                    }//échelle
                    case 4: {
                        System.out.print("☠ ");
                        break;
                    }//simulation d'un ennemi en attente d'un symbole
                    case 5: {
                        System.out.print("☼ ");
                        break;
                    }//simulation d'un objet
                    case 10: {
                        System.out.print("J ");
                        break;
                    }//simulation du joueur courant en attendant un symbole
                    case 11: {
                        System.out.print("P ");
                        break;
                    }//simulation du joueur 2 en attendant un symbole
                    case 12 : {
                        System.out.print("__");
                        break;
                    }
                }

            }
            System.out.print("\n");
        }

        if ((player2.getName()).equals("")) {//si un deuxieme joueur n'est présent dans la partie
            System.out.println("Score " + player1.getName() + ": " + player1.getScore() + printLife(player1));//affichage du nom et du score du joueur1
        } else {//sinon affichage du nom et du score du joueur1 et du joueur2
            System.out.println("Score " + player1.getName() + ": " + player1.getScore() + printLife(player1));
            System.out.println(player2.getName() + ": " + "unlimited life, kill player 1");//affichage du nom et du score du joueur
        }
        //out+="<html/>";
        //return out;
    }

    public void set1Player(Player player1) {
      //ajoute un joueur à la scene
        this.player1 = player1;
        this.player2 = new Player(0, "", 2);
        for (int i = 0; i < (this.height); i++) {//parcours de la matrice en y
            for (int j = 0; j < (this.lenght); j++) {//parcours de la matrice en x
                if(board[i][j] == 6){
                    setPositionPlayer(player1, j, i);
                }
            }
        }
    }

    public synchronized void reSpawnPlayer1(){
      //méthode pour respawn le joueur 1 dans la scene
      this.setValuePosition(player1.getPosX(), player1.getPosY(), 4);
      player1.setPosition(0,0);
      int Platforme = this.getHeight()-2;
      int spawnX = 0;
      try{
        wait(2000);
        player1.getKill();
        do{
          spawnX = (int)(Math.random()*this.getLenght()+1);
        }while(this.getValuePosition(spawnX,Platforme+1)!=2 || this.getValuePosition(spawnX,Platforme)==2);
        player1.setPosition(spawnX, Platforme);
        this.setValuePosition(spawnX, Platforme, 10);
      } catch (Exception e) {
        e.printStackTrace();
    }
  }


    public String printLife(Player player) {
      //méthode permettant d'afficher la vie des joueurs
        String life = "   ";
        for (int i = 0; i < player.getLife(); i++) {
            life += "♥";
        }
        return life;
    }

    public synchronized void setValuePosition(int x, int y, int value) {
      //méthode permettant de metre une valeur dans le tableau
        board[y][x] = value;
    }

    public synchronized void setPositionPlayer(Player player, int x, int y) {
      //méthode permettant de changer la position du joueur
        if (player.getNumber() == 1) {
            board[y][x] = 10;
            player.setPosition(x, y);
        } else if (player.getNumber() == 2) {
            board[y][x] = 11;
            player.setPosition(x, y);
        }
    }

    public int getValuePosition(int x, int y) {
      //méthode permettant de récupérer la valeur de la position et de tester si c'est dans le tableau
        try{
            return board[y][x];
        }catch (ArrayIndexOutOfBoundsException e){
            return -1;
        }
    }

    public void set2Players(Player player1, Player player2) {
      //ajoute deux joueurs à la scene
        this.player1 = player1;
        this.player2 = player2;
    }


    public int getHeight() {
      // méthode permettant de récupérer la hauteur du plateau
        return this.height;
    }

    public int getLenght() {
      //méthode permettant de récupérer la longueur du tableau
        return this.lenght;
    }


    public Player getPlayer1() {
      //getter pour le premier joueur
        return this.player1;
    }

    public Player getPlayer2() {
      //getter pour le second joueur
        return this.player2;
    }

    public void setPosXSpawnEnemy(int x){
      //setter pour le spawn en X des ennemis
      this.posXSpawnEnemy = x;
    }

    public void setPosYSpawnEnemy(int y){
      //setter pour le spawn en Y des ennemis
      this.posYSpawnEnemy = y;
    }

    public int getPosXSpawnEnemy(){
      //getter pour le spawn en X des ennemis
      return this.posXSpawnEnemy;
    }

    public int getPosYSpawnEnemy(){
      //getter pour le spawn en Y des ennemis
      return this.posYSpawnEnemy;
    }

    public void setPosXSpawnPlayer1(int x){
      //setter pour le spawn en X du joueur
      this.posXSpawnPlayer1 = x;
    }

    public void setPosYSpawnPlayer1(int y){
      //setter pour le spawn en Y du joueur
      this.posYSpawnPlayer1 = y;
    }

    public int getPosXSpawnPlayer1(){
      //getter pour le spawn en X du joueur
      return this.posXSpawnPlayer1;
    }

    public int getPosYSpawnPlayer1(){
      //getter pour le spawn en Y du joueur
      return this.posYSpawnPlayer1;
    }

}

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

// 20 - fleche vers le haut
// 21 - fleche vers la gauche
// 22 - fleche vers la droite
// 23 - fleche vers le bas


public class Scene {
    private final int height; //Hauteur de l'écran
    private final int lenght; //Largeur de l'écran
    private final int[][] board; //scene représentée par une matrice 2*2
    private Player player1 = new Player(100, "Player1", 1);
    private Player player2 = new Player(100, "Player2", 2);

    //constructeur de Scene
    public Scene(int height, int lenght) {//constrcuteur de la scene
        this.height = height;
        this.lenght = lenght;
        this.board = new int[height][lenght];
        this.setScene();
    }


    //méthode qui permet d'initialiser la scene sous forme brut
    public void setScene() {
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


    //fonction permettant d'afficher la matrice de l'écran
    public void printMatrix() {
        for (int i = 0; i < (this.height); i++) {
            for (int y = 0; y < (this.lenght); y++) {
                System.out.print(board[i][y] + " ");
            }
            System.out.print("\n");
        }
    }

    //fonction permettant d'afficher l'écran à partir de la matrice
    public synchronized void matrix2Screen() {
        System.out.println("\033[H\033[2J");//supprime tout ce qu'il y a dans la console auparavant
        int value;
        for (int i = 0; i < (this.height); i++) {//parcours de la matrice en y
            for (int y = 0; y < (this.lenght); y++) {//parcours de la matrice en x
                value = board[i][y];
                switch (value) {
                    case 0: {
                        System.out.print("  ");
                        break;
                    }//espace vide
                    case 1: {
                        System.out.print("–-");
                        break;
                    }//bord horizontal
                    case 2: {
                        System.out.print("▓▓");
                        break;
                    }//platforme
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
                    case 6: {
                        System.out.print("⚑ ");
                        //out+="⚑";
                        break;
                    }//simulation du spawn du joueur
                    case 9: {
                        System.out.print("||");
                        break;
                    }//bord vertical
                    case 10: {
                        System.out.print("J ");
                        break;
                    }//simulation du joueur courant en attendant un symbole
                    case 11: {
                        System.out.print("P ");
                        break;
                    }//simulation du joueur 2 en attendant un symbole
                    case 20: {
                        System.out.print("↑ ");
                        break;
                    }
                    case 21: {
                        System.out.print("← ");
                        break;
                    }
                    case 22: {
                        System.out.print("→ ");
                        break;
                    }
                    case 23: {
                        System.out.print("↓ ");
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
            System.out.println("Score " + player2.getName() + ": " + player2.getScore() + printLife(player2));//affichage du nom et du score du joueur
        }
        //out+="<html/>";
        //return out;
    }

    //méthode permettant d'afficher la vie des joueurs
    public String printLife(Player player) {
        String life = "   ";
        for (int i = 0; i < player.getLife(); i++) {
            life += "♥";
        }
        return life;
    }

    //méthode permettant de metre une valeur dans le tableau
    public synchronized void setValuePosition(int x, int y, int value) {
        board[y][x] = value;
    }

    //méthode permettant de changer la position du joueur
    public synchronized void setPositionPlayer(Player player, int x, int y) {
        if (player.getNumber() == 1) {
            board[y][x] = 1;
            player.setPosition(x, y);
        } else if (player.getNumber() == 2) {
            board[y][x] = 2;
            player.setPosition(x, y);
        }
    }

    // méthode permettant de récupérer la hauteur du plateau
    public int getHeight() {
        return this.height;
    }

    //méthode permettant de récupérer la longueur du tableau
    public int getLenght() {
        return this.lenght;
    }

    //méthode permettant de récupérer la valeur de la position et de tester si c'est dans le tableau
    public int getValuePosition(int x, int y) {
        try{
            return board[y][x];
        }catch (ArrayIndexOutOfBoundsException e){
            return -1;
        }
    }

    //ajoute un joueur à la scene
    public void set1Player(Player player1) {
        this.player1 = player1;
        this.player2 = new Player(0, "", 2);
        printMatrix();
        for (int i = 0; i < (this.height); i++) {//parcours de la matrice en y
            for (int j = 0; j < (this.lenght); j++) {//parcours de la matrice en x
                if(board[i][j] == 10){
                    player1.setPosition(j,i);
                }
            }
        }
        System.out.println(player1.getPosX() + " " + player1.getPosY());
    }

    //ajoute deux joueurs à la scene
    public void set2Players(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    //getter pour le premier joueur
    public Player getPlayer1() {
        return this.player1;
    }

    //getter pour le second joueur
    public Player getPlayer2() {
        return this.player2;
    }

}

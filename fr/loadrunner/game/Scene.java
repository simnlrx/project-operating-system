package game;


// -- Lexique des différents entiers avec leur représentation graphique --
// 0 - représente un espace vide dans la scene
// 1 - représente les contours horizontaux de l'écran
// 2 - représente un morceau de platforme
// 3 - représente un morceau d'une échelle
// 4 - représente un ennemi
// 5 - représente un objet à récupérer
// 6 - spawn d'un joueur
// 7 - représente le spawn d'un ennemi
// 9 - représente les contours vertiacaux de l'écran
// 10 - représente le joueur courant
// 11 - représente le joueur en multijoueur


public class Scene {
    private int height; //Hauteur de l'écran
    private int length; //Largeur de l'écran
    private int board[][]; //scene représentée par une matrice 2*2
    private int baseStairsY;//pointeur selon l'axe Y
    private int baseStairsX;//pointeur selon l'axe X
    private Player player1;//déclaration du joueur1
    private Player player2;//déclaration du joueur2
    private boolean inGame;//boolean qui permet de savoir si le jue est en cours


    //constructeur de Scene avec un seul joueur
    public Scene(int height, int length, Player player1, boolean inGame) {
        this.player1 = player1;
        this.player2 = new Player(0, "",2);
        this.height = height;
        this.length = length;
        this.inGame = inGame;
        this.board = new int[height][length];
        //initialisation d'une Scene remplies préalablement de 0
        for (int i = 0; i < this.height; i++) {
            for (int y = 0; y < this.length; y++) {
                //déclarations des bordures du terrain
                if (i == 0 || i == (this.height - 1)) {
                    board[i][y] = 1;
                } else if (y == 0 || y == (this.length - 1)) {
                    board[i][y] = 9;
                } else {
                    board[i][y] = 0;
                }
            }
        }
    }

    //constructeur de scene avec le joueur 1 et le joueur2
    public Scene(int height, int length, Player player1, Player player2, boolean inGame) {
        this.player1 = player1;
        this.player2 = player2;
        this.height = height;
        this.length = length;
        this.board = new int[height][length];
        for (int i = 0; i < this.height; i++) {
            for (int y = 0; y < this.length; y++) {
                //déclarations des bordures du terrain
                if (i == 0 || i == (this.height - 1)) {
                    board[i][y] = 1;
                } else if (y == 0 || y == (this.length - 1)) {
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
            for (int y = 0; y < (this.length); y++) {
                System.out.print(board[i][y] + " ");
            }
            System.out.print("\n");
        }
    }

    //fonction permettant d'afficher l'écran à partir de la matrice
    public void matrix2Screen() {
        System.out.println("\033[H\033[2J");//supprime tout ce qu'il y a dans la console auparavant
        int value;
        for (int i = 0; i < (this.height); i++) {//parcours de la matrice en y
            for (int y = 0; y < (this.length); y++) {//parcours de la matrice en x
                value = board[i][y];
                switch (value) {
                    case 0: {System.out.print("  ");break;}//espace vide
                    case 1: {System.out.print("–-");break;}//bord horizontal
                    case 2: {System.out.print("▓▓");break;}//platforme
                    case 3: {System.out.print("│┤");break;}//échelle
                    case 4: {System.out.print("EN");break;}//simulation d'un ennemi en attente d'un symbole
                    case 5: {System.out.print("☼☼");break;}//simulation d'un objet
                    case 6: {System.out.print("SJ");break;}//simulation du spawn du joueur
                    case 7: {System.out.print("SE");break;}//simulation du spawn d'un ennemi
                    case 9: {System.out.print("||");break;}//bord vertical
                    case 10: {System.out.print("J1");break;}//simulation du joueur courant en attendant un symbole
                    case 11: {System.out.print("J2");break;}//simulation du joueur 2 en attendant un symbole
                }
            }
            System.out.print("\n");
        }
        if ((player2.getName()).equals("")) {//si un deuxieme joueur n'est présent dans la partie
            System.out.println("Score " + player1.getName() + ": " + player1.getScore());//affichage du nom et du score du joueur1
        } else {//sinon affichage du nom et du score du joueur1 et du joueur2
            System.out.println("Score " + player1.getName() + ": " + player1.getScore() + "   Score " + player2.getName() + ": " + player2.getScore());//affichage du nom et du score du joueur
        }
    }

    public void genSceenLevel1() {//fonction qui va permettre de générer un niveau prédéfinis
        this.baseStairsY = this.height - 2;//declaration d'une base d'escalier
        int basePlat = baseStairsY;
        int baseStair = baseStairsY;
        for (int i = 1; i < 18; i++) {//génération d'une premier platforme
            board[basePlat][i] = 2;
        }
        basePlat -= 4;
        for (int i = 16; i < 30; i++) {//génération d'une deuxieme platforme
            board[basePlat][i] = 2;
        }
        basePlat -= 4;
        for (int i = 2; i < 26; i++) {//génération d'une troisieme platforme
            board[basePlat][i] = 2;
        }
        basePlat -= 4;
        for (int i = 5; i < 30; i++) {//génération d'une quatrieme platforme
            board[basePlat][i] = 2;
        }
        for (int i = 1; i < 5; i++) {//generation premier esclaier
            board[baseStair - i][16] = 3;
        }
        baseStair -= 4;
        for (int i = 1; i < 5; i++) {//generation deuxieme esclaier
            board[baseStair - i][18] = 3;
        }
        baseStair -= 4;
        for (int i = 1; i < 5; i++) {//generation troisieme esclaier
            board[baseStair - i][10] = 3;
        }
        baseStair -= 4;
        for (int i = 1; i < 5; i++) {//generation quatrieme esclaier
            if (board[baseStair - i][22] != 1) {
                board[baseStair - i][22] = 3;
            }
        }
        board[baseStairsY - 1][2] = 6;
    }

    public void setValuePosition(int x, int y, int value){
        board[y][x] = value;
    }

    public void setPositionPlayer(Player player, int x, int y){
      if(player.getNumber()==1){
        board[y][x] = 1;
        player.setPosition(x, y);
      }
      else if(player.getNumber()==2){
        board[y][x] = 2;
        player.setPosition(x, y);
      }
    }

    public int getValuePosition(int x,int y){
      return board[y][x];
    }

    public boolean getinGame(){
      return this.inGame;
    }
}

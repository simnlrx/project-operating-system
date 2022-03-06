package game;


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


public class Scene {
    private int height; //Hauteur de l'écran
    private int length; //Largeur de l'écran
    private int board[][]; //scene représentée par une matrice 2*2
    private int baseStairsY;//pointeur selon l'axe Y
    private int baseStairsX;//pointeur selon l'axe X
    private Player player1;//déclaration du joueur1
    private Player player2;//déclaration du joueur2


    //constructeur de Scene
    public Scene(int height, int length, Player player1) {
        this.player1 = player1;
        this.player2 = new Player(0, "",2);
        this.height = height;
        this.length = length;
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

    public Scene(int height, int length, Player player1, Player player2) {
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
        for (int i = 0; i < (this.height); i++) {
            for (int y = 0; y < (this.length); y++) {
                value = board[i][y];
                switch (value) {
                    case 0: {System.out.print("  ");break;}//espace vide
                    case 1: {System.out.print("–-");break;}//bord horizontal
                    case 2: {System.out.print("▓▓");break;}//platforme
                    case 3: {System.out.print("│┤");break;}//échelle
                    case 4: {System.out.print("4 ");break;}//simulation d'un ennemi en attente d'un symbole
                    case 5: {System.out.print("☼ ");break;}//simulation d'un objet
                    case 6: {System.out.print("S ");break;}//simulation du spawn
                    case 9: {System.out.print("||");break;}//bord vertical
                    case 10: {System.out.print("1 ");break;}//simulation du joueur courant en attendant un symbole
                    case 11: {System.out.print("2 ");break;}//simulation du joueur 2 en attendant un symbole
                }
            }
            System.out.print("\n");
        }
        if ((player2.getName()).equals("")) {
            System.out.println("Score " + player1.getName() + ": " + player1.getScore());//affichage du nom et du score du joueur
        } else {
            System.out.println("Score " + player1.getName() + ": " + player1.getScore() + "   Score " + player2.getName() + ": " + player2.getScore());//affichage du nom et du score du joueur
        }
    }

    public void genSceenLevel1() {//fonction qui va pemrettre de générer un niveau prédéfinis
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

    //fonction permettant de générée la scene principale du jeu
    public void genSceneRandom() {
        baseStairsX = (int) (Math.random() * 32) + 1;//premier random permetant de trouver la base du premier escalier
        baseStairsY = this.height - 2;//emplacement du stage n°1
        genPlatform(baseStairsX, baseStairsY);//appel de la fonction genPlatform
    }

    public void genStairs(int baseStairsY, int baseStairsX) {//fonction test pour la génération d'escaliers
        for (int i = 1; i < 5; i++) {
            if (board[baseStairsY + i][baseStairsX] != 1) {
                board[baseStairsY + i][baseStairsX] = 3;
            }
        }
    }

    //fonction qui va permettre de générée des platformes aléatoirement
    //renvoie selon l'axe x, l'endroit de la prochaine base de l'escalier
    public void genPlatform(int baseStairsX, int baseStairsY) {
        int stage = baseStairsY;//nombres de niveaux restants
        int stairsBase = 0;//base de l'échelle courrante
        int nbPlatform;//créé un nombre aléatoire de parties d'une platforme
        int edegeOfPlat;//pointeur qui va pointer l'endroit de la génération d'une platforme
        boolean direction = true;
        do {
            if (direction) {//création de platformes de gauche à droite
                nbPlatform = (int) (Math.random() * 24) + 8;
                stairsBase = (int) (Math.random() * nbPlatform);
                System.out.println(direction);
                if (board[stage][baseStairsX - 3] == 9 || board[stage][baseStairsX - 2] == 9 || board[stage][baseStairsX - 1] == 9 || board[stage][baseStairsX] == 9) {//Si un bord est sur le chemin de la création de la platforme
                    edegeOfPlat = baseStairsX + 3;//alors il n'y a pas de bord gauche
                } else {// si il n'y a pas de bord dans sur le chemin de la généaration des platformes
                    edegeOfPlat = baseStairsX - 2;//le bord se situe à gauche de la platforme
                    while (nbPlatform > 0) {//tant qu'il y a des platformes de disponible, création d'une nouvelle partie de la platforme
                        if (board[stage][edegeOfPlat] == 9) {
                            nbPlatform = 0;//Avant de commencer la génératio, si le pointeur pointe ver un bord, on stop la génération
                        } else if (board[stage][edegeOfPlat] == 3) {//si la génération de platforme rencontre un escalier, il avance d'un cran
                            edegeOfPlat++;//on passe l'escalier
                        } else if (board[stage][edegeOfPlat] != 3) {//si il n'y a pas d'escalier à l'endroit actuel et pas de bord
                            board[stage][edegeOfPlat] = 2;//la case de la matrice prend la valeur 2 (une platforme)
                            edegeOfPlat++;//on incrémente la base de la platforme poiur la décaler de 1 unitée à gauche
                            nbPlatform--;//on enleve 1 platforme du nombre de platforme à posées
                        }
                    }
                }
                //genStairs(stage, stairsBase);
            } else {//création de platformes de droite à gauche
                System.out.println(direction);
                nbPlatform = (int) (Math.random() * 24) + 8;
                if (board[stage][baseStairsX + 3] == 9 || board[stage][baseStairsX + 2] == 9 || board[stage][baseStairsX + 1] == 9 || board[stage][baseStairsX] == 9) {//Si un bord est sur le chemin de la création de la platforme
                    edegeOfPlat = baseStairsX - 3;//alors il n'y a pas de bord droit
                } else {//si il n'y a pas de bord dans sur le chemin de la généaration des platformes
                    edegeOfPlat = baseStairsX + 2;//le bord se situe à droite de la platforme
                }
                while (nbPlatform != 0) {//tant qu'il y a des platformes de disponible, création d'une nouvelle partie de la platforme
                    if (board[stage][edegeOfPlat] == 9) {
                        nbPlatform = 0;//Avant de commencer la génération, si le pointeur pointe ver un bord, on stop la génération
                    } else if (board[stage][edegeOfPlat] == 3) {//si la génération de platforme rencontre un escalier, il avance d'un cran
                        edegeOfPlat--;//si un escalier est déja sur cete case, on passe l'escalier
                    } else if (board[stage][edegeOfPlat] != 3) {//si il n'y a pas d'escalier à l'endroit actuel et pas de bord
                        board[stage][edegeOfPlat] = 2;//la case de la matrice va prendre la valeur 2 (un escalier)
                        edegeOfPlat--;//on décale le bord
                        nbPlatform--;//on enleve 1 platforme du nombre de platforme à posées
                    }
                }
                //genStairs(stage, stairsBase);
            }
            direction = !direction;
            stage = stage - 4;
        } while (stage >= 4);
    }

    public void setValuePostion(int x, int y, int value){
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

    public int getValueScene(int x,int y){
      return board[y][x];
    }

    public void setValueScene(int x,int y, int value){
      board[y][x] = value;
    }
}

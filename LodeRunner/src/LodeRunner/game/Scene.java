package LodeRunner.game;

// -- Lexique des différents entiers avec leur représentation graphique --

// 0 - représente un espace vide dans la scene
// 1 - représente les contours horizontaux de l'écran
// 2 - représente un morceau de platforme
// 3 - représente un morceau d'une échelle
// 4 - représente un ennemi
// 5 - représente un objet à récupérer
// 6 - spawn des joueurs
// 9 - représente les contours vertiacaux de l'écran
// 10 - représente le joueur courant
// 11 - représente le joueur en multijoueur
// 12 - paserelle
// 13 - spawn ennemie
// 14 - case vide pour les ennemis
// 15 - niveau suivant


import LodeRunner.handler.ServerManager;

public class Scene {
    private final int height; //Hauteur de l'écran
    private final int length; //Largeur de l'écran
    private final int[][] board; //scene représentée par une matrice 2*2
    private Player player1;
    private Player player2;
    private ServerManager serverManager;
    private String fboard;

    private int posXSpawnEnemy;// position en X du spawn ennemi
    private int posYSpawnEnemy;// position en Y du spawn ennemi

    private int posXNextLevel;// position en X du spawn du joueur 1
    private int posYNextLevel;// position en Y du spawn du joueur 1

    /**
     * Constructeur d'une scene
     *
     * @param height Hauteur de la scene
     * @param length Longueur de la scene
     */
    public Scene(int height, int length, Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.height = height;
        this.length = length;
        this.board = new int[height][length];
        this.setScene();
        serverManager = null;
    }


    public void setScene() {
        //méthode qui permet d'initialiser la scene en étant vide
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

    public void generateBoard(String s) {
        StringBuilder res = new StringBuilder();
        String value;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != ' ') {
                value = String.valueOf(s.charAt(i));
                if (i + 1 < s.length() && s.charAt(i + 1) != ' ') {
                    value += String.valueOf(s.charAt(i + 1));
                    i++;
                }
                switch (value) {
                    case "0", "6", "13", "14" -> res.append("  ");
                    case "1", "2", "9" -> res.append("▓▓");
                    case "3", "15" -> res.append("│┤");
                    case "4" -> res.append("☠ ");
                    case "5" -> res.append("☼ ");
                    case "10" -> res.append(player1.getName().charAt(0)).append(" ");
                    case "11" -> res.append(player2.getName().charAt(0)).append(" ");
                    case "12" -> res.append("__");
                    case "R" -> res.append("\n");
                }
            }
        }
        res.append(player1.getName()).append(": ").append(player1.getScore()).append(" ").append(player1.getLifeToString());
        res.append("\n");
        if (player2 != null)
            res.append(player2.getName()).append(": ").append(player2.getScore()).append(" ").append(player2.getLifeToString());
        this.fboard = res.toString();
    }

    public synchronized String getBoardtoString() {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.length; j++) {
                res.append(this.board[i][j]).append(" ");
            }
            res.append("R ");
        }
        return res.toString();
    }

    public synchronized void matrix2Screen() {
        //fonction permettant d'afficher l'écran à partir du tableau (qui est une matrice)
        System.out.println("\033[H\033[2J");//supprime tout ce qu'il y a dans la console auparavant
        int value;
        for (int i = 0; i < (this.height); i++) {
            //parcours de la matrice en y
            for (int y = 0; y < (this.length); y++) {
                //parcours de la matrice en x
                value = board[i][y];
                // porte de sortie du niveau
                switch (value) {
                    case 0, 13, 14, 6 -> System.out.print("  ");// espace vide
                    case 1, 2, 9 -> System.out.print("▓▓");// bord horizontal
                    case 15, 3 -> System.out.print("│┤");//échelle
                    case 4 -> System.out.print("☠ ");// simulation d'un ennemi
                    case 5 -> System.out.print("☼ ");// simulation d'un objet
                    case 10 -> System.out.print(player1.getName().charAt(0) + " ");// simulation du joueur courant
                    case 11 -> System.out.print(player2.getName().charAt(0) + " ");// simulation du joueur 2
                    case 12 -> System.out.print("__");// passerelle
                }
            }
            System.out.print("\n");
        }
        System.out.println(player1.getName() + ": " + player1.getScore() + " " + player1.getLifeToString());
        System.out.println("\n");
        if (player2 != null)
            System.out.println(player2.getName() + ": " + player2.getScore() + " " + player2.getLifeToString());
    }

    public void set1Player(Player player) {
        //méthode permettant d'ajouter un joueur à la scene
        this.player1 = player;
        this.player2 = null;
        for (int i = 0; i < (this.height); i++) {
            //parcours de la matrice en y
            for (int j = 0; j < (this.length); j++) {
                //parcours de la matrice en x
                if (board[i][j] == 6) {
                    setPositionPlayer(player, j, i);
                }
            }
        }
    }

    public void set2Players(Player player1, Player player2) {
        // ajoute deux joueurs à la scene
        //méthode permettant d'ajouter un joueur à la scene
        this.player1 = player1;
        this.player2 = player2;
        for (int i = 0; i < (this.height); i++) {
            //parcours de la matrice en y
            for (int j = 0; j < (this.length); j++) {
                //parcours de la matrice en x
                if (player2.getType() == 2 && board[i][j] == 13)
                    setPositionPlayer(player2, j, i);
                if (board[i][j] == 6) {
                    setPositionPlayer(player1, j, i);
                    if (player2.getType() == 1)
                        setPositionPlayer(player2, j + 1, i);
                }
            }
        }
    }

    public synchronized void reSpawnPlayer(Player player) {
        //méthode pour respawn le joueur dans la scene
        player.setPosition(0, 0);
        int Platforme = this.getHeight() - 2;
        int spawnX = 0;
        try {
            if ((player.getType() == 0 || player.getType() == 1)) {
                this.setValuePosition(player.getPosX(), player.getPosY(), 4);
                wait(2000);
                if (serverManager != null) {
                    serverManager.death(player);
                } else {
                    player.death();
                }
                do {
                    spawnX = (int) (Math.random() * this.getLenght() + 1);
                } while ((this.getValuePosition(spawnX, Platforme + 1) != 2 || this.getValuePosition(spawnX, Platforme) == 2) && this.getValuePosition(spawnX, Platforme + 1) != 5);
                player.setPosition(spawnX, Platforme);
                this.setValuePosition(spawnX, Platforme, 10);
            } else {
                if (player.getType() == 2) {
                    this.setValuePosition(player.getPosX(), player.getPosY(), 2);
                    wait(3000);
                    player.setPosition(posXSpawnEnemy, posYSpawnEnemy);
                    this.setValuePosition(posXSpawnEnemy, posYSpawnEnemy, 11);
                    serverManager.death(player);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized void setValuePosition(int x, int y, int value) {
        // méthode permettant de metre une valeur dans le tableau
        board[y][x] = value;
    }

    public synchronized void setPositionPlayer(Player player, int x, int y) {
        // méthode permettant de changer la position du joueur
        if (player.getNumber() == 1) {
            board[y][x] = 10;
            player.setPosition(x, y);
        } else if (player.getNumber() == 2) {
            board[y][x] = 11;
            player.setPosition(x, y);
        }
    }

    public int getValuePosition(int x, int y) {
        // méthode permettant de récupérer la valeur de la position et de tester si c'est dans le tableau
        try {
            return board[y][x];
        } catch (ArrayIndexOutOfBoundsException e) {
            return -1;
        }
    }

    public int getHeight() {
        // méthode permettant de récupérer la hauteur du plateau
        return this.height;
    }

    public int getLenght() {
        // méthode permettant de récupérer la longueur du tableau
        return this.length;
    }

    public void setPlayer1(Player player) {
        // setter pour le premier joueur
        this.player2 = player;
    }

    public void setPlayer2(Player player) {
        // setter pour le second joueur
        this.player2 = player;
    }

    public int getPosXSpawnEnemy() {
        // getter pour le spawn en X des ennemis
        return this.posXSpawnEnemy;
    }

    public void setPosXSpawnEnemy(int x) {
        // setter pour le spawn en X des ennemis
        this.posXSpawnEnemy = x;
    }

    public int getPosYSpawnEnemy() {
        // getter pour le spawn en Y des ennemis
        return this.posYSpawnEnemy;
    }

    public void setPosYSpawnEnemy(int y) {
        // setter pour le spawn en Y des ennemis
        this.posYSpawnEnemy = y;
    }

    public int getPosXNextLevel() {
        // getter pour le spawn en X du joueur
        return this.posXNextLevel;
    }

    public void setPosXNextLevel(int x) {
        // setter pour le spawn en X du joueur
        this.posXNextLevel = x;
    }

    public int getPosYNextLevel() {
        // getter pour le spawn en Y du joueur
        return this.posYNextLevel;
    }

    public void setPosYNextLevel(int y) {
        // setter pour le spawn en Y du joueur
        this.posYNextLevel = y;
    }

    public String getFboard() {
        return fboard;
    }
}

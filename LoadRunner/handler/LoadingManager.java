package LoadRunner.handler;

import LoadRunner.utils.Display;

import java.util.Scanner;

public class LoadingManager {

    private final GameManager gameManager;

    private int level;
    private int gamemode;

    private String[][] board;

    public LoadingManager(GameManager gameManager) {
        this.gameManager = gameManager;
        initLoadingScene();
    }

    public void start() {
        Scanner scanner;
        String[][] gamemodeChoose = getDisplay(Display.loadingPage);
        do {
            printBoard(gamemodeChoose);

            scanner = new Scanner(System.in);
            this.gamemode = scanner.nextInt();

        } while (this.gamemode != 1 && this.gamemode != 2);

        initLoadingScene();
        String[][] levelChoose = getDisplay(Display.levelPage);
        do {

            printBoard(levelChoose);
            scanner = new Scanner(System.in);
            this.level = scanner.nextInt();

        } while (this.level != 1 && this.level != 2 && this.level != 3);

        initLoadingScene();
        printBoard(getDisplay(Display.scorePage));
        new Scanner(System.in).nextLine();

    }

    public String[][] getDisplay(String[] linesToDisplay) {
        String[][] res = this.board.clone();
        int l = this.gameManager.getScene().getLenght();
        int h = (this.gameManager.getScene().getHeight() / 2) - (linesToDisplay.length / 2) - 1;

        for (String s : linesToDisplay) {
            String line = getCentered((l - 4), s);
            for (int i = 0; i < line.length(); i++) {
                res[h][2 + i] = line.charAt(i) + " ";
            }
            h++;
        }

        return res;

    }

    private String getCentered(int size, String s) {
        String toAdd = "";

        for (int i = 0; i < (size - s.length()) / 2; i++) {
            toAdd += " ";
        }

        return toAdd + s + toAdd;
    }

    public void printBoard(String[][] tab) {//affichage de board
        for (int i = 0; i < tab.length; i++) {
            for (int y = 0; y < tab[i].length; y++) {
                System.out.print(tab[i][y]);
            }
            System.out.print("\n");
        }
    }

    public int getLevel() {
        return level;
    }

    public int getGamemode() {
        return gamemode;
    }

    public void initLoadingScene() {

        int h = this.gameManager.getScene().getHeight();
        int l = this.gameManager.getScene().getLenght();

        this.board = new String[h][l];

        for (int i = 0; i < h; i++) {
            for (int y = 0; y < l; y++) {
                if (i == 0 || i == (h - 1)) {
                    this.board[i][y] = "–-";
                } else if (y == 0 || y == (l - 1)) {
                    this.board[i][y] = "||";
                } else {
                    this.board[i][y] = "  ";
                }
            }
        }
    }


}

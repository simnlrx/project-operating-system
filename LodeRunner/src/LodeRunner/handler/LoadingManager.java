package LodeRunner.handler;

import LodeRunner.game.Player;
import LodeRunner.utils.Display;

import java.io.IOException;
import java.util.Scanner;

public class LoadingManager {

    private final GameManager gameManager;

    private int gamemode;
    private int multigamemode;
    private int type;
    private String ip;
    private String[][] board;

    public LoadingManager(GameManager gameManager) {
        this.gameManager = gameManager;
        initLoadingScene();
    }

    public void start() throws IOException {
        Player player1 = gameManager.getPlayer1();
        Player player2 = gameManager.getPlayer2();
        player1.setReady(false);
        player2.setReady(false);

        Scanner scanner;
        String[][] gamemodeChoose = getDisplay(Display.loadingPage);
        do {
            printBoard(gamemodeChoose);

            scanner = new Scanner(System.in);
            this.gamemode = scanner.nextInt();
            gameManager.setGameMode(this.gamemode);

        } while (this.gamemode != 1 && this.gamemode != 2);

        System.out.println(gamemode);

        initLoadingScene();

        if (this.gamemode == 2) {
            String[][] multiChoose = getDisplay(Display.multiPage);
            do {
                printBoard(multiChoose);

                scanner = new Scanner(System.in);
                this.multigamemode = scanner.nextInt();

            } while (this.multigamemode != 1 && this.multigamemode != 2);

            gameManager.setMultiGamemode(this.multigamemode);

            initLoadingScene();
            if (this.multigamemode == 1) {
                gameManager.startServer();
                gameManager.setServer(true);

                String[][] namePlayer = getDisplay(Display.namePage);
                do {

                    printBoard(namePlayer);
                    scanner = new Scanner(System.in);
                    player1.setName(scanner.nextLine());
                    player1.setType(1);
                    player1.setReady(true);
                } while (player1.getName().equals("p1") || player1.getName().equals(" "));

                initLoadingScene();

                String[][] host = getDisplay(Display.hostPage);
                do {
                    printBoard(host);
                    scanner = new Scanner(System.in);
                    scanner.nextLine();
                    gameManager.getServer().send("p1name" + player1.getName());
                } while (gameManager.getServer().getTcpTask().getClient() != 1);


                initLoadingScene();
                String[][] wait = getDisplay(Display.waitPage);

                do {

                    printBoard(wait);
                    gameManager.getServer().send("p1ready");
                } while (!player2.isReady());
            } else {
                gameManager.setServer(false);
                initLoadingScene();
                String[][] name = getDisplay(Display.namePage);

                do {
                    printBoard(name);

                    scanner = new Scanner(System.in);
                    player2.setName(scanner.nextLine());

                } while (player2.getName().equals("p2") || player2.getName().equals(" "));
                initLoadingScene();
                String[][] ip = getDisplay(Display.ipPage);

                do {
                    printBoard(ip);

                    scanner = new Scanner(System.in);
                    this.ip = scanner.nextLine();

                } while (this.ip.equals(""));

                gameManager.startClient(this.ip);

                player2.send(player2.getName());

                initLoadingScene();

                String[][] modeChoose = getDisplay(Display.modePage);

                do {

                    printBoard(modeChoose);
                    scanner = new Scanner(System.in);
                    this.type = scanner.nextInt();
                    player2.setType(type);

                } while (this.type != 1 && this.type != 2);

                initLoadingScene();

                if (player2.getType() == 1)
                    player2.send("p2ready1");
                else if (player2.getType() == 2)
                    player2.send("p2ready2");
                do {
                    printBoard(getDisplay(Display.waitPage));
                } while (!player1.isReady());
                gameManager.setGameState(GameState.MULTIGAME);
                gameManager.getClient().statReception();
                //Clé pour mettre prêt: VszbBZbQCOFPuQmPHknvkg2G5i1VRqH6
            }

        } else if(this.gamemode == 3){
          do{
            BufferedReader br = new BufferedReader(new FileReader("foo.txt"));
            String line = null;
            String[][] end;
            String[] scorePage = new String[]{"Scores",
                    "",
                    res,
                    "",
                    "Press e to Exit"};
            scanner = new Scanner(System.in);
            end = this.getDisplay(scorePage);
            printBoard(end);
          }while(scanner.nextLine().equals(""));
          System.exit(0);
        }else {
            String[][] namePlayer = getDisplay(Display.namePage);
            do {

                printBoard(namePlayer);
                scanner = new Scanner(System.in);
                player1.setName(scanner.nextLine());
                player1.setReady(true);

            } while (player1.getName().equals("p1") || player1.getName().equals(" "));

            initLoadingScene();

            String[] scorePage = new String[]{"Level 1", "Player : " + player1.getName(), "", "Score :" + player1.getScore(), "", "5 lifes"};
            printBoard(getDisplay(scorePage));
            new Scanner(System.in).nextLine();
        }
    }

    public void loadEnd() {
        Player player1 = gameManager.getPlayer1();
        // affichage de fin de partie avec les différentes informations recueillies
        String[][] end;
        String[] scorePage = new String[]{"GAME OVER",
                "",
                "Player : " + player1.getName(),
                "lifes : " + player1.getLife(),
                "Score :" + player1.getScore(),
                "",
                "Press e to Exit"};

        end = this.getDisplay(scorePage);
        printBoard(end);
    }

    public void loadEndMulti() {
        Player player1 = gameManager.getPlayer1();
        Player player2 = gameManager.getPlayer2();
        // affichage de fin de partie avec les différentes informations recueillies
        String[][] end;
        String[] scorePage = new String[]{"GAME OVER",
                "",
                "Player 1 : " + player1.getName(),
                "Player 2 : " + player2.getName(),
                "lifes Player 1: " + player1.getLife(),
                "lifes Player 2: " + player2.getLife(),
                "Score Player 1: :" + player1.getScore(),
                "Score Player 2: :" + player2.getScore(),
                "",
                "Press e to Exit"};

        end = this.getDisplay(scorePage);
        printBoard(end);
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
        System.out.println("\033[H\033[2J");
        for (int i = 0; i < tab.length; i++) {
            for (int y = 0; y < tab[i].length; y++) {
                System.out.print(tab[i][y]);
            }
            System.out.print("\n");
        }
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

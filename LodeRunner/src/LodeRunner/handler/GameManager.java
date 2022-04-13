package LodeRunner.handler;

import LodeRunner.Client.Client;
import LodeRunner.game.Player;
import LodeRunner.game.Scene;
import LodeRunner.thread.RefreshScene;
import LodeRunner.thread.RegenSceneThread;

import javax.swing.*;
import java.io.IOException;
import java.util.Scanner;

public class GameManager {

    private Scene scene;  // scene de jeu
    private GameState gameState;// GameState pour l'état de la partie
    private ThreadManager threadManager;// liste de thread
    private ServerManager server;
    private Client client;
    private RegenSceneThread regen;
    private Player player1;// joueur 1
    private Player player2;// joueur 2
    private JFrame frame;
    private int gamemode;// mode de jeu - solo ou multi -
    private int level;// niveau de la partie
    private boolean printEndGame;// boolean pour l'affichage de fin de la partie
    private int multiGamemode;
    private int port;
    private boolean isServer = false;

    /*
     * Constructeur de GameManager
     * @param Scene scene qui est la scène du jeu
     * @param GameState gameState pour l'état du jeu
     */

    public GameManager(GameState gameState, int port) {
        // lors du lancement de la partie, les joueurs choisis auparavant sont ajoutés au GameManager
        this.player1 = new Player("p1", 1);
        this.player2 = new Player("p2", 2);
        this.gameState = gameState;
        this.printEndGame = true;
        this.port = port;
        this.scene = new Scene(30, 40, player1, player2);//les valeurs 17 et 36 sont faites pour coller avec les méthodes de création des escaliers =>17-1(pour le bord)= 4 escaliers
        // possibilité de faire l'affichage de fin de partie quand printEndGame est à true
    }

    public void start() {
        frame = new JFrame("Contrôles");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        FrameManager frameManager = new FrameManager(frame, this);
        frameManager.generate();
        threadManager = new ThreadManager();

        if (gamemode == 1 || isServer()) {
            regen = new RegenSceneThread(this);
            threadManager.addThread(regen);
            new LevelManager(this);
            new EnemiesManager(this, threadManager);
        }

        if (gamemode == 1) {
            scene.set1Player(player1);
            gameState = GameState.SOLOGAME;
        }
        if (gamemode == 2) {
            scene.set2Players(player1, player2);
            gameState = GameState.MULTIGAME;
        }

        threadManager.addThread(new RefreshScene(this));
        threadManager.startThreads();

    }

    public void nextLevel() throws IOException, InterruptedException {
        System.out.println("\033[H\033[2J");
        setLevel(getLevel() + 1);
        if (getLevel() < 5) {
            System.out.println("Loading Level " + this.getLevel() + ", please wait. . .");
            scene = new Scene(30, 40, player1, player2);
            Thread.sleep(5000);
            regen.reload();
            start();
        } else {
            if(isServer()){
              getServer().send("end");
            }
            endGame();
        }
        frame.dispose();
    }

    public void printEndGameSolo() throws IOException {
        //méthode pour afficher la fin de partie
        if (this.printEndGame) {
            Scanner scanner;
            String continueToEnd;

            //vérification si l'affichage de la partie est déja en cours
            System.out.println("\033[H\033[2J");

            //supprime tout ce qu'il y a dans la console auparavant
            LoadingManager loadingEnd = new LoadingManager(this);
            // création de l'affichage de fin de partie
            this.printEndGame = false;

            do {
                loadingEnd.loadEnd();
                scanner = new Scanner(System.in);
                continueToEnd = scanner.nextLine();
            } while (!continueToEnd.equals("e"));
            System.exit(0);
        }
    }

    public void printEndGameMulti() throws IOException {
        //méthode pour afficher la fin de partie
        if (this.printEndGame) {
            Scanner scanner;
            String continueToEnd;

            //vérification si l'affichage de la partie est déja en cours
            System.out.println("\033[H\033[2J");

            //supprime tout ce qu'il y a dans la console auparavant
            LoadingManager loadingEnd = new LoadingManager(this);
            // création de l'affichage de fin de partie
            this.printEndGame = false;

            do {
                loadingEnd.loadEndMulti();
                scanner = new Scanner(System.in);
                continueToEnd = scanner.nextLine();
            } while (!continueToEnd.equals("e"));
            if (isServer)
                server.stop();
            if (gamemode == 2 && !isServer)
                client.logout();
            System.exit(0);
        }
    }

    public void endGame() throws IOException {
        // méthode qui va permettre de mettre fin à la partie suivi de son affichage
        gameState = GameState.END;
        if(this.gamemode==1){
          printEndGameSolo();
        }else if(this.gamemode==2){
          printEndGameMulti();
        }
    }

    public void startServer() {
        server = new ServerManager(this);
        server.start();
    }

    public void startSocketServer() throws IOException {
        server.startSocket();
    }

    public Scene getScene() {
        // récupération de la scene
        return this.scene;
    }

    public int getGameMode() {
        // récupération du mode de jeu
        return this.gamemode;
    }

    public void setGameMode(int gamemode) {
        // affectation du mode de jeu
        this.gamemode = gamemode;
    }

    public GameState getGameState() {
        // récupération de GameState
        return gameState;
    }

    public void setGameState(GameState gameState) {
        // affecetation de gameState
        this.gameState = gameState;
    }

    public int getLevel() {
        // récupération du niveau
        return this.level;
    }

    public void setLevel(int level) {
        // affectation du niveau
        this.level = level;
    }

    public int getPort() {
        return port;
    }

    public void setMultiGamemode(int multiGamemode) {
        this.multiGamemode = multiGamemode;
    }

    public ServerManager getServer() {
        return server;
    }

    public boolean isServer() {
        return isServer;
    }

    public void setServer(boolean server) {
        isServer = server;
    }


    public void startClient(String ip) {
        client = new Client(this, player2, ip, getPort());
        client.login();
    }

    public Client getClient() {
        return client;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }
}

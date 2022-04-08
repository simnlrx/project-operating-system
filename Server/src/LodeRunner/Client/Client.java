package LodeRunner.Client;

import LodeRunner.game.Player;
import LodeRunner.handler.GameManager;
import LodeRunner.handler.GameState;

import java.io.IOException;
import java.net.Socket;

public class Client {

    private Player player;
    private GameManager gameManager;
    private final String ip;
    private final int port;
    private Socket socket;

    public Client(GameManager gameManager, Player player, String ip, int port) {
        this.gameManager = gameManager;
        this.player = player;
        this.ip = ip;
        this.port = port;
    }

    public void login() {
        try{
            socket = new Socket(ip, port);
            player.setSocket(socket);
            player.openWriter();
            new Thread(new ReceptionSocketServer(gameManager,8060)).start();
            System.out.println("th socket start");
            while (gameManager.getGameState().equals(GameState.LOADING)){
                Thread.sleep(100);
                System.out.println(gameManager.getGameState().getName());
            }
            new Thread(new ReceptionDatagramServer(gameManager)).start();
            System.out.println("th datagram start");
        }catch (IOException | InterruptedException e){
            System.out.println("Connexion impossible.");
            e.printStackTrace();
            System.exit(0);
        }
    }

    public void logout() throws IOException {
        player.closeWriter();
        socket.close();
    }

    public Player getPlayer() {
        return player;
    }

}

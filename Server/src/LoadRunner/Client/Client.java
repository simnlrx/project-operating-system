package LoadRunner.Client;

import LoadRunner.game.Player;

import java.io.IOException;
import java.net.Socket;

public class Client {

    private Player player;
    private final String ip;
    private final int port;
    private Socket socket;
    private byte[] data = new byte[5000];

    public Client(Player player, String ip, int port) {
        this.player = player;
        this.ip = ip;
        this.port = port;
    }

    public void login() {
        try{
            socket = new Socket(ip, port);
            player.setSocket(socket);
            player.openWriter();
        }catch (IOException e){
            System.out.println("Connexion impossible.");
            e.printStackTrace();
            System.exit(0);
        }
    }

    public void logout() throws IOException {
        player.closeWriter();
        socket.close();
    }

    public void reception(){

    }

    public Player getPlayer() {
        return player;
    }

}

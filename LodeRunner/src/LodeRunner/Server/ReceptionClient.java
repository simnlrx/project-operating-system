package LodeRunner.Server;

import LodeRunner.game.KeySelection;
import LodeRunner.game.Player;
import LodeRunner.handler.GameManager;
import LodeRunner.handler.GameState;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class ReceptionClient implements Runnable {

    private final GameManager gameManager;
    private final Socket socket;
    private String pseudo = "";

    public ReceptionClient(GameManager gameManager, Socket socket) {
        this.gameManager = gameManager;
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            String tampon;
            Player player = gameManager.getScene().getPlayer2();
            KeySelection keySelection = new KeySelection(player, gameManager);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            while (!socket.isClosed() && ((tampon = reader.readLine()) != null)) {
                if (this.pseudo.isBlank()) {
                    pseudo = tampon;
                    player.setName(pseudo);
                    player.setSocket(socket);
                    gameManager.startSocketServer();
                    System.out.println("Connexion établie. \nPRESS ENTER");
                }

                if(tampon.equals("p2ready1") || tampon.equals("p2ready2")) {
                    gameManager.setGameState(GameState.MULTIGAME);
                    if (tampon.equals("p2ready1")) {
                        player.setType(1);
                    } else {
                        player.setType(2);
                    }
                    Broadcast broadcast = new Broadcast(gameManager, gameManager.getPort());
                    broadcast.setIp(socket.getInetAddress().getHostAddress());
                    new Thread(broadcast).start();
                    player.setReady(true);
                }

                if (player.isReady()) {
                    switch (tampon) {
                        case "a" -> keySelection.setKey('a');
                        case "z" -> keySelection.setKey('z');
                        case "e" -> keySelection.setKey('e');
                        case "q" -> keySelection.setKey('q');
                        case "s" -> keySelection.setKey('s');
                        case "d" -> keySelection.setKey('d');
                        case "w" -> keySelection.setKey('w');
                        case "c" -> keySelection.setKey('c');
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

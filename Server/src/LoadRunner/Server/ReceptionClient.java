package LoadRunner.Server;

//Reçoit les infos du client

import LoadRunner.game.KeySelection;
import LoadRunner.game.Player;
import LoadRunner.handler.GameManager;

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
            //player.setAdresse(socket.getInetAddress().getHostAddress());

            while(!socket.isClosed() && ((tampon = reader.readLine()) != null)) {
                if(this.pseudo.isBlank()) {
                  pseudo = tampon;
                  player.setName(pseudo);
                  player.setSocket(socket);
                  System.out.println(tampon);
                }

                if(tampon.equals("VszbBZbQCOFPuQmPHknvkg2G5i1VRqH6"))
                    player.setReady(true);

                if(player.isReady()){
                    switch (tampon){
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

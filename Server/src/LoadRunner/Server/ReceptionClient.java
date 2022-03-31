package LoadRunner.Server;

//Reçoit les infos du client

import LoadRunner.game.Player;
import LoadRunner.handler.GameManager;
import LoadRunner.utils.ReaderUtil;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class ReceptionClient implements Runnable {

    private GameManager gameManager;
    private final Socket socket;

    public ReceptionClient(GameManager gameManager, Socket socket) {
        this.gameManager = gameManager;
        this.socket = socket;
    }

    @Override
    public void run() {
        try {

            int cpt = 0;
            String tampon;
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            while(!socket.isClosed() && (tampon = ReaderUtil.getMessage(reader)) != null) {
                if(cpt == 0) {
                    Player player = new Player(tampon, 1);
                }


                if(tampon.equalsIgnoreCase("z")){

                }

                cpt++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

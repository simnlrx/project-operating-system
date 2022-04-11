package LodeRunner.Client;

import LodeRunner.game.Player;
import LodeRunner.handler.GameManager;
import LodeRunner.handler.GameState;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.Arrays;

public class ReceptionSocketServer implements Runnable {

    private final GameManager gameManager;
    private final int port;

    public ReceptionSocketServer(GameManager gameManager, int port) {
        this.gameManager = gameManager;
        this.port = port;
    }

    @Override
    public void run() {

        try (ServerSocket serverSocket = new ServerSocket(this.port)) {

            Socket socket = serverSocket.accept();
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Player player1 = gameManager.getScene().getPlayer1();
            Player player2 = gameManager.getScene().getPlayer2();
            String tampon;

            while (!socket.isClosed() && (tampon = reader.readLine()) != null) {

                if (tampon.contains("p1ready")) {
                    player1.setType(1);
                    player1.setReady(true);
                }
                if (tampon.contains("p1name")) {
                    String name = tampon.substring(6);
                    player1.setName(name);
                }
                if (tampon.contains("p1score")) {
                    int score = Integer.parseInt(tampon.substring(7));
                    player1.addScore(score);
                }
                if (tampon.contains("p1life")) {
                    player1.death();
                }
                if (tampon.contains("p2score")) {
                    int score = Integer.parseInt(tampon.substring(7));
                    player2.addScore(score);
                }
                if (tampon.contains("p2life")) {
                    player2.death();
                }

                if (gameManager.getGameState().equals(GameState.END)) {
                    reader.close();
                    socket.close();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

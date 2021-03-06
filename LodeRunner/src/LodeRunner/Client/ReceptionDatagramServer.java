package LodeRunner.Client;

import LodeRunner.handler.GameManager;
import LodeRunner.handler.GameState;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ReceptionDatagramServer implements Runnable {

    private final GameManager gameManager;

    public ReceptionDatagramServer(GameManager gameManager) {
        this.gameManager = gameManager;
    }


    @Override
    public void run() {
        DatagramPacket dtgrPacket;

        try (DatagramSocket dtgrSocket = new DatagramSocket(gameManager.getPort());) {

            while (gameManager.getGameState().equals(GameState.MULTIGAME)) {
                byte[] data = new byte[5000];
                dtgrPacket = new DatagramPacket(data, data.length);
                dtgrSocket.receive(dtgrPacket);
                gameManager.getScene().generateBoard(new String(dtgrPacket.getData()));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

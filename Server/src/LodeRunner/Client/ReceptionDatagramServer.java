package LodeRunner.Client;

import LodeRunner.handler.GameManager;
import LodeRunner.handler.GameState;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Arrays;

public class ReceptionDatagramServer implements Runnable{

    private final GameManager gameManager;

    public ReceptionDatagramServer(GameManager gameManager) {
        this.gameManager = gameManager;
    }


    @Override
    public void run() {
        DatagramPacket dtgrPacket;
        System.out.println("gamestate : " + gameManager.getGameState().getName());

        try {

            DatagramSocket dtgrSocket = new DatagramSocket(gameManager.getPort());
            System.out.println("gamestate : " + gameManager.getGameState().getName());

            while (gameManager.getGameState().equals(GameState.MULTIGAME)){
                byte[] data = new byte[5000];
                dtgrPacket = new DatagramPacket(data, data.length);
                dtgrSocket.receive(dtgrPacket);
                gameManager.getScene().setFinalBoard(Arrays.toString(dtgrPacket.getData()));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package LodeRunner.Server;

//Envoi en UDP les informations

import LodeRunner.handler.GameManager;
import LodeRunner.handler.GameState;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class Broadcast implements Runnable {

    private final GameManager gameManager;
    private final int port;

    public Broadcast(GameManager gameManager, int port) {
        this.gameManager = gameManager;
        this.port = port;
    }

    @Override
    public void run() {

        while (gameManager.getGameState().equals(GameState.MULTIGAME)) {
            try (DatagramSocket dtgrSocket = new DatagramSocket()) {

                /*String ip = "255.255.255.255";
                InetSocketAddress addr = new InetSocketAddress(ip, port);*/

                byte[] msgByte = gameManager.getScene().getFinalBoard().getBytes();
                dtgrSocket.send(new DatagramPacket(msgByte, msgByte.length, port));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}

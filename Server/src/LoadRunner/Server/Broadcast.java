package LoadRunner.Server;

//Envoi en UDP les informations

import LoadRunner.handler.GameManager;
import LoadRunner.handler.GameState;

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

        if (gameManager.getGameState().equals(GameState.MULTIGAME)) {
            try (DatagramSocket dtgrSocket = new DatagramSocket()) {

                String ip = "255.255.255.255";
                InetSocketAddress addr = new InetSocketAddress(ip, port);

                String msg = gameManager.getScene().generateBoard();
                byte[] msgByte = msg.getBytes();
                dtgrSocket.send(new DatagramPacket(msgByte, msgByte.length, addr));


            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}

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
    private String ip;

    public Broadcast(GameManager gameManager, int port) {
        this.gameManager = gameManager;
        this.port = port;
    }

    @Override
    public void run() {

        while (gameManager.getGameState().equals(GameState.MULTIGAME)) {
            try (DatagramSocket dtgrSocket = new DatagramSocket()) {

                InetSocketAddress addr = new InetSocketAddress(ip, port);

                String str = gameManager.getScene().getBoardtoString();
                System.out.println(str);
                byte[] msgByte = str.getBytes();

                dtgrSocket.send(new DatagramPacket(msgByte, msgByte.length, addr));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}

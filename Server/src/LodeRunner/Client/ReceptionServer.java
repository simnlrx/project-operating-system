package LodeRunner.Client;

import LodeRunner.handler.GameManager;
import LodeRunner.handler.GameState;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.Arrays;

public class ReceptionServer implements Runnable {

    private GameManager gameManager;
    private int port;

    public ReceptionServer(GameManager gameManager, int port) {
        this.gameManager = gameManager;
        this.port = port;
    }

    @Override
    public void run() {

        try(ServerSocket serverSocket = new ServerSocket(this.port)){

            Socket socket = serverSocket.accept();
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            while (!socket.isClosed()){
                System.out.println("ReceptionServer OPEN");
                if(reader.readLine().equals("p1ready")){
                    gameManager.getScene().getPlayer1().setReady(true);
                    reader.close();
                    socket.close();
                }else{
                    System.out.println(reader.readLine());
                }
            }

        }catch (IOException e){
            e.printStackTrace();
        }

        DatagramPacket dtgrPacket;

        String ip = "255.255.255.255";
        InetSocketAddress addr = new InetSocketAddress(ip, gameManager.getPort());

        try {
            DatagramSocket dtgrSocket = new DatagramSocket(addr);

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

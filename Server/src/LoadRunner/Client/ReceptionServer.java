package LoadRunner.Client;

import LoadRunner.handler.GameManager;
import LoadRunner.handler.GameState;

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

        try {
            DatagramSocket dtgrSocket = new DatagramSocket(gameManager.getPort());

            while (gameManager.getGameState().equals(GameState.MULTIGAME)){
                byte[] data = new byte[5000];
                dtgrPacket = new DatagramPacket(data, data.length);
                dtgrSocket.receive(dtgrPacket);
                Arrays.toString(dtgrPacket.getData());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

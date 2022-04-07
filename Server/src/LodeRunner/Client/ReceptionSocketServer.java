package LodeRunner.Client;

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

    }
}

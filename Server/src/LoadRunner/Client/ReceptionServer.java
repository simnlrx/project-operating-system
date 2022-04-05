package LoadRunner.Client;

import LoadRunner.handler.GameManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

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
                }
            }

        }catch (IOException e){
            e.printStackTrace();
        }

    }
}

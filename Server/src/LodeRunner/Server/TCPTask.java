package LodeRunner.Server;

//Démarre le serveur (créé le socket et accpet les connexions)

import LodeRunner.handler.GameManager;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPTask implements Runnable {

    private final GameManager gameManager;
    private final int port;
    private int client;

    public TCPTask(GameManager gameManager, int port) {
        this.gameManager = gameManager;
        this.port = port;
        this.client = 0;
    }

    @Override
    public void run() {

        try (ServerSocket serverSocket = new ServerSocket(this.port)) {

            while (client < 1) {
                Socket socket = serverSocket.accept();

                ReceptionClient newClient = new ReceptionClient(gameManager, socket);
                Thread th = new Thread(newClient);

                th.start();
                client++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public int getPort() {
        return port;
    }

    public int getClient(){
        return client;
    }
}

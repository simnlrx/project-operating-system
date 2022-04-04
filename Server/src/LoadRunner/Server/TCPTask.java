package LoadRunner.Server;

//Démarre le serveur (créé le socket et accpet les connexions)

import LoadRunner.handler.GameManager;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
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
                System.out.println("socket accpet");

                ReceptionClient newClient = new ReceptionClient(gameManager, socket);
                System.out.println("instance de clien cree");
                Thread th = new Thread(newClient);
                System.out.println("thread client cree");

                System.out.println("client start ");
                th.start();
                //th.join();
                System.out.println("client++");
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

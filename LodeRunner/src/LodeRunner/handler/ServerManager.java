package LodeRunner.handler;

import LodeRunner.Server.TCPTask;
import LodeRunner.game.Player;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerManager {

    private final GameManager gameManager;
    private TCPTask tcpTask;
    private Thread tcp;
    private PrintWriter writer;
    private Socket socket;

    public ServerManager(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public void start() {
        tcpTask = new TCPTask(gameManager, gameManager.getPort());
        tcp = new Thread(tcpTask);
        tcp.start();
    }

    public void startSocket() throws IOException {
        socket = new Socket(gameManager.getPlayer2().getSocket().getInetAddress().getHostAddress(), 8060);
        writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
    }

    public void send(String s) {
        writer.println(s);
    }

    public void addScore(Player player, int score) {
        String s = "p";
        if (player.getName().equals(gameManager.getPlayer1().getName())) {
            s += "1";
        } else {
            s += "2";
        }
        s += "score" + score;
        send(s);
    }

    public void death(Player player) {
        player.death();
        String life = "p";
        if (player.getName().equals(gameManager.getPlayer1().getName())) {
            life += "1";
        } else {
            life += "2";
        }
        life += "life";
        send(life);
    }

    public void stop() throws IOException {
        writer.close();
        socket.close();
        tcp.interrupt();
    }

    public TCPTask getTcpTask() {
        return tcpTask;
    }

}

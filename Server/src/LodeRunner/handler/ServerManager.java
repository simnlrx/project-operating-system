package LodeRunner.handler;

import LodeRunner.Server.TCPTask;

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

    public ServerManager(GameManager gameManager){
        this.gameManager = gameManager;
    }

    public void start() {
        tcpTask = new TCPTask(gameManager, gameManager.getPort());
        tcp = new Thread(tcpTask);
        tcp.start();
    }

    public void startSocket() throws IOException {
        socket = new Socket(gameManager.getScene().getPlayer2().getSocket().getInetAddress().getHostAddress(), 8060);
        writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
    }

    public void send(String s){
        writer.println(s);
    }

    public void stop() throws IOException {
        writer.close();
        socket.close();
        tcp.interrupt();
    }

    public Thread getTcp() {
        return tcp;
    }

    public void setTcp(Thread tcp) {
        this.tcp = tcp;
    }

    public TCPTask getTcpTask() {
        return tcpTask;
    }

    public void setTcpTask(TCPTask tcpTask) {
        this.tcpTask = tcpTask;
    }
}

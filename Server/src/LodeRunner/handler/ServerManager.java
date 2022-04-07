package LodeRunner.handler;

import LodeRunner.Server.TCPTask;

public class ServerManager {

    private final GameManager gameManager;
    private TCPTask tcpTask;
    private Thread tcp;

    public ServerManager(GameManager gameManager){
        this.gameManager = gameManager;
    }

    public void start(){
        tcpTask = new TCPTask(gameManager, gameManager.getPort());
        tcp = new Thread(tcpTask);
        tcp.start();
    }

    public void stop(){
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

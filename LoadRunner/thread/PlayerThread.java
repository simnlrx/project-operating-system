package LoadRunner.thread;

//NE RIEN FAIRE ICI JE REFLECHIS BABA MERCI
import LoadRunner.game.Player;

public class PlayerThread extends Thread {

    private Player player;

    public PlayerThread(Player player){
        this.player = player;
    }

    @Override
    public void run() {

    }
}

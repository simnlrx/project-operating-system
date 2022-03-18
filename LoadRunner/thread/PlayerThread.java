package LoadRunner.thread;

//NE RIEN FAIRE ICI JE REFLECHIS BABA MERCI

import LoadRunner.game.Player;

public class PlayerThread extends Thread {
    private Player player;

    public PlayerThread(Player player){
        this.player = player;
    }

    public void setSemaphore(Semaphore s){
      this.s = s;
    }

    @Override
    public void run() {
      try{
        while(gameManager.getGameState().isGame()){
        }catch(InterruptedException e){
              e.printStackTrace();
        }
      }
    }
}

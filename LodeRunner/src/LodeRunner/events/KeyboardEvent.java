package LodeRunner.events;

import LodeRunner.game.KeySelection;
import LodeRunner.game.Player;
import LodeRunner.handler.GameManager;
import LodeRunner.handler.GameState;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class KeyboardEvent implements KeyListener {

    private final KeySelection keySelection;
    private final GameManager gameManager;
    private final Player player;

    public KeyboardEvent(Player player, GameManager gameManager) {
        this.keySelection = new KeySelection(player, gameManager);
        this.gameManager = gameManager;
        this.player = player;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        char p = e.getKeyChar();
        String res = "" + p;
        if (gameManager.getGameState().equals(GameState.MULTIGAME) && !gameManager.isServer()) {
            if (p == 'z' || p == 'e' || p == 'a' || p == 'q' || p == 's' || p == 'd' || p == 'w' || p == 'c') {
                try {
                    player.send(res);
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            }
        } else
            keySelection.setKey(p);
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}

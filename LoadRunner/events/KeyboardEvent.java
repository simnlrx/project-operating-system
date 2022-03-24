package LoadRunner.events;

//NE RIEN FAIRE ICI JE REFLECHIS BABA MERCI

import LoadRunner.game.KeySelection;
import LoadRunner.game.Player;
import LoadRunner.game.Scene;
import LoadRunner.handler.GameManager;
import LoadRunner.handler.GameState;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardEvent extends Thread implements KeyListener {

    private final KeySelection keySelection;

    public KeyboardEvent(Player player, GameManager gameManager) {
        this.keySelection = new KeySelection(player, gameManager);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        keySelection.setKey(e.getKeyChar());
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

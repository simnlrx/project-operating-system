package LoadRunner.events;

import LoadRunner.game.KeySelection;
import LoadRunner.game.Player;
import LoadRunner.handler.GameManager;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardEvent implements KeyListener {

    private final KeySelection keySelection;

    public KeyboardEvent(Player player, GameManager gameManager) {
        this.keySelection = new KeySelection(player, gameManager);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        keySelection.setKey(e.getKeyChar());
        //if(e.getKeyChar() == 'z') joueur.print(e.get
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

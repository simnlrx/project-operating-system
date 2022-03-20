package LoadRunner.events;

//NE RIEN FAIRE ICI JE REFLECHIS BABA MERCI

import LoadRunner.game.KeySelection;
import LoadRunner.game.Player;
import LoadRunner.game.Scene;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardEvent extends Thread implements KeyListener {

    private Player player;
    private Scene scene;
    private KeySelection keySelection;

    public KeyboardEvent(Player player, Scene scene) {
        this.player = player;
        this.scene = scene;
        this.keySelection = new KeySelection(player, scene);
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

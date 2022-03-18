package LoadRunner.events;

//NE RIEN FAIRE ICI JE REFLECHIS BABA MERCI

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardEvent extends Thread implements KeyListener {

    @Override
    public void keyTyped(KeyEvent e) {
        if((e.getKeyChar() == 'z') || (e.getKeyChar() == 'Z')){
            System.out.println("Z");
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

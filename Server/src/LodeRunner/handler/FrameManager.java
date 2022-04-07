package LodeRunner.handler;

import LodeRunner.events.KeyboardEvent;

import javax.swing.*;
import java.awt.*;

public class FrameManager {

    private final JFrame frame;
    private final GameManager gameManager;

    public FrameManager(JFrame frame, GameManager gameManager) {
        this.frame = frame;
        this.gameManager = gameManager;
    }

    public void generate(){
        frame.setLayout(new FlowLayout());
        frame.setSize(500, 500);

        KeyboardEvent keyboardEvent = new KeyboardEvent(gameManager.getScene().getPlayer1(), gameManager);
        frame.addKeyListener(keyboardEvent);

        frame.setVisible(true);
    }

}

package LoadRunner.handler;

import LoadRunner.events.KeyboardEvent;
import LoadRunner.game.Scene;

import java.awt.*;

public class FrameManager {

    private final Frame frame;
    private final GameManager gameManager;

    public FrameManager(Frame frame, GameManager gameManager) {
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

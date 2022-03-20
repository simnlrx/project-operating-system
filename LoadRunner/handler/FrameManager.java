package LoadRunner.handler;

import LoadRunner.events.KeyboardEvent;
import LoadRunner.game.Scene;

import java.awt.*;

public class FrameManager {

    private final Frame frame;
    private final Scene scene;

    public FrameManager(Frame frame, Scene scene) {
        this.frame = frame;
        this.scene = scene;
    }

    public void generate(){
        frame.setLayout(new FlowLayout());
        frame.setSize(200, 200);

        KeyboardEvent keyboardEvent = new KeyboardEvent(scene.getPlayer1(), scene);
        frame.addKeyListener(keyboardEvent);

        frame.setVisible(true);
    }

}

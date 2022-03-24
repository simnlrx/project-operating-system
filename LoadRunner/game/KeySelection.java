package LoadRunner.game;

import LoadRunner.handler.GameManager;

public class KeySelection {

    private final Player player;
    private final GameManager gameManager;
    private final Scene scene;

    public KeySelection(Player player, GameManager gameManager) {
        this.player = player;
        this.gameManager = gameManager;
        this.scene = gameManager.getScene();
    }

    public void setKey(char key) {
        System.out.println("MOVE");
        int top = scene.getValuePosition(player.getPosX(), player.getPosY() - 1);
        int bottom = scene.getValuePosition(player.getPosX(), player.getPosY() + 1);
        int left = scene.getValuePosition((player.getPosX() - 1), player.getPosY());
        int right = scene.getValuePosition((player.getPosX() + 1), player.getPosY());
        switch (key) {
            case 'z':
                if (top == 3) {
                    scene.setValuePosition(player.getPosX(), player.getPosY(), 3);
                    scene.setPositionPlayer(player, player.getPosX(), player.getPosY() - 1);
                    break;
                }

            case 'q':
                if (left == 0 || left == 3) {
                    if (scene.getValuePosition(player.getPosX() - 1, player.getPosY() + 1) == 2 || scene.getValuePosition(player.getPosX() - 1, player.getPosY() + 1) == 3) {
                        scene.setValuePosition(player.getPosX(), player.getPosY(), 0);
                        scene.setPositionPlayer(player, player.getPosX() - 1, player.getPosY());
                    }
                }
                break;
            case 's':
                if (bottom == 3) {
                    scene.setValuePosition(player.getPosX(), player.getPosY(), 0);
                    scene.setPositionPlayer(player, player.getPosX(), player.getPosY() + 1);
                }
                break;
            case 'd':
                if (right == 0 || right == 3) {
                    if (scene.getValuePosition(player.getPosX() + 1, player.getPosY() + 1) == 2 || scene.getValuePosition(player.getPosX() + 1, player.getPosY() + 1) == 3) {
                        scene.setValuePosition(player.getPosX(), player.getPosY(), 0);
                        scene.setPositionPlayer(player, player.getPosX() + 1, player.getPosY());
                    }
                }
                break;
            case 'e':
                System.exit(0);
        }
    }
}

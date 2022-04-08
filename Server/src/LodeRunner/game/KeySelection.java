package LodeRunner.game;

import LodeRunner.handler.GameManager;
import LodeRunner.thread.BlockBreakThread;

public class KeySelection {

    private final Player player;
    private final Scene scene;
    private final GameManager gameManager;

    /*
     * Constructeur KeySelection
     * @param player Player joueur
     * @param gameManager gameManagaer
     */

    public KeySelection(Player player, GameManager gameManager) {
        this.player = player;
        this.scene = gameManager.getScene();
        this.gameManager = gameManager;
    }

    public void setKey(char key) {
        // méthode pour affecter une action en fonction du caractè passer en paramètre
        int x = player.getPosX();
        int y = player.getPosY();

        int top = scene.getValuePosition(x, y - 1);
        int bottom = scene.getValuePosition(x, y + 1);
        int left = scene.getValuePosition((x - 1), y);
        int right = scene.getValuePosition((x + 1), y);

        switch (key) {
            case 'z':
                // si touche z touchée alors déplacement vers le haut
                if (top == 3 || top == 15) {
                    scene.setValuePosition(x, y, 3);
                    scene.setPositionPlayer(player, x, y - 1);
                }
                break;
            case 'q':
                // si touche q touchée alors déplacement vers la gauche
                if (left == 0 || left == 3 || left == 14 || left == 11 || left == 4 || left == 13) {
                    if (scene.getValuePosition(x - 1, y + 1) == 2 || scene.getValuePosition(x - 1, y + 1) == 3 || scene.getValuePosition(x - 1, y + 1) == 4) {
                        scene.setValuePosition(x, y, 0);
                        scene.setPositionPlayer(player, x - 1, y);
                    }
                    break;
                }
                if (left == 5 && scene.getValuePosition(x - 1, y + 1) != 14) {
                    scene.setValuePosition(x, y, 0);
                    scene.setPositionPlayer(player, x - 1, y);
                    setScore();
                }
                if (left == 12) {
                    if (scene.getValuePosition(x - 1, y) == 12) {
                        scene.setValuePosition(x, y, 0);
                        scene.setPositionPlayer(player, x - 1, y);
                    }
                }
                break;
            case 's':
                // si touche s touchée alors déplacement vers le bas
                if (bottom == 3) {
                    scene.setValuePosition(x, y, 0);
                    scene.setPositionPlayer(player, x, y + 1);
                }
                break;
            case 'd':
                // si touche d touchée alors déplacement vers la droite
                if (right == 0 || right == 3 || right == 14 || right == 11 || right == 4 || right == 13) {
                    if (scene.getValuePosition(x + 1, y + 1) == 2 || scene.getValuePosition(x + 1, y + 1) == 3 || scene.getValuePosition(x + 1, y + 1) == 4) {
                        scene.setValuePosition(x, y, 0);
                        scene.setPositionPlayer(player, x + 1, y);
                    }
                    break;
                }
                if (right == 5 && scene.getValuePosition(x + 1, y + 1) != 14) {
                    scene.setValuePosition(x, y, 0);
                    scene.setPositionPlayer(player, x + 1, y);
                    setScore();
                    break;
                }
                if (right == 12) {
                    if (scene.getValuePosition(x + 1, y) == 12) {
                        scene.setValuePosition(x, y, 0);
                        scene.setPositionPlayer(player, x + 1, y);
                    }
                }
                break;
            case 'e':
                // si touche e touchée alors bloc à gauche cassé
                if (scene.getValuePosition(x + 1, y) == 2) {
                    BlockBreakThread blockBreakE = new BlockBreakThread(gameManager, x + 1, y);
                    blockBreakE.start();
                }
                break;
            case 'a':
                // si touche a touchée alors bloc à droite cassé
                if (scene.getValuePosition(x - 1, y) == 2) {
                    BlockBreakThread blockBreakA = new BlockBreakThread(gameManager, x - 1, y);
                    blockBreakA.start();
                }
                break;
            case 'w':
                // si touche w touchée alors bloc en bas à gauche cassé
                if (scene.getValuePosition(x - 1, y + 1) == 2) {
                    BlockBreakThread blockBreakW = new BlockBreakThread(gameManager, x - 1, y + 1);
                    blockBreakW.start();
                }
                break;
            case 'c':
                // si touche c touchée alors bloc en bas à droite cassé
                if (scene.getValuePosition(x + 1, y + 1) == 2) {
                    BlockBreakThread blockBreakW = new BlockBreakThread(gameManager, x + 1, y + 1);
                    blockBreakW.start();
                }
                break;

        }
    }

    private void setScore() {
        player.addScore(100);
        if (gameManager.isServer()) {
            String score = "p";
            if (player.getName().equals(gameManager.getScene().getPlayer1().getName())) {
                score += "1";
            } else {
                score += "2";
            }
            score += "score100";
            gameManager.getServer().send(score);
        }
    }
}

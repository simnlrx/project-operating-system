package LodeRunner.game;

import LodeRunner.handler.GameManager;
import LodeRunner.handler.GameState;
import LodeRunner.thread.BlockBreakThread;

public class KeySelection {

    private final Player player;
    private final GameManager gameManager;

    /*
     * Constructeur KeySelection
     * @param player Player joueur
     * @param gameManager gameManagaer
     */

    public KeySelection(Player player, GameManager gameManager) {
        this.player = player;
        this.gameManager = gameManager;
    }

    public void setKey(char key) {
        // méthode pour affecter une action en fonction du caractè passer en paramètre
        Scene scene = gameManager.getScene();
        int x = player.getPosX();
        int y = player.getPosY();

        int top = scene.getValuePosition(x, y - 1);
        int bottom = scene.getValuePosition(x, y + 1);
        int left = scene.getValuePosition((x - 1), y);
        int right = scene.getValuePosition((x + 1), y);

        if (player.getType() == 2) {
            switch (key) {
                case 'z':
                    if (top == 3 || top == 15) {
                        scene.setValuePosition(x, y, 3);
                        scene.setPositionPlayer(player, x, y - 1);
                    }
                case 'q':
                    if (left == 0 || left == 3 || left == 14 || left == 10 || left == 4 || left == 13 || left == 16 || left == 5) {
                        if (scene.getValuePosition(x - 1, y + 1) == 2 || scene.getValuePosition(x - 1, y + 1) == 3 || scene.getValuePosition(x - 1, y + 1) == 4) {
                            scene.setValuePosition(x, y, 0);
                            scene.setPositionPlayer(player, x - 1, y);
                        }
                        if (scene.getValuePosition(x - 1, y + 1) == 14){
                          //le joueur tombe dans le trou
                            scene.setValuePosition(x, y, 0);
                            scene.setPositionPlayer(player, x - 1, y+1);
                            scene.reSpawnPlayer(player);
                        }

                        if(scene.getValuePosition(x - 1, y) == 11){
                          scene.setValuePosition(x, y, 0);
                          scene.setPositionPlayer(player, x + 2, y);
                        }else if(scene.getValuePosition(x - 1, y) == 10){
                          scene.setValuePosition(x, y, 0);
                          scene.setPositionPlayer(player, x + 2, y);
                        }
                            break;
                    }
                case 's':
                    if (bottom == 3) {
                        scene.setValuePosition(x, y, 0);
                        scene.setPositionPlayer(player, x, y + 1);
                    }

                case 'd':
                    if (right == 0 || right == 3 || right == 14 || right == 11 || right == 4 || right == 13 || left == 16 || left == 5) {
                        if (scene.getValuePosition(x + 1, y + 1) == 2 || scene.getValuePosition(x + 1, y + 1) == 3 || scene.getValuePosition(x + 1, y + 1) == 4) {
                            scene.setValuePosition(x, y, 0);
                            scene.setPositionPlayer(player, x + 1, y);
                        }
                        if (scene.getValuePosition(x - 1, y + 1) == 14){
                          //le joueur se dirige vers l'ennemi
                            scene.setValuePosition(x, y, 0);
                            scene.setPositionPlayer(player, x - 1, y);
                            scene.reSpawnPlayer(player);
                        }
                        if(scene.getValuePosition(x + 1, y) == 11){
                          scene.setValuePosition(x, y, 0);
                          scene.setPositionPlayer(player, x + 2, y);
                        }else if(scene.getValuePosition(x + 1, y) == 10){
                          scene.setValuePosition(x, y, 0);
                          scene.setPositionPlayer(player, x + 2, y);
                        }
                        break;
                    }
            }
        }

        if (player.getType() == 0 || player.getType() == 1) {
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
                    if (left == 0 || left == 3 || left == 14 || left == 11 || left == 4 || left == 13 || left == 16) {
                        if (scene.getValuePosition(x - 1, y + 1) == 2 || scene.getValuePosition(x - 1, y + 1) == 3 || scene.getValuePosition(x - 1, y + 1) == 4) {
                            scene.setValuePosition(x, y, 0);
                            scene.setPositionPlayer(player, x - 1, y);
                        }
                        break;
                    }
                    if (left == 5 && scene.getValuePosition(x - 1, y + 1) != 14) {
                        scene.setValuePosition(x, y, 0);
                        scene.setPositionPlayer(player, x - 1, y);
                        player.setScore(100);
                        if (gameManager.getGameState().equals(GameState.MULTIGAME)) {
                            gameManager.getServer().addScore(player, 100);
                        }
                    }
                    if (left == 12) {
                        if (scene.getValuePosition(x - 1, y) == 12) {
                            scene.setValuePosition(x, y, 0);
                            scene.setPositionPlayer(player, x - 1, y);
                        }
                    }
                    if (left == 11 || left == 10) {
                        scene.setValuePosition(x, y, 0);
                        scene.setPositionPlayer(player, x - 2, y);
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
                    if (right == 0 || right == 3 || right == 14 || right == 11 || right == 4 || right == 13 || left == 16) {
                        if (scene.getValuePosition(x + 1, y + 1) == 2 || scene.getValuePosition(x + 1, y + 1) == 3 || scene.getValuePosition(x + 1, y + 1) == 4) {
                            scene.setValuePosition(x, y, 0);
                            scene.setPositionPlayer(player, x + 1, y);
                        }
                        break;
                    }
                    if (right == 5 && scene.getValuePosition(x + 1, y + 1) != 14) {
                        scene.setValuePosition(x, y, 0);
                        scene.setPositionPlayer(player, x + 1, y);
                        player.setScore(100);
                        if (gameManager.getGameState().equals(GameState.MULTIGAME)) {
                            gameManager.getServer().addScore(player, 100);
                        }
                        break;
                    }
                    if (right == 12) {
                        if (scene.getValuePosition(x + 1, y) == 12) {
                            scene.setValuePosition(x, y, 0);
                            scene.setPositionPlayer(player, x + 1, y);
                        }
                    }
                    if (right == 11 || right == 10) {
                        scene.setValuePosition(x, y, 0);
                        scene.setPositionPlayer(player, x + 2, y);
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
    }
}

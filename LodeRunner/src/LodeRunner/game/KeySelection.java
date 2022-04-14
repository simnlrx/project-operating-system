package LodeRunner.game;

import LodeRunner.handler.GameManager;
import LodeRunner.handler.GameState;
import LodeRunner.thread.BlockBreakThread;

public class KeySelection {

    private final Player player;
    private final GameManager gameManager;

    /**
     * Constructeur KeySelection
     *
     * @param player      Player joueur
     * @param gameManager gameManagaer
     */
    public KeySelection(Player player, GameManager gameManager) {
        this.player = player;
        this.gameManager = gameManager;
    }

    public void killPlayer1() {
        if (gameManager.getPlayer1().getLife() == 0) {
            try {
                gameManager.endGame();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
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
                    if (top == 3) {
                        scene.setValuePosition(x, y, 3);
                        scene.setPositionPlayer(player, x, y - 1);
                    }
                    if (top == 10) {
                        scene.setValuePosition(x, y, 3);
                        scene.setPositionPlayer(player, x, y - 1);
                        scene.setValuePosition(gameManager.getPlayer1().getPosX(), gameManager.getPlayer1().getPosY(), 11);
                        scene.reSpawnPlayer(gameManager.getPlayer1());
                        gameManager.getServer().addScore(player, 100);
                        killPlayer1();
                    }
                    break;
                case 'q':
                    if (left == 0 || left == 3 || left == 5 || left == 6 || left == 10 || left == 12 || left == 13) {
                        if (scene.getValuePosition(x - 1, y + 1) == 2 || scene.getValuePosition(x - 1, y + 1) == 14 || scene.getValuePosition(x - 1, y + 1) == 3 || scene.getValuePosition(x - 1, y + 1) == 4) {
                            if (left == 5) {
                                scene.setValuePosition(x, y, 0);
                                scene.setPositionPlayer(player, x - 2, y);
                                break;
                            }
                            if (scene.getValuePosition(x - 1, y + 1) == 14) {
                                scene.setValuePosition(x, y, 0);
                                scene.setPositionPlayer(player, x - 1, y + 1);
                                gameManager.getScene().reSpawnPlayer(player);
                                break;
                            } else {
                                scene.setValuePosition(x, y, 0);
                                scene.setPositionPlayer(player, x - 1, y);
                            }
                        }
                        if (left == 12) {
                            if (scene.getValuePosition(x - 1, y) == 12) {
                                scene.setValuePosition(x, y, 0);
                                scene.setPositionPlayer(player, x - 1, y);
                            }
                        }
                        if (left == 10) {
                            scene.setValuePosition(x, y, 0);
                            scene.setPositionPlayer(player, x - 1, y);
                            scene.setValuePosition(gameManager.getPlayer1().getPosX(), gameManager.getPlayer1().getPosY(), 11);
                            scene.reSpawnPlayer(gameManager.getPlayer1());
                            gameManager.getServer().addScore(player, 100);
                            killPlayer1();
                        }
                    }
                    break;
                case 's':
                    if (bottom == 3) {
                        scene.setValuePosition(x, y, 3);
                        scene.setPositionPlayer(player, x, y + 1);
                    }
                    if (bottom == 10) {
                        scene.setValuePosition(x, y, 3);
                        scene.setPositionPlayer(player, x, y + 1);
                        scene.setValuePosition(gameManager.getPlayer1().getPosX(), gameManager.getPlayer1().getPosY(), 11);
                        scene.reSpawnPlayer(gameManager.getPlayer1());
                        gameManager.getServer().addScore(player, 100);
                        killPlayer1();
                    }
                    break;
                case 'd':
                    if (right == 0 || right == 3 || right == 5 || right == 6 || right == 10 || right == 12 || right == 13) {
                        if (scene.getValuePosition(x + 1, y + 1) == 2 || scene.getValuePosition(x + 1, y + 1) == 14 || scene.getValuePosition(x + 1, y + 1) == 3 || scene.getValuePosition(x + 1, y + 1) == 4) {
                            if (right == 5) {
                                scene.setValuePosition(x, y, 0);
                                scene.setPositionPlayer(player, x + 2, y);
                                break;
                            }
                            if (scene.getValuePosition(x + 1, y + 1) == 14) {
                                scene.setValuePosition(x, y, 0);
                                scene.setPositionPlayer(player, x + 1, y + 1);
                                gameManager.getScene().reSpawnPlayer(player);
                                break;
                            } else {
                                scene.setValuePosition(x, y, 0);
                                scene.setPositionPlayer(player, x + 1, y);
                            }
                        }
                        if (right == 12) {
                            if (scene.getValuePosition(x + 1, y) == 12) {
                                scene.setValuePosition(x, y, 0);
                                scene.setPositionPlayer(player, x + 1, y);
                            }
                        }
                        if (right == 10) {
                            scene.setValuePosition(x, y, 0);
                            scene.setPositionPlayer(player, x + 1, y);
                            scene.setValuePosition(gameManager.getPlayer1().getPosX(), gameManager.getPlayer1().getPosY(), 0);
                            scene.reSpawnPlayer(gameManager.getPlayer1());
                            gameManager.getServer().addScore(player, 100);
                            killPlayer1();
                        }
                    }
                    break;
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
                    if (left == 0 || left == 3 || left == 14 || left == 4 || left == 13 || left == 16) {
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
                    if (left == 11 && gameManager.getPlayer2().getType() != 2 && scene.getValuePosition(x - 2, y) != 9) {
                        scene.setValuePosition(x, y, 0);
                        scene.setPositionPlayer(player, x - 2, y);
                        break;
                    } else if (left == 10 && scene.getValuePosition(x - 2, y) != 9) {
                        scene.setValuePosition(x, y, 0);
                        scene.setPositionPlayer(player, x - 2, y);
                        break;
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
                    if (right == 0 || right == 3 || right == 14 || right == 4 || right == 13 || right == 16) {
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
                    if (right == 11 && gameManager.getPlayer2().getType() != 2 && scene.getValuePosition(x - 2, y) != 9) {
                        scene.setValuePosition(x, y, 0);
                        scene.setPositionPlayer(player, x + 2, y);
                        break;
                    } else if (right == 10 && scene.getValuePosition(x + 2, y) != 9) {
                        scene.setValuePosition(x, y, 0);
                        scene.setPositionPlayer(player, x + 2, y);
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
    }
}

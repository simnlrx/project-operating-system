package LodeRunner.game;

import LodeRunner.handler.GameManager;
import LodeRunner.thread.BlockBreakThread;

public class KeySelection {

  private final Player player;
  private final Scene scene;
  private final GameManager gameManager;

  public KeySelection(Player player, GameManager gameManager) {
    this.player = player;
    this.scene = gameManager.getScene();
    this.gameManager = gameManager;
  }

  public void setKey(char key) {
    int x = player.getPosX();
    int y = player.getPosY();

    int top = scene.getValuePosition(x, y - 1);
    int bottom = scene.getValuePosition(x, y + 1);
    int left = scene.getValuePosition((x - 1), y);
    int right = scene.getValuePosition((x + 1), y);

    switch (key) {
      case 'z':
      if (top == 3 || top == 15) {
        scene.setValuePosition(x, y, 3);
        scene.setPositionPlayer(player, x, y - 1);
      }
      break;
      case 'q':
      if (left == 0 || left == 3 || left == 14 || left == 11 || left == 4) {
        if (scene.getValuePosition(x - 1, y + 1) == 2 || scene.getValuePosition(x - 1, y + 1) == 3 || scene.getValuePosition(x - 1, y + 1) == 4) {
          scene.setValuePosition(x, y, 0);
          scene.setPositionPlayer(player, x - 1, y);
        }
        break;
      }
      if (left == 5) {
        scene.setValuePosition(x, y, 0);
        scene.setPositionPlayer(player, x - 1, y);
        player.addScore(100);
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
      if (bottom == 3) {
        scene.setValuePosition(x, y, 0);
        scene.setPositionPlayer(player, x, y + 1);
      }
      break;
      case 'd':
      if (right == 0 || right == 3 || right ==14 || right == 11 || right == 4) {
        if (scene.getValuePosition(x + 1, y + 1) == 2 || scene.getValuePosition(x + 1, y + 1) == 3 || scene.getValuePosition(x + 1, y + 1) == 4) {
          scene.setValuePosition(x, y, 0);
          scene.setPositionPlayer(player, x + 1, y);
        }
        break;
      }
      if (right == 5) {
        scene.setValuePosition(x, y, 0);
        scene.setPositionPlayer(player, x + 1, y);
        player.addScore(100);
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
      if (scene.getValuePosition(x + 1, y) == 2) {
        BlockBreakThread blockBreakE = new BlockBreakThread(gameManager, x + 1, y);
        blockBreakE.start();
      }
      break;
      case 'a':
      if (scene.getValuePosition(x - 1, y) == 2) {
        BlockBreakThread blockBreakA = new BlockBreakThread(gameManager, x - 1, y);
        blockBreakA.start();
      }
      break;
      case 'w':
      if (scene.getValuePosition(x - 1, y + 1) == 2) {
        BlockBreakThread blockBreakW = new BlockBreakThread(gameManager, x - 1, y + 1);
        blockBreakW.start();
      }
      break;
      case 'c':
      if (scene.getValuePosition(x + 1, y + 1) == 2) {
        BlockBreakThread blockBreakW = new BlockBreakThread(gameManager, x + 1, y + 1);
        blockBreakW.start();
      }
      break;

    }
  }
}

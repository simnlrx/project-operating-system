package LoadRunner.game;

public class Moove {

    private final Player player;
    private final Scene scene;

    public Moove(Player player, Scene scene) {
        this.player = player;
        this.scene = scene;
    }

    public void setmoove(char direction) {
        switch (direction) {
            case 'z':
                if (scene.getValuePosition(player.getPosX(), player.getPosY() - 1) == 3) {
                    scene.setValuePosition(player.getPosX(), player.getPosY(), 0);
                    player.setPosition(player.getPosX(), player.getPosY() - 1);
                }
                break;
            case 'q':
                if (scene.getValuePosition(player.getPosX() - 1, player.getPosY() + 1) == 0) {
                    scene.setValuePosition(player.getPosX(), player.getPosY(), 0);
                    player.setPosition(player.getPosX() - 1, player.getPosY());
                }
                break;
            case 's':
                if (scene.getValuePosition(player.getPosX(), player.getPosY() + 1) == 3) {
                    scene.setValuePosition(player.getPosX(), player.getPosY(), 0);
                    player.setPosition(player.getPosX(), player.getPosY() + 1);
                }
                break;
            case 'd':
                if (scene.getValuePosition(player.getPosX() + 1, player.getPosY()+1) == 0) {
                    scene.setValuePosition(player.getPosX(), player.getPosY(), 0);
                    player.setPosition(player.getPosX() + 1, player.getPosY() + 1);
                }
                break;
        }
    }
}

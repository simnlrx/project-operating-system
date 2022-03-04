package game;

public class Player {
    //
    private Scene scene;
    private int score;
    private String name;
    private int posX;
    private int posY;

    //constructeur de game.Player
    public Player(Scene scene, int score, String name) {
        this.scene = scene;
        this.score = score;
        this.name = name;
        this.posX = 0;
        this.posY = 0;
    }

    //accesseurs pour le nom du joueur
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    //accesseurs pour le score du joueur
    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return this.score;
    }

    public void setPosPlayer(int x, int y){
      scene.setPosPlayer(x,y);
    }


}

package game;

public class Player {
    //
    private int score;
    private String name;
    private int posX;
    private int posY;

    //constructeur de game.Player
    public Player(int score, String name) {
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
}

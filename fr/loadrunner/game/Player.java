package game;

public class Player {

    private int score;
    private String name;
    private int posX;
    private int posY;
    private final int number;
    private int life;

    /**
     * Constructeur d'un joueur
     * @param score Score du joueur
     * @param name Non du joueur
     * @param number Numéro du joueur
     */
     
    public Player(int score, String name, int number) {
        this.score = score;
        this.name = name;
        this.posX = 0;
        this.posY = 0;
        this.number = number;
        this.life = 3;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name){
      this.name = name;
    }

    //accesseurs pour le score du joueur
    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return this.score;
    }

    public void setPosition(int x, int y){
      this.posX = x;
      this.posY = y;
    }

    public int getNumber() {
        return this.number;
    }

    public int getPosX(){
      return this.posX;
    }

    public int getPosY(){
      return this.posY;
    }

    public int getLife() {
        return life;
    }

    public void addLife() {
        this.life++;
    }

    public void removeLife(){
        this.life--;
    }
}

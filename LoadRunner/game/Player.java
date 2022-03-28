package LoadRunner.game;

public class Player {

    private int score;
    private String name;
    private int posX;
    private int posY;
    private final int number;
    private int life;

    /*
     * Constructeur d'un joueur
     * @param score Score du joueur
     * @param name Non du joueur
     * @param number Numéro du joueur
     */

    public Player(int score, String name, int number) {
        this.score = score;//score initialiser
        this.name = name;//nom initialiser
        this.posX = 0;//position en X
        this.posY = 0;//position en Y
        this.number = number;//numéro du joueur
        this.life = 3;//nombre de vie initialiser à 3
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

    public void getKill(){//méthode pour enlever la vie d'un joueur
        this.life--;
    }

    public void addScore(int score){
        this.score+=score;
    }

    public void addLife() {
        this.life++;
    }

    public void removeLife(){
        this.life--;
    }
}

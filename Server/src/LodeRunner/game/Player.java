package LodeRunner.game;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class Player {

    private int score;
    private String name;
    private int posX;
    private int posY;
    private final int number;
    private int life;
    private boolean ready;
    private int type; //0 -> seul donc gentil 1-> gentil (multi) -> 2 méchant
    private Socket socket;
    private PrintWriter writer;

    /*
     * Constructeur d'un joueur
     * @param score Score du joueur
     * @param name Non du joueur
     * @param number Numéro du joueur
     */

    public Player(String name, int number) {
        this.score = 0;
        // score initialiser
        this.name = name;
        // nom initialiser
        this.posX = 0;
        // position en X
        this.posY = 0;
        // position en Y
        this.number = number;
        // numéro du joueur
        this.life = 5;
        // nombre de vie initialiser à 3
        this.type = 0;
        this.socket = null;
        this.ready = false;
    }

    public void openWriter() throws IOException {
        writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
    }

    public void closeWriter() throws IOException {
        writer.close();
    }

    public void send(String s) throws IOException {
        writer.println(s);
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

    public String getLifeToString(){
        StringBuilder res = new StringBuilder();
        for(int i = 0; i < getLife(); i++){
            res.append("♥");
        }
        return res.toString();
    }

    public void getKill(){
      // méthode pour enlever la vie d'un joueur
        this.life--;
    }

    public void addScore(int score){
      // méthode pour ajouter un score au score du joueur
        this.score+=score;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public boolean isReady() {
        return ready;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
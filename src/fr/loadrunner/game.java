public class Game{
  public static void main(String[]arg){
    Player joueur1 = new Player(10, "Bastien");
    Player joueur2 = new Player(10, "Simon");
    Sceen sceen = new Sceen(8,16);
    sceen.Matrix2Screen();
  }
}

public class Game{
  public static void main(String[]arg){
    Player joueur1 = new Player(10, "Bastien");
    Player joueur2 = new Player(10, "Simon");
    System.out.println(joueur1.getName());
    System.out.println(joueur1.getScore());
  }
}

public class Game{
  public static void main(String[]arg){
    Player joueur1 = new Player(10, "Bastien");
    Player joueur2 = new Player(10, "Simon");
    Sceen sceen = new Sceen(17,34);//les valeurs 17 et 36 sont faites pour coller avec les méthodes de création des escaliers =>17-1(pour le bord)= 4 escaliers
    //sceen.genSceen();//test d'affichage de mla méthode Matrix2Screen
    sceen.printMatrix();
  }
}

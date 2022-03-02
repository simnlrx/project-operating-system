public class Game{
  public static void main(String[]arg){
    Player joueur1 = new Player(0, "Bastien");
    Player joueur2 = new Player(0, "Simon");
    Sceen sceen = new Sceen(18,34,joueur1, joueur2);//les valeurs 17 et 36 sont faites pour coller avec les méthodes de création des escaliers =>17-1(pour le bord)= 4 escaliers
    sceen.genSceenLevel1();//test d'affichage de mla méthode Matrix2Screen
    sceen.matrix2Screen();
  }
}

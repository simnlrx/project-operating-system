public class Game{
  public static void main(String[]arg){
    int optionGame;
    Player joueur1 = new Player(0, "Bastien");
    Player joueur2 = new Player(0, "Simon");
    Scene scene = new Scene(18,34,joueur1);//les valeurs 17 et 36 sont faites pour coller avec les méthodes de création des escaliers =>17-1(pour le bord)= 4 escaliers
    scene.genSceneLevel1();//test d'affichage de mla méthode Matrix2Screen
    scene.matrix2Screen();
    scene.printMatrix();
  }
}

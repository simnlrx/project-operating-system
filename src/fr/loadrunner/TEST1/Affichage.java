package TEST;

public class Affichage{
    private int nbLignes;
    private int nbColonnes;
    private char[][] tab;
    public int x = 20;
    public int y = 5;

    public Affichage(int nbLignes, int nbColonnes){
      this.nbLignes = nbLignes;
      this.nbColonnes = nbColonnes;
      this.tab = new char[nbLignes][nbColonnes];
    }

    public void setCollones(int nbColonnes){
      this.nbColonnes = nbColonnes;
    }

    public void setLignes(int nbLignes){
      this.nbLignes = nbLignes;
    }

    public void afficher(){
      System.out.println("+");
    }

    public void afficheBord(){
      for(int i=0;i<=nbLignes+1;i++){
        System.out.println("+");
      }
      System.out.println("");
    }

    public void afficheLigne(int pos, char c){
      System.out.println("+");
      for(int i=0;i<nbLignes;i++){
        if(i==pos){
          System.out.println('c');
        }else{
          System.out.println(" ");
        }
        System.out.println("+\n");
      }
    }

    public void Affprint(){
      afficheBord();
      for(int i=0;i<nbLignes;i++){
        if(i==y){
          afficheLigne(x,'X');
        }else{
          afficheLigne(-1,'X');
        }
      }
      afficheBord();
    }
}

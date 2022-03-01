public class Sceen{
    private int height; //Hauteur de l'écran
    private int lenght; //Largeur de l'écran
    private int tab[][]; //scene représentée par un tableau en 2D

    //constructeur de Sceen
    public Sceen(int height, int lenght){
      this.height = height;
      this.lenght = lenght;
      this.tab = new int[height][lenght];
      //initialisation d'une Sceen remplies préalablement de 0
      for(int i=0;i<this.height;i++){
        for(int y=0;y<this.lenght;y++){
          //déclarations des bordures du terrain
          if(i==0 || i==(this.height-1)){
            tab[i][y] = 1;
          }else if(y==0 || y==(this.lenght-1)){
            tab[i][y] = 1;
          }else{
            tab[i][y]= 0;
          }
        }
      }
    }

    //fonction permettant d'afficher la matrice de l'écran
    public void printMatrix(){
      for(int i=0;i<(this.height);i++){
        for(int y=0;y<(this.lenght);y++){
          System.out.print(tab[i][y]+" ");
        }
        System.out.println("");
      }
    }
    //fonction permettant de transformer 
}

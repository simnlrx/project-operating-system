// -- Lexique des différents entiers avec leur représentation graphique --
// 0 - représente un espace vide dans la scene
// 1 - représente les contours horizontaux de l'écran
// 2 - représente un morceau de platforme
// 3 - représente un morceau d'une échelle
// 4 - représente un ennemi
// 5 - représente un objet à récupérer
// 9 - représente les contours vertiacaux de l'écran
// 10 - représente le joueur courant
// 11 - représente le joueur en multijoueur


public class Sceen{
    private int height; //Hauteur de l'écran
    private int lenght; //Largeur de l'écran
    private int tab[][]; //scene représentée par une matrice 2*2

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
            tab[i][y] = 9;
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
    //fonction permettant d'afficher l'écran à partir de la matrice
    public void Matrix2Screen(){
      int valor;
      for(int i=0;i<(this.height);i++){
        for(int y=0;y<(this.lenght);y++){
          valor = tab[i][y];
          switch(valor){
            case 0: {System.out.print(" ");break;}
            case 1: {System.out.print("–");break;}
            case 2: {System.out.print("=");break;}
            case 3: {System.out.print("#");break;}
            case 4: {System.out.print("4");break;}
            case 5: {System.out.print("☼");break;}
            case 9: {System.out.print("|");break;}
            case 10: {System.out.print("1");break;}
            case 11: {System.out.print("2");break;}
          }
        }
        System.out.println("");
      }
    }

}

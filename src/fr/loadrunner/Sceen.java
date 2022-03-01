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
    private int baseStairsYNextPlatform;

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
    public void matrix2Screen(){
      int value;
      for(int i=0;i<(this.height);i++){
        for(int y=0;y<(this.lenght);y++){
          value = tab[i][y];
          switch(value){
            case 0: {System.out.print(" ");break;}//espace vide
            case 1: {System.out.print("–");break;}//bord horizontal
            case 2: {System.out.print("=");break;}//platforme
            case 3: {System.out.print("#");break;}//échelle
            case 4: {System.out.print("4");break;}//simulation d'un ennemi en attente d'un symbole
            case 5: {System.out.print("☼");break;}//simulation d'un objet
            case 9: {System.out.print("|");break;}//bord vertical
            case 10: {System.out.print("1");break;}//simulation du joueur courant en attendant un symbole
            case 11: {System.out.print("2");break;}//simulation du joueur 2 en attendant un symbole
          }
        }
        System.out.println("");
      }
    }

    //fonction permettant de générée la scene principale du jeu
    public void genSceen(){
      boolean direction= true;//sens de création de la platforme (true=gauche à droite, false = droite à gauche)
      int baseStairsX = (int)(Math.random()*32)+1;//premier random permetant de trouver la base du premier escalier
      int baseStairsY = this.height-1;//emplacement
      for(int i=0;i<4;i++){//boucle pour générer les 4 différents escaliers
        genPlatform(baseStairsX,baseStairsY,direction);
        direction = !direction;
        for(int y=1;y<=4;y++){//création de l'escalier lié à la platforme
          if(tab[baseStairsY-1][baseStairsYNextPlatform]!=1){
            tab[baseStairsY-1][baseStairsYNextPlatform] = 3;//la valeur de la case prend 3 pour placerun escalier
            baseStairsY--;
          }
        }
      }
    }

    //fonction qui va permettre de générée une platforme
    //renvoie selon l'axe x, l'endroit de la prochaine base de l'escalier
    public void genPlatform(int baseStairsX, int baseStairsY, boolean direction){
      int nbPlatform = (int)(Math.random()*10)+1;//créé un nombre aléatoire de parties d'une platforme
      int edegeOfPlat;//pointeur qui va pointer l'endroit de la génération d'une platforme
      int baseStairsYNextPlatform = tab[baseStairsY][baseStairsX];//base de la prochaine platforme initialisée sur la premiere paltaforme
      if(direction){//création de platformes de gauche à droite
        edegeOfPlat = baseStairsX;
          if(tab[baseStairsY][edegeOfPlat-3]==9 || tab[baseStairsY][edegeOfPlat-2]==9 || tab[baseStairsY][edegeOfPlat-1]==9){//Si un bord est sur le chemin de la création de la platforme
            edegeOfPlat = baseStairsY;//alors il n'y a pas de bord gauche
          }else{// si il n'y a pas de bord dans sur le chemin de la généaration des platformes
            edegeOfPlat = baseStairsX-3;//le bord se situe à gauche de la platforme
          }
        while(nbPlatform>0){//tant qu'il y a des platformes de disponible, création d'une nouvelle partie de la platforme
              System.out.println("C'est ok 1");
          if(tab[edegeOfPlat][baseStairsX]==9){
            nbPlatform=0;//Avant de commencer la génératio, si le pointeur pointe ver un bord, on stop la génération
          }
          if(tab[edegeOfPlat][baseStairsX]==3){//si la génération de platforme rencontre un escalier, il avance d'un cran
            edegeOfPlat++;//on passe l'escalier
          }else if(tab[edegeOfPlat][baseStairsX]!=3){//si il n'y a pas d'escalier à l'endroit actuel et pas de bord
            this.tab[edegeOfPlat][baseStairsX]=2;//la case de la matrice prend la valeur 2 (une platforme)
            edegeOfPlat++;//on incrémente la base de la platforme poiur la décaler de 1 unitée à gauche
            nbPlatform--;//on enleve 1 platforme du nombre de platforme à posées
          }
        }
      }else{//création de platformes de droite à gauche
        edegeOfPlat = baseStairsX;
          if(tab[baseStairsY][edegeOfPlat+3]==9 || tab[baseStairsY][edegeOfPlat+2]==9 || tab[baseStairsY][edegeOfPlat+1]==9){//Si un bord est sur le chemin de la création de la platforme
            edegeOfPlat = baseStairsY;//alors il n'y a pas de bord droit
          }else{//si il n'y a pas de bord dans sur le chemin de la généaration des platformes
          edegeOfPlat = baseStairsX+3;//le bord se situe à droite de la platforme
          }
        while(nbPlatform!=0){//tant qu'il y a des platformes de disponible, création d'une nouvelle partie de la platforme
          if(tab[baseStairsY][edegeOfPlat]==9) nbPlatform=0;//Avant de commencer la génération, si le pointeur pointe ver un bord, on stop la génération
          if(tab[baseStairsY][edegeOfPlat]==3){//si la génération de platforme rencontre un escalier, il avance d'un cran
            edegeOfPlat--;//si un escalier est déja sur cete case, on passe l'escalier
          }else if(tab[baseStairsY][edegeOfPlat]!=3){//si il n'y a pas d'escalier à l'endroit actuel et pas de bord
            this.tab[baseStairsY][edegeOfPlat]=2;//la case de la matrice va prendre la valeur 2 (un escalier)
            edegeOfPlat--;//on décale le bord
            nbPlatform--;//on enleve 1 platforme du nombre de platforme à posées
          }
        }
      }
      while(tab[baseStairsY][baseStairsYNextPlatform]==1 || tab[baseStairsY][baseStairsYNextPlatform]==0){//tant que la base de la prochaine platforme se situe sur un escalier ou dans le vide
        baseStairsYNextPlatform = (int)(Math.random()*34)+1;//on tire un nouvel endroit
      }
      this.baseStairsYNextPlatform = baseStairsYNextPlatform;
    }

}

package LodeRunner.thread;

import LodeRunner.game.Scene;
import LodeRunner.game.Player;
import LodeRunner.handler.GameManager;


public class EnemyThread extends Thread {
    private int posX;
    // position en X de l'ennemi
    private int posY;
    // position en Y de l'ennemi
    private Scene scene;
    // scene de l'ennemi
    private GameManager gameManager;
    // GameManager
    private Player player1;
    // joueur 1
    private Player player2;
    // joueur 2

    /*
     * Constructeur de EnemyThread
     * @param int posX pour la position en X de l'ennemi
     * @param int posY pour la position en Y de l'ennemi
     * @param GameManager gameManager
     */

    public EnemyThread(int posX, int posY, GameManager gameManager) {
        this.scene = gameManager.getScene();
        this.posX = posX;
        this.posY = posY;
        scene.setValuePosition(posX, posY, 4);
        // les coordonnées de l'ennemi sont directement placés dans la scene
        this.gameManager = gameManager;
        this.player1 = gameManager.getScene().getPlayer1();
        this.player2 = gameManager.getScene().getPlayer1();
    }

    public double getDistanceToPlayer1(int posXEnemy, int posYEnemy) {
        // méthode pour le calcul de la distance entre un ennemi et le joueur 1
        double dist = Math.sqrt(Math.pow((posXEnemy - player1.getPosX()), 2) + Math.pow((posYEnemy - player1.getPosY()), 2));
        return dist;
    }

    public double getDistanceToPlayer2(int posXEnemy, int posYEnemy) {
        // méthode pour le calcul de la distance entre un ennemi et le joueur 2
        double dist = Math.sqrt(Math.pow((posXEnemy - player2.getPosX()), 2) + Math.pow((posYEnemy - player2.getPosY()), 2));
        return dist;
    }

    public synchronized void KillPlayer() {
        // méthode qui permet de tuer un joueur au contact d'un ennemi, et d'engendrer les conséquences occasionées
        // le jeu nécessite que le joueur 1 doit etre en vie
        if (player1.getLife() >= 1) {
            if ((posX == player1.getPosX() && posY == player1.getPosY())) {
                // on enleve le joueur de la scene lorsqu'il est mort et on le remplace à l'endroit du spawn
                scene.reSpawnPlayer(player1);
            } else if (posX == player2.getPosX() && posY == player2.getPosY()) {
                scene.reSpawnPlayer(player2);
            }
        } else {
            try {
                gameManager.endGame();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void downStairs() {
        // méthode permettant de faire descendre les escaliers à un ennemi
        if (scene.getValuePosition(posX, posY + 1) == 3 || scene.getValuePosition(posX, posY + 1) == 10) {
            //vérification si le bloc suivant est un escalier et pas une platforme
            scene.setValuePosition(posX, posY, 0);
            //postion actuelle de l'ennemi placée à la valeur 0
            this.posY++;
            // incrémentation de sa position
            scene.setValuePosition(posX, posY, 4);
            //valeur de l'ennemi placé à sa nouvelle position
        }
    }

    public synchronized void upStairs() {
        // méthode permettant de faire monter les escaliers à un ennemi
        if (scene.getValuePosition(posX, posY - 1) == 3 || scene.getValuePosition(posX, posY - 1) == 10) {
            //vérification si le bloc suivant est un escalier et pas une platforme
            scene.setValuePosition(posX, posY, 0);
            //postion actuelle de l'ennemi placée à la valeur 0
            this.posY--;
            // décrémentation de sa position
            scene.setValuePosition(posX, posY, 4);
            //valeur de l'ennemi placé à sa nouvelle position
        }
    }

    public synchronized void moveLeft() {
        // méthode permettant de déplacer un ennemi vers la gauche
        int valueBlocInf = scene.getValuePosition(posX - 1, posY + 1);
        // récupération de la valeur du bloc en bas à gauche
        int valueBlocMid = scene.getValuePosition(posX - 1, posY);
        // récupérationn de la valeur du bloc à gauche

        if (valueBlocInf != 0 && valueBlocInf != 9 && valueBlocMid != 12) {
            // si le bloc inférieur gauche est différents d'un vide et qu'il est différent d'un bord de l'écran et que ce n'est pas une passerelle
            if (valueBlocMid == 5) {
                // et si le prochain bloc est un object
                scene.setValuePosition(posX, posY, 0);
                // on supprime l'ennemi de la scene
                this.posX = posX - 2;
                // deplacement de 2 blocs à gauche
                scene.setValuePosition(posX, posY, 4);
                // on replace l'ennemi de la scene
            } else if (valueBlocMid != 2 && valueBlocInf == 14) {
                //si le bloc en face de l'ennemi est diférrent d'une platforme et différent d'une paserelle
                scene.setValuePosition(posX, posY, 0);
                this.posX--;
                this.posY++;
                scene.setValuePosition(posX, posY, 4);
                try {
                    sleep(5000);
                    //on endort l'ennemi 5 secondes avant le respawn
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //on replace l'ennemi à l'endroit du sapwn des ennemis
                player1.addScore(50);
                //le joueur gagne 50 points
                scene.setValuePosition(posX, posY, 2);
                posX = scene.getPosXSpawnEnemy();
                posY = scene.getPosYSpawnEnemy();
                scene.setValuePosition(posX, posY, 4);
            } else if (valueBlocMid != 2 && valueBlocMid != 4 && (valueBlocInf == 2 || valueBlocInf == 3)) {
                scene.setValuePosition(posX, posY, 0);
                this.posX--;
                scene.setValuePosition(posX, posY, 4);
            }
        }
    }

    public synchronized void moveRight() {
        // méthode permettant de déplacer un ennemi vers la la droite
        int valueBlocInf = scene.getValuePosition(posX + 1, posY + 1);
        // récupération de la valeur du bloc en bas à droite
        int valueBlocMid = scene.getValuePosition(posX + 1, posY);
        // récupérationn de la valeur du bloc à droite

        if (valueBlocInf != 0 && valueBlocInf != 9 && valueBlocMid != 12) {
            // si le bloc inférieur droit est différents d'un vide et qu'il est différent d'un bord de l'écran et que ce n'est pas une passerelle
            if (valueBlocMid == 5) {
                // et si le prochain bloc est un object
                scene.setValuePosition(posX, posY, 0);
                // on supprime l'ennemi de la scene
                this.posX = posX + 2;
                // deplacement de 2 blocs à gauche
                scene.setValuePosition(posX, posY, 4);
                // on replace l'ennemi de la scene
            } else if (valueBlocMid != 2 && valueBlocInf == 14) {
                // si le bloc en face de l'ennemi est diférrent d'une platforme et différent d'une paserelle
                scene.setValuePosition(posX, posY, 0);
                // on supprime l'ennemi de la scene
                this.posX++;
                this.posY++;
                scene.setValuePosition(posX, posY, 4);
                try {
                    sleep(5000);
                    // on endort l'ennemi 5 secondes avant le respawn
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //on replace l'ennemi à l'endroit du sapwn des ennemis
                player1.addScore(50);
                //le joueur gagne 50 points
                scene.setValuePosition(posX, posY, 2);
                posX = scene.getPosXSpawnEnemy();
                posY = scene.getPosYSpawnEnemy();
                scene.setValuePosition(posX, posY, 4);

            } else if (valueBlocMid != 2 && valueBlocMid != 4 && (valueBlocInf == 2 || valueBlocInf == 3)) {
                scene.setValuePosition(posX, posY, 0);
                this.posX++;
                scene.setValuePosition(posX, posY, 4);
            }
        }
    }


    @Override
    public void run() {
        try {
            while (gameManager.getGameState().isGame()) {
                this.sleep(300);
                if (player2 == null) {
                    if (getDistanceToPlayer1(posX, posY + 1) < getDistanceToPlayer1(posX, posY)) {
                        // vérification si un deplacement vers le haut pourrai rapprocher l'ennemi du joueur
                        downStairs();
                    } else if (getDistanceToPlayer1(posX, posY - 1) < getDistanceToPlayer1(posX, posY)) {// si un deplacement vers le haut éloigne l'ennemi
                        upStairs();
                    }
                    if (getDistanceToPlayer1(posX - 1, posY) < getDistanceToPlayer1(posX, posY)) {
                        // vérification si un deplacement vers la gauche pourrai rapprocher l'ennemi du joueur
                        moveLeft();
                    } else {// si un deplacement vers la gauche éloigne l'ennemi
                        moveRight();
                    }
                    KillPlayer();
                } else {
                    if (getDistanceToPlayer1(posX, posY) < getDistanceToPlayer2(posX, posY)) {
                        if (getDistanceToPlayer1(posX, posY + 1) < getDistanceToPlayer1(posX, posY)) {
                            // vérification si un deplacement vers le haut pourrai rapprocher l'ennemi du joueur
                            downStairs();
                        } else if (getDistanceToPlayer1(posX, posY - 1) < getDistanceToPlayer1(posX, posY)) {// si un deplacement vers le haut éloigne l'ennemi
                            upStairs();
                        }
                        if (getDistanceToPlayer1(posX - 1, posY) < getDistanceToPlayer1(posX, posY)) {
                            // vérification si un deplacement vers la gauche pourrai rapprocher l'ennemi du joueur
                            moveLeft();
                        } else {// si un deplacement vers la gauche éloigne l'ennemi
                            moveRight();
                        }
                        KillPlayer();
                    } else {
                        if (getDistanceToPlayer2(posX, posY + 1) < getDistanceToPlayer2(posX, posY)) {
                            // vérification si un deplacement vers le haut pourrai rapprocher l'ennemi du joueur
                            downStairs();
                        } else if (getDistanceToPlayer2(posX, posY - 1) < getDistanceToPlayer2(posX, posY)) {// si un deplacement vers le haut éloigne l'ennemi
                            upStairs();
                        }
                        if (getDistanceToPlayer2(posX - 1, posY) < getDistanceToPlayer2(posX, posY)) {
                            // vérification si un deplacement vers la gauche pourrai rapprocher l'ennemi du joueur
                            moveLeft();
                        } else {// si un deplacement vers la gauche éloigne l'ennemi
                            moveRight();
                        }
                        KillPlayer();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

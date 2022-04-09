package LodeRunner.handler;

public enum GameState {

    LOADING("loading", false),
    SOLOGAME("solo", true),
    MULTIGAME("multi", true),
    END("end", false);

    private String name;
    // nom de l'étape du jeu
    private boolean isgame;
    // état de la partie

    /*
     * Constructeur d'un GameState
     * @param name String retourne nom de l'étape du jeu
     * @param isgame boolean retourne l'état de la partie
     */

    GameState(String name, boolean isgame) {
        this.name = name;
        this.isgame = isgame;
    }

    public String getName() {
        return name;
    }

    public boolean isGame() {
        return isgame;
    }

    public void setIsgame(boolean isgame) {
        this.isgame = isgame;
    }
}

package LodeRunner.handler;

public enum GameState {

    GAMEMODE("select gamemode", false),
    LEVEL("select level", false),
    LOADING("loading", false),
    SOLOGAME("solo", true),
    MULTIGAME("multi", true),
    END("end", false);

    private String name;
    private boolean isgame;

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

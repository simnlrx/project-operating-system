package fr.loadrunner.handler;

public enum GameState {

    LOADING("loading", true),
    SOLOGAME("solo", false),
    MULTIGAME("multi", false),
    END("end", false);

    private String name;
    private boolean state;

    GameState(String name, boolean state) {
        this.name = name;
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}

package handler;

import utils.Display;

public class MainSreenManager {

    private final Display display = new Display();
    private String color;

    public MainSreenManager() {}
    public MainSreenManager(String color) {
        this.color = color;
    }

    public void start(){
        display.title();
    }


}

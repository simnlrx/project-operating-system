package LodeRunner.handler;

import java.util.ArrayList;

public class ThreadManager {
    private final ArrayList<Thread> threadList;
    // déclaration d'une ArrayList de Thread

    /**
     * Constructeur par défaut de ThreadManager
     * instanciation d'une ArrayList de Thread
     */
    public ThreadManager() {
        this.threadList = new ArrayList<>();
    }

    public synchronized void startThreads() {
        // méthode permettant de démarrer l'ensemble des threads de la liste
        for (int i = 0; i < threadList.size(); i++) {
            if (!threadList.get(i).isAlive()) {
                threadList.get(i).start();
            }
        }
    }

    public void addThread(Thread thread) {
        // méthode permettant d'ajouter un thread à la liste
        threadList.add(thread);
    }
}

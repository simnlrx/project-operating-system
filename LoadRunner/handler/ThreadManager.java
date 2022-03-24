package LoadRunner.handler;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

import LoadRunner.thread.EnemyThread;
import LoadRunner.thread.RefreshScene;
import LoadRunner.thread.RegenSceneThread;

public class ThreadManager{
  private final ArrayList<Thread> threadList;

  public ThreadManager(){
    this.threadList = new ArrayList<Thread>();
  }

  public void startThreads(){
    for(int i=0;i<threadList.size();i++){
      threadList.get(i).start();
    }
  }

  public void addThread(Thread thread){
    threadList.add(thread);
  }

  public ArrayList getThreadList(){
    return threadList;
  }
}

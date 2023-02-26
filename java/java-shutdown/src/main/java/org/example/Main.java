package org.example;

import java.time.LocalDateTime;

public class Main {
  public static void main(String[] args) {
    int counter = 0;
    Thread workerThread = Thread.currentThread();
    Runtime.getRuntime().addShutdownHook(new Thread() {
      @Override
      public void run() {
        try {
          System.out.println("Inside termination Shutdown Hook");
          workerThread.interrupt(); // Tell the worker thread to end
          System.out.println("Waiting for the main worker to end");
          workerThread.join(60 * 1000); // Wait for the worker thread to end
          System.out.println("The main worker thread has ended");
        } catch(Exception e) {
          e.printStackTrace();
        }
      }
    }); // End of shutdown hook
    while(!workerThread.interrupted()) {
      counter++;
      System.out.println("Count: " + counter + ", now: " + LocalDateTime.now());
      try {
        workerThread.sleep(1 * 1000);
      } catch (InterruptedException e) {
        workerThread.currentThread().interrupt();
      }
    }
    System.out.println("All done ... The main app thread has terminated");
  }
}
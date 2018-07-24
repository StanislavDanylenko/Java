package ua.nure.danylenko.Practice5.Part1;

public class RunThread implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 6; i++){
            System.out.println(Thread.currentThread().getName());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}

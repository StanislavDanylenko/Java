package ua.nure.danylenko.Practice5.Part1;

public class ExThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 6; i++){
            System.out.println(getName());
            try {
                sleep(500);
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}

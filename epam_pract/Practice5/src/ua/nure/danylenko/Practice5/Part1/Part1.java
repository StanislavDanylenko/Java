package ua.nure.danylenko.Practice5.Part1;

public class Part1 {
    public static void main(String[] args) {
        Thread t1 = new ExThread();
        Thread t2 = new Thread(new RunThread());

        t1.start();
        t2.start();
    }
}

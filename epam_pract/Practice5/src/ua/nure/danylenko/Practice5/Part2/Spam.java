package ua.nure.danylenko.Practice5.Part2;

import java.util.Scanner;

public class Spam {
    long[] millis;
    String[] messages;
    Thread[] thrds;

    public Spam(long[] millis, String[] messages){
        this.millis = millis;
        this.messages = messages;
        thrds = new Thread[millis.length];
    }

    public void start() {
        for (int i = 0; i < millis.length; i++){
            thrds[i] = new MyThread(millis[i], messages[i]);
            thrds[i].start();
        }
    }

    public void stop() {
        for (Thread tr : thrds){
            tr.interrupt();
        }
    }

    public static void main(String[] args) {
        Spam spam = new Spam(new long[]{100, 200, 300, 400, 500}, new String[]{"AAAA", "BBBBB",
                "CCCCC","DDDDD","EEEEE"});
        spam.start();
        // wait for Enter
        Scanner in = new Scanner(System.in);
        in.nextLine(); // return only if Enter obtained
        in.close();
        spam.stop();
    }
}

package ua.nure.danylenko.task5.Part1;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class Spam {
    private static final InputStream STD_IN = System.in;
    private static final String ENCODING = "Cp1251";

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

    public static void main(String[] args) throws InterruptedException {
        Spam spam = new Spam(new long[]{100, 200, 300, 400, 500}, new String[]{"AAAA", "BBBBB",
                "CCCCC","DDDDD","EEEEE"});
        // wait for Enter
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(3000);
                    System.setIn(new ByteArrayInputStream((System.lineSeparator()).getBytes(ENCODING)));
                } catch (InterruptedException e) {
                    return;
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
        spam.start();
        thread.join();

        Scanner in = new Scanner(System.in);
        String line = null;

        if("".equals(line = in.nextLine())){ // return only if Enter obtained
           in.close();
           spam.stop();
           System.setIn(STD_IN);
        }
    }
}

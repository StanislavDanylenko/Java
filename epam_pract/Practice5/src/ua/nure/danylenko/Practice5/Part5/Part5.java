package ua.nure.danylenko.Practice5.Part5;

import java.io.IOException;
import java.io.RandomAccessFile;

public class Part5 extends Thread{
    private RandomAccessFile rac;
    Thread[] threads = new Thread[10];
    public static final int LENGTH = System.lineSeparator().length();

    public Part5(RandomAccessFile file) {
        rac = file;
    }

    @Override
    public void run() {
        for (int num = 0; num < 10; num++) {
            int startPos = num * (20 + LENGTH);
            if (num == 0) {
                startPos = 0;
            }
            threads[num] = new MyThread(num, startPos, rac);
            threads[num].start();
        }
        for (int i = 0; i < 10; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            WriteClass.rac.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        WriteClass wr = new WriteClass("test.txt");
        Thread t1 = new Part5(wr.getFile());
        t1.start();
        t1.join();
        wr.readFile("test.txt");
    }

}

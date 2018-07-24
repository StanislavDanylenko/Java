package ua.nure.danylenko.task5.Part2;

import java.io.IOException;
import java.io.RandomAccessFile;

public class MyThread extends Thread {
    private int number;
    private int position;
    int countWrite = 21;
    RandomAccessFile file;
    int nums = 1;

    public MyThread(int number, int position, RandomAccessFile file) {
        this.number = number;
        this.position = position;
        this.file = file;
    }

    @Override
    public void run() {
        while (nums <= countWrite) {
            synchronized (file) {
                try {
                    sleep(10);
                    WriteClass.rac.seek(position++);
                    if (nums++ <= 20) {
                        WriteClass.rac.write('0' + number);
                        System.out.println(number + " - " + getName());
                    } else if (number != 9){
                        WriteClass.rac.write(System.lineSeparator().getBytes());
                        System.out.println("LS" + " - " + getName());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

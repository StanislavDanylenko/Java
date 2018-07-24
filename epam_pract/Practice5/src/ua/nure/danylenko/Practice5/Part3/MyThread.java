package ua.nure.danylenko.Practice5.Part3;

public class MyThread extends Thread {
    Counter counter;

    public MyThread(Counter obj) {
        counter = obj;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            synchronized (counter) {
                {
                    System.out.println((counter.c1 == counter.c2) + " - " + getName());
                    counter.c1++;
                    try {
                        sleep(10);
                    } catch (InterruptedException e) {
                        return;
                    }
                    counter.c2++;
                }
            }
        }
    }
}

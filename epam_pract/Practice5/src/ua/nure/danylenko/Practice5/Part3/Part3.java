package ua.nure.danylenko.Practice5.Part3;

public class Part3 {
    public static void main(String[] args) throws InterruptedException {
        Counter obj = new Counter();
        Thread t1 = new MyThread(obj);
        t1.start();
        Thread t2 = new MyThread(obj);
        t2.start();
        Thread t3 = new MyThread(obj);
        t3.start();
        Thread t4 = new MyThread(obj);
        t4.start();
        Thread t5 = new MyThread(obj);
        t5.start();

        t1.join();
        t2.join();
        t3.join();
        t4.join();
        t5.join();
    }
}


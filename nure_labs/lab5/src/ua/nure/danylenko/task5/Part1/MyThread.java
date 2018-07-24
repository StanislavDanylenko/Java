package ua.nure.danylenko.task5.Part1;

class MyThread extends Thread {
    long millis;
    String message;

    public MyThread(long millis, String message){
        this.message = message;
        this.millis = millis;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                sleep(millis);
            } catch (InterruptedException e) {
                return;
            }
            System.out.println(message);
        }
    }
}


package ua.nure.danylenko.Practice5.Part4;

public class Matrix {
    private int[][] arr;
    private Thread[] threads;
    int count;
    public int[] results;

    public Matrix(int x, int y) {
        arr = new int[x][y];
        count = x;
        threads = new Thread[count];
        results = new int[count];
    }

    public void randomFillArray() {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                arr[i][j] = (int) (Math.random() * 1000);
            }
        }
        //Arrays.deepToString(arr);
    }

    public void findMaxValue() {
        long startTime = System.currentTimeMillis();
        int max = arr[0][0];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    return;
                }
                if (arr[i][j] > max) {
                    max = arr[i][j];
                }
            }
        }
        long finishTime = System.currentTimeMillis();

        System.out.println("Максимальное значение: " + max);
        System.out.println("Линейное выполнение: " + (finishTime - startTime));
    }

    public void findMaxValueWithThreads() {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            threads[i] = new MyThread(arr[i], i);
            threads[i].start();
        }
        for (int i = 0; i < count; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                return;
            }
        }

        long finishTime = System.currentTimeMillis();
        System.out.println("Максимальное значение: " + findMaxLine(results));
        System.out.println("Параллельное выполнение: " + (finishTime - startTime));
    }

    public int findMaxLine(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }

    private class MyThread extends Thread {
        int[] arr;
        int pos;

        public MyThread(int[] arr, int pos) {
            this.arr = arr;
            this.pos = pos;
        }

        @Override
        public void run() {
            int max = arr[0];
            for (int i = 1; i < arr.length; i++) {
                try {
                    sleep(1);
                } catch (InterruptedException e) {
                    return;
                }
                if (arr[i] > max) {
                    max = arr[i];
                }
            }
            results[pos] = max;
        }
    }
}

package ua.nure.danylenko.task4.Part4;

import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class Part4 {

    private static void bubbleSort(int[] arr) {
        for (int i = arr.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }
    }

    public static void generate(){
        Random rnd = new Random(System.currentTimeMillis());
        int[] arr = new int[10];
        for (int i = 0; i < 10; i++) {
            arr[i] = rnd.nextInt(51);
        }
        writeFile("part4.txt", "UTF8", arr);
    }
    public static void process() {
        int[] arr = new int[10];
        readFile("part4.txt", "UTF8", arr);
        bubbleSort(arr);
        writeFile("part4_sorted.txt", "UTF8", arr);
    }
    public static void print() {
        StringBuilder start = new StringBuilder();
        StringBuilder finish = new StringBuilder();

        start.append("input  ==> ");
        finish.append("output ==> ");

        int[] arr = new int[10];
        int[] arrSorted = new int[10];

        readFile("part4.txt", "UTF8", arr);
        readFile("part4_sorted.txt", "UTF8", arrSorted);

        fillStringBuilder(start, arr);
        fillStringBuilder(finish, arrSorted);
        System.out.println(start.toString());
        System.out.println(finish.toString());
    }

    private static void writeFile(String fileName, String encoding, int[] arr) {
        try (BufferedWriter nFile = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(fileName), encoding))) {
            for (int i = 0; i < 10; i++) {
                nFile.write(arr[i] + " ");
                nFile.flush();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    private static void readFile(String fileName, String encoding, int[] arr) {
        try {
            Scanner scanner = new Scanner(new File(fileName), encoding);
            while (scanner.hasNextLine()) {
                String[] arrStr = scanner.nextLine().split(" ");
                for (int i = 0; i < 10; i++) {
                    arr[i] = Integer.parseInt(arrStr[i]);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    private static void fillStringBuilder(StringBuilder sb, int[] arr) {
        for (int i = 0; i < 9; i++) {
            sb.append(arr[i] + " ");
        }
        sb.append(arr[9]);
    }

    public static void main(String[] args) {
        generate();
        process();
        print();
    }
}

package ua.nure.danylenko.practice1;

import java.util.Arrays;

public class Part6 {
    public static void main(String args[]) {

        int length = Integer.parseInt(args[0]);
        int[] arr = new int[length];
        int pos = 0;

        for (int i = 2; pos < length && i < Integer.MAX_VALUE; i++) {
            int count = 1;
            for (int j = 1; j <= Math.sqrt(i); j++) {
                if (i % j == 0) {
                    count++;
                    if (count > 2) {
                        break;
                    }
                }
            }
            if (count == 2) {
                arr[pos++] = i;
            }
        }
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}

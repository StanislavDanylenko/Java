package ua.nure.danylenko.task1;

import java.util.Arrays;

public class Part9 {
    public static void getFilledFiveDimensionalArray() {
        int[][][][] fiveDimensionalArray = new int[2][2][2][2];

        for (int i = 0; i < 16; i++) {
            String valueString = "000" + Integer.toBinaryString(i);
            fiveDimensionalArray[valueString.charAt(valueString.length() - 4) - '0']
                    [valueString.charAt(valueString.length() - 3) - '0']
                    [valueString.charAt(valueString.length() - 2) - '0']
                    [valueString.charAt(valueString.length() - 1) - '0'] = i + 1;
        }

        System.out.println(Arrays.deepToString(fiveDimensionalArray));
        // region другой вывод
        /*for (int[][][][] i : fiveDimensionalArray) {
            for (int[][][] j : i) {
                for (int[][] k : j) {
                    for (int[] p : k) {
                        for (int r : p) {
                            System.out.println(r);
                        }
                    }
                }
            }
        }*/
        // endregion
    }

    public static void main(String[] args) {
        getFilledFiveDimensionalArray();
    }

}

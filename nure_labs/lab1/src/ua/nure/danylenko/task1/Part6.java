package ua.nure.danylenko.task1;

import java.util.Arrays;

public class Part6 {

    private static int getFibbonachiNumber(int position) {
        if (position < 1) {
            return -1;
        } else {
            if (position == 1) {
                return 1;
            } else if (position == 2) {
                return 1;
            } else return getFibbonachiNumber(position - 1) + getFibbonachiNumber(position - 2);
        }
    }

    public static int[] getRecArrayWithFibbonachiNumbers(int n) {

        int resultArr[] = new int[n];

        for (int i = 0; i < n; i++) {
            resultArr[i] = getFibbonachiNumber(i + 1);
            System.out.print(resultArr[i] + " ");
        }
        System.out.println();
        return resultArr;
    }

    public static int[] getArrayWithFibbonachiNumbers(int n) {
        int resultArr[] = new int[n];
        int firstAndSecond = 1;

        for (int i = 0; i < n; i++) {
            if (i == 0 || i == 1) {
                resultArr[i] = firstAndSecond;
            } else {
                resultArr[i] = resultArr[i - 1] + resultArr[i - 2];
            }
            //System.out.print(resultArr[i] + " ");
        }
        //System.out.println();
        return resultArr;
    }

    public static void main(String[] args) {
        // region проверка
        if (args != null && args.length > 0) {
            try {
                Integer.parseInt(args[0]);
            } catch (NumberFormatException ex) {
                throw new IllegalArgumentException();
            }
        } else {
            throw new IllegalArgumentException();
        }
        // endregion
        System.out.println(Arrays.toString(getArrayWithFibbonachiNumbers(Integer.parseInt(args[0]))));
    }

}

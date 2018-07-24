package ua.nure.danylenko.task1;

import java.util.*;

public class Part5 {
    // region перебор
   /* public static int getNumOfLackyTicketsByBust(int num) {

        if (num % 2 == 1) return 0;
        int res = 0;
        int bound = 1;
        for (int j = 1; j <= num / 2; bound *= 10, j++) {
        }

        for (int i = 1; i < bound; i++) {

            int countDigits = (int) Math.log10(i) + 1;
            int sum = 0;
            int copyI = i;

            for (int k = 0; k < countDigits; k++) {
                sum += copyI % 10;
                copyI /= 10;
            }
            for (int b = 0; b < bound; b++) {

                int countDigits2 = (int) Math.log10(b) + 1;
                int copyB = b;
                int sum2 = 0;
                for (int z = 0; z < countDigits2; z++) {
                    sum2 += copyB % 10;
                    copyB /= 10;
                }
                if (sum == sum2) {
                    res++;
                    System.out.print("" + i + "-" + b + "\n");
                }
            }
        }
        return res;
    }*/
    // endregion

    private static ArrayList<Integer> getNextArr(List<Integer> prevArr) {
        int newLen = prevArr.size() + 9;
        ArrayList<Integer> arr = new ArrayList<>();
        for (int i = 0; i < newLen; i++) {
            int q = 0;
            for (int j = 0; j < 10; j++) {
                try {
                    q += prevArr.get(i - j);
                } catch (Exception ex) {
                }
            }
            arr.add(i, q);
        }
        return arr;
    }

    public static int getNumOfLackyTicketsByList(int num) {
        List<Integer> arr = new ArrayList<>();
        List<Integer> arr2 = new ArrayList<>();
        int result = 0;
        for (int i = 0; i < 10; i++) {
            arr.add(1);
        }
        for (int i = 0; i < (num / 2 - 1); i++) {
            arr2 = arr;
            arr = getNextArr(arr);
        }
        for (int i = 0; i < arr.size(); i++) {
            int k = (int) Math.pow(arr.get(i), 2);
            try {
                result += k - arr2.get(i) * arr.get(i);
            } catch (Exception ex) {
                result += k;
            }
        }
        if (num == 2) {
            result--;
        }
        return result;
    }

    public static int getNumOfLackyTickets(int num) {
        if (num % 2 != 0) {
            return 0;
        }
        int halfNum = num / 2;
        int[][] dynamicMatrix = new int[halfNum + 1][halfNum * 9 + 1];
        int sizeLine = 10;

        dynamicMatrix[0][0] = 1;
        for (int i = 0; i < sizeLine; i++) {
            dynamicMatrix[1][i] = 1;
        }
        sizeLine += 9;
        for (int j = 2; j <= halfNum; j++) {
            for (int k = 0; k < sizeLine; k++) {
                int sumPrevLine = 0;
                for (int n = ((k - 9 < 0) ? 0 : k - 9); n <= k; n++) {
                    sumPrevLine += dynamicMatrix[j - 1][n];
                }
                dynamicMatrix[j][k] = sumPrevLine;
            }
            sizeLine += 9;
        }
        int result = 0;
        for (int m = 0; m < halfNum * 9 + 1; m++) {
            if (m < (halfNum * 9 + 1 / 2)) {
                result += Math.pow(dynamicMatrix[halfNum][m], 2) - dynamicMatrix[halfNum][m] * dynamicMatrix[halfNum - 1][m];
            } else {
                result += Math.pow(dynamicMatrix[halfNum][m], 2);
            }
        }
        // System.out.println(result);
        return result;
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
        System.out.println(getNumOfLackyTickets(Integer.parseInt(args[0])));
    }

}
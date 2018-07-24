package ua.nure.danylenko.task1;

import java.util.Arrays;

public class Part7 {

    public static int[] getArrayWithPrimeNumbers(int num) {
        int countOfDividers = 0;
        int posInResultArr = 0;
        int[] resultArr = new int[num];

        for (int i = 2; posInResultArr < num && i < Integer.MAX_VALUE; i++) {
            countOfDividers = 1;
            for (int j = 1; j <= Math.sqrt(i); j++) {
                if (i % j == 0) {
                    countOfDividers++;
                    if (countOfDividers > 2) {
                        break;
                    }
                }
            }
            if (countOfDividers < 3) {
                resultArr[posInResultArr++] = i;
            }
        }
        //System.out.print(Arrays.toString(resultArr));
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
        System.out.println(Arrays.toString(getArrayWithPrimeNumbers(Integer.parseInt(args[0]))));
    }

}

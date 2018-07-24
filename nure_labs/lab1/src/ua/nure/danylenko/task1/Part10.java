package ua.nure.danylenko.task1;

public class Part10 {

    public static void getPascalTriangle(int num) {
        long arrayOfValues[][] = new long[num][num];
        for (int i = 0; i < num; i++) {
            arrayOfValues[i][0] = 1;
            arrayOfValues[i][i] = 1;
            for (int k = 1; k < i; k++) {
                arrayOfValues[i][k] = arrayOfValues[i - 1][k - 1] + arrayOfValues[i - 1][k];
            }
        }

        for (int n = 0; n < num; n++) {
            for (int k = 0; k <= n; k++) {
                System.out.format("%d", arrayOfValues[n][k]);
                System.out.print(" ");
            }
            System.out.println();
        }
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
        getPascalTriangle(Integer.parseInt(args[0]));
    }

}
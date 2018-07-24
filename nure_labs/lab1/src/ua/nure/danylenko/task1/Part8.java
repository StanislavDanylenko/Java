package ua.nure.danylenko.task1;

public class Part8 {

    public static char[][] printChessBoard(int n, int m) {
        char[][] resultArr = new char[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i % 2 == 1) {
                    if (j % 2 == 1) {
                        //resultArr[i][j] = 'Ч';
                        resultArr[i][j] = 'C';

                    } else {
                        //resultArr[i][j] = 'Б';
                        resultArr[i][j] = 'B';
                    }
                } else {
                    if (j % 2 == 1) {
                        resultArr[i][j] = 'B';
                        //resultArr[i][j] = 'Б';
                    } else {
                        resultArr[i][j] = 'C';
                        //resultArr[i][j] = 'Ч';
                    }
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(resultArr[i][j] + "  ");
            }
            System.out.println();
        }
        return resultArr;
    }

    public static void main(String[] args) {
        // region проверка
        if (args != null && args.length > 1) {
            try {
                Integer.parseInt(args[0]);
                Integer.parseInt(args[1]);
            } catch (NumberFormatException ex) {
                throw new IllegalArgumentException();
            }
        } else {
            throw new IllegalArgumentException();
        }
        // endregion
        printChessBoard(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
    }

}

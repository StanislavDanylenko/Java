package ua.nure.danylenko.task1;

public class Part4 {

    public static int getSumOfFactorials(int num) {
        int sumOfFactorials = 1;
        int res = 1;

        for (int i = 2; i <= num; i++) {
            sumOfFactorials *= i * -1;
            res += sumOfFactorials;
        }
        return res;
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
        System.out.println(getSumOfFactorials(Integer.parseInt(args[0])));
    }

}

package ua.nure.danylenko.task1;

public class Part3 {

    public static boolean isPrimeNumber(int num) {
        int sqrtOfNumber = (int)Math.sqrt(num);
        if (num < 2) {
            return false;
        } else {
            for (int i = 2; i < sqrtOfNumber; i++) {
                if (num % i == 0) {
                    return false;
                }
            }
        }
        return true;
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
        System.out.println(isPrimeNumber(Integer.parseInt(args[0])));
    }

}

package ua.nure.danylenko.task1;

public class Part2 {

    public static int getSumOfDigits(int num) {
        int sumOfDigits = 0;
        while (num > 0) {
            sumOfDigits += num % 10;
            num /= 10;
        }
        return sumOfDigits;
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
        System.out.println(getSumOfDigits(Integer.parseInt(args[0])));
    }

}

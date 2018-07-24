package ua.nure.danylenko.task1;

public class Part1 {

    public static int getGratestCommonDivisor(int firstNumber, int secondNumber) {

        while ((firstNumber != 0) && (secondNumber != 0)) {
            if (firstNumber > secondNumber) {
                firstNumber = firstNumber % secondNumber;
            } else {
                secondNumber = secondNumber % firstNumber;
            }
        }
        return firstNumber + secondNumber;
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
        System.out.println(getGratestCommonDivisor(Integer.parseInt(args[0]), Integer.parseInt(args[1])));
    }

}

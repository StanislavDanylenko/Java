package ua.nure.danylenko.task1;

public class Part11 {

    public static int getSumOfDigits(String[] argz) {
        int sumOfDigits = 0;
        for (String currentStr : argz) {
            for (int i = 0; i < currentStr.length(); i++) {
                char currentDigit = currentStr.charAt(i);
                if (currentDigit > 47 && currentDigit < 58) {
                    sumOfDigits += currentDigit - 48;
                }
            }
        }
        //System.out.println(sumOfDigits);
        return sumOfDigits;
    }

    public static void main(String[] args) {
        System.out.println(getSumOfDigits(args));
    }

}

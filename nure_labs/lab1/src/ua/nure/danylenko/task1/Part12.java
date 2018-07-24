package ua.nure.danylenko.task1;

public class Part12 {

    public static String getStringFromArgs(String[] argz) {
        StringBuilder resString = new StringBuilder();

        for (String currentStr : argz) {
            for (int i = 0; i < currentStr.length(); i++) {
                char currentChar = currentStr.charAt(i);
                if ((currentChar > 64 && currentChar < 91) || currentChar > 96 && currentChar < 123) {
                    resString.append(currentChar);
                }
            }
        }
        // System.out.println(resString.toString());
        return resString.toString();
    }

    public static void main(String[] args) {
        System.out.println(getStringFromArgs(args));
    }
}

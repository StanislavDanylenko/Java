package ua.nure.danylenko.task3.Part3;

import ua.nure.danylenko.task3.Util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part3 {
    private static final String REGEX = "(?U)\\b(\\S+)";

    public static String deleteRepeat(String input) {
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {
            String word = matcher.group();
            Pattern p2 = Pattern.compile(word);
            Matcher m2 = p2.matcher(input);
            int count = 0;
            while (m2.find()) {
                count++;
                if (count > 1) {
                    input = input.replaceAll(word, "");
                    break;
                }
            }
        }
        return input;
    }

    public static void main(String[] args) {
        String input = Util.getInput("part3.txt");
        System.out.println(input);
        System.out.println("~~~~~~~~~~~~~~~~~~~~");
        System.out.println(deleteRepeat(input));
    }
}
